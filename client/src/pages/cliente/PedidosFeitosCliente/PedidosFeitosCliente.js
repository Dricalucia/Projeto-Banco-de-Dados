document.addEventListener("DOMContentLoaded", function () {
  const headerContainer = document.getElementById("header-container");
  fetch("../../../utils/HeaderCliente.html")
    .then((response) => response.text())
    .then((data) => {
      headerContainer.innerHTML = data;
    });

  const loggedInUser = JSON.parse(sessionStorage.getItem("loggedInUser"));
  if (!loggedInUser) {
    console.error("Nenhum usuário logado encontrado");
    window.location.href =
      "/Projeto-Banco-de-Dados/client/src/pages/login/cliente/LoginCliente.html";
    return;
  } else {
    console.log("Usuário logado encontrado:", loggedInUser);
  }

  let pedidosCache = [];

  loadPedidos();

  function loadPedidos() {
    fetch(`http://localhost:8080/pedido-cliente/${loggedInUser.idCliente}`)
      .then((response) => response.json())
      .then((pedidoClientes) => {
        const pedidosTabelaCorpo =
          document.getElementById("pedidosTabelaCorpo");
        pedidosTabelaCorpo.innerHTML = "";

        const promises = pedidoClientes.map(async (pedidoCliente) => {
          const response = await fetch(
            `http://localhost:8080/pedidos/${pedidoCliente.pedidoNrPedido}`
          );
          const pedido = await response.json();
          const response_1 = await fetch(
            `http://localhost:8080/itens-pedido/${pedido.nrPedido}`
          );
          const itensPedido = await response_1.json();
          const itemPromises = itensPedido.map((item) =>
            fetch(`http://localhost:8080/itens/${item.itemIdItem}`).then(
              (response_2) =>
                response_2.json().then((itemDetails) => ({
                  ...itemDetails,
                  qtde: item.quantidadeItem,
                  preco: itemDetails.precoItem,
                }))
            )
          );
          const items = await Promise.all(itemPromises);
          const itemNames = items.map((item_1) => item_1.nomeItem);
          let itens = itemNames.join(", ");
          if (itens.length > 20) {
            itens = itens.substring(0, 17) + "...";
          }

          const pedidoCompleto = {
            pedido,
            itensPedido: items,
            status: pedido.statusPedido,
            valorTotal: pedido.valorTotalPedido.toFixed(2),
            enderecoEntrega: `${loggedInUser.rua}, ${loggedInUser.numero}, ${loggedInUser.bairro}, ${loggedInUser.cidade}, ${loggedInUser.uf}`,	
          };
          pedidosCache.push(pedidoCompleto);
          console.log("Pedido completo:", pedidoCompleto);

          return {
            pedido,
            itens,
            status: pedido.statusPedido,
            valorTotal: pedido.valorTotalPedido.toFixed(2),
          };
        });

        Promise.all(promises).then((pedidos) => {
          pedidos
            .sort((a, b) => b.pedido.nrPedido - a.pedido.nrPedido)
            .forEach(({ pedido, itens, status, valorTotal }) => {
              const tr = document.createElement("tr");
              tr.innerHTML = `
                <th scope="row">${pedido.nrPedido}</th>
                <td>${itens}</td>
                <td>${status}</td>
                <td>R$ ${valorTotal}</td>
                <td><button class="btn btn-sm btn-outline-secondary" data-bs-toggle="modal" data-bs-target="#InfoPedido" onclick="loadPedidoDetalhes(${pedido.nrPedido})">...</button></td>
              `;
              pedidosTabelaCorpo.appendChild(tr);
            });
        });
      })
      .catch((error) => console.error("Erro ao carregar pedidos:", error));
  }

  window.loadPedidoDetalhes = function (nrPedido) {
    const pedidoDetalhes = pedidosCache.find(
      (pedido) => pedido.pedido.nrPedido === nrPedido
    );
    if (pedidoDetalhes) {
      const infoPedidoModalLabel = document.getElementById(
        "infoPedidoModalLabel"
      );
      const infoPedidoItensTabelaCorpo = document.getElementById(
        "infoPedidoItensTabelaCorpo"
      );
      const infoPedidoModalEnderecoEntrega = document.getElementById(
        "infoPedidoModalEnderecoEntrega"
      );
      const infoPedidoModalValorTotal = document.getElementById(
        "infoPedidoModalValorTotal"
      );

      infoPedidoModalLabel.textContent = `Informações Pedido N° ${nrPedido}`;
      infoPedidoItensTabelaCorpo.innerHTML = "";

      let totalPedido = 0;

      pedidoDetalhes.itensPedido.forEach((item) => {
        totalPedido += item.qtde * item.preco;

        const tr = document.createElement("tr");
        const currentTime = new Date();
        const deliveryTime = new Date(
          pedidoDetalhes.pedido.dataHoraPrevistaEntrega
        );
        const diff = Math.floor((currentTime - deliveryTime) / 60000);

        let entrega = diff > 0 ? `${diff} min` : "Atrasado";
        if (diff <= 0 && pedidoDetalhes.pedido.dataHoraEntrega) {
          entrega = "Entregue";
        }

        tr.innerHTML = `
          <td>${item.nomeItem}</td>
          <td>${item.qtde}</td>
          <td>${pedidoDetalhes.pedido.statusPedido}</td>
          <td>R$ ${(item.qtde * item.preco).toFixed(2)}</td>
          <td>${entrega}</td>
          <td>${pedidoDetalhes.pedido.formaPagamento}</td>
        `;
        infoPedidoItensTabelaCorpo.appendChild(tr);
      });

      infoPedidoModalValorTotal.textContent = `R$ ${totalPedido.toFixed(2)}`;
      infoPedidoModalEnderecoEntrega.textContent =
        pedidoDetalhes.enderecoEntrega || "N/A";
    }
  };
});
