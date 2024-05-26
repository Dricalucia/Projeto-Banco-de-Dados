window.addEventListener("DOMContentLoaded", (event) => {
  const headerContainer = document.getElementById("header-container");
  fetch("../../../utils/HeaderFuncionario.html")
    .then((response) => response.text())
    .then((data) => {
      headerContainer.innerHTML = data;
    });

  const loggedInUser = JSON.parse(sessionStorage.getItem("loggedInUser"));
  if (!loggedInUser || !loggedInUser.cpf) {
    console.error("Nenhum funcionário logado encontrado");
    window.location.href =
      "/Projeto-Banco-de-Dados/client/src/pages/login/funcionario/LoginFuncionario.html";
    return;
  } else {
    console.log("Funcionário logado encontrado:", loggedInUser);
  }

  loadPedidosEmAberto();
});

async function loadPedidosEmAberto() {
  try {
    const response = await fetch(`http://localhost:8080/pedidos`);
    const pedidos = await response.json();
    const pedidosEmAberto = pedidos.filter(
      (pedido) => pedido.statusPedido === "Em Aberto"
    );
    const pedidosEmAbertoContainer = document.getElementById(
      "pedidosEmAbertoContainer"
    );
    pedidosEmAbertoContainer.innerHTML = "";

    const promises = pedidosEmAberto.map(async (pedido) => {
      const pedidoClienteResponse = await fetch(
        `http://localhost:8080/pedido-cliente`
      );
      const pedidoCliente = await pedidoClienteResponse.json();
      const clienteId = pedidoCliente.find(
        (pc) => pc.pedidoNrPedido === pedido.nrPedido
      )?.clienteIdCliente;

      if (clienteId) {
        const clienteResponse = await fetch(
          `http://localhost:8080/clientes/${clienteId}`
        );
        const cliente = await clienteResponse.json();

        const itensPedidoResponse = await fetch(
          `http://localhost:8080/itens-pedido/${pedido.nrPedido}`
        );
        const itensPedido = await itensPedidoResponse.json();

        const itemPromises = itensPedido.map(async (item) => {
          const itemResponse = await fetch(
            `http://localhost:8080/itens/${item.itemIdItem}`
          );
          const itemDetails = await itemResponse.json();
          return {
            nomeItem: itemDetails.nomeItem,
            qtde: item.quantidadeItem,
            preco: itemDetails.precoItem,
          };
        });

        const items = await Promise.all(itemPromises);
        const itemDetails = items
          .map((item) => `${item.qtde} ${item.nomeItem}`)
          .join("<br>");
        const truncatedItemDetails =
          itemDetails.length > 100
            ? itemDetails.substring(0, 97) + "..."
            : itemDetails;

        const valorTotal = items.reduce(
          (total, item) => total + item.qtde * item.preco,
          0
        );

        return {
          pedido,
          cliente,
          itemDetails,
          truncatedItemDetails,
          valorTotal,
        };
      }
    });

    const pedidosDetalhados = await Promise.all(promises);

    pedidosDetalhados.forEach(
      ({ pedido, cliente, itemDetails, truncatedItemDetails, valorTotal }) => {
        const card = document.createElement("div");
        card.className = "col-md-4";
        card.innerHTML = `
              <div class="card mb-4 shadow-lg rounded-2 border border-2 border-black">
                <div class="card-header d-flex justify-content-between align-items-center">
                  <h3 class="my-0">Pedido N° ${pedido.nrPedido}</h3>
                  ${
                    itemDetails.length > 100
                      ? `<button type="button" class="btn btn-sm btn-outline-secondary" data-bs-toggle="modal" data-bs-target="#verMaisModal" onclick="showMoreDetails('${pedido.nrPedido}', '${cliente.nome}', '${cliente.sobrenome}', '${itemDetails}', ${valorTotal})">...</button>`
                      : ""
                  }
                </div>
                <div class="card-body">
                  <h6 class="card-title">${new Date(
                    pedido.dataHoraPedido
                  ).toLocaleDateString()}</h6>
                  <p class="card-text" id="itemDetails-${pedido.nrPedido}">
                    ${truncatedItemDetails}
                  </p>
                  <p class="card-text"><i class="bi bi-person"></i> ${
                    cliente.nome
                  } ${cliente.sobrenome}</p>
                  <h5 class="card-title">Total: R$ ${valorTotal.toFixed(2)}</h5>
                  <div class="d-flex justify-content-between align-items-center">
                    <button type="button" class="btn btn-success" onclick="aceitarPedido(${
                      pedido.nrPedido
                    })">Aceitar</button>
                    <button type="button" class="btn btn-danger" onclick="negarPedido(${
                      pedido.nrPedido
                    })">Negar</button>
                  </div>
                </div>
              </div>
            `;
        pedidosEmAbertoContainer.appendChild(card);
      }
    );
  } catch (error) {
    console.error("Erro ao carregar pedidos em aberto:", error);
  }
}

function showMoreDetails(
  nrPedido,
  nomeCliente,
  sobrenomeCliente,
  itemDetails,
  valorTotal
) {
  document.getElementById(
    "verMaisModalLabel"
  ).innerText = `Detalhes do Pedido N° ${nrPedido}`;
  document.getElementById("itensPedido").innerText = `Itens`;
  document.getElementById("modalItemDetails").innerHTML = itemDetails;
  document.getElementById(
    "modalClientDetails"
  ).innerHTML = `<i class="bi bi-person"></i> ${nomeCliente} ${sobrenomeCliente}`;
  document.getElementById(
    "modalTotalValue"
  ).innerText = `Total: R$ ${valorTotal.toFixed(2)}`;

  document.getElementById("modalAceitarBtn").onclick = () =>
    aceitarPedido(nrPedido);
  document.getElementById("modalNegarBtn").onclick = () =>
    negarPedido(nrPedido);
}

async function aceitarPedido(nrPedido) {
  try {
    const pedidoResponse = await fetch(
      `http://localhost:8080/pedidos/${nrPedido}`
    );
    if (!pedidoResponse.ok) {
      console.error(
        "Erro ao obter detalhes do pedido",
        await pedidoResponse.text()
      );
      return;
    }
    const pedido = await pedidoResponse.json();

    pedido.statusPedido = "Em Preparo";

    const response = await fetch(`http://localhost:8080/pedidos/${nrPedido}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(pedido),
    });
    if (response.ok) {
      console.log(`Pedido ${nrPedido} aceito`);
      loadPedidosEmAberto(); // Recarrega a lista de pedidos
    } else {
      console.error("Erro ao aceitar pedido");
    }
  } catch (error) {
    console.error("Erro ao aceitar pedido:", error);
  }
}

async function negarPedido(nrPedido) {
  try {
    const pedidoResponse = await fetch(
      `http://localhost:8080/pedidos/${nrPedido}`
    );
    if (!pedidoResponse.ok) {
      console.error(
        "Erro ao obter detalhes do pedido",
        await pedidoResponse.text()
      );
      return;
    }
    const pedido = await pedidoResponse.json();

    pedido.statusPedido = "Negado";

    const response = await fetch(`http://localhost:8080/pedidos/${nrPedido}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(pedido),
    });
    if (response.ok) {
      console.log(`Pedido ${nrPedido} negado`);
      loadPedidosEmAberto(); // Recarrega a lista de pedidos
    } else {
      console.error("Erro ao negar pedido");
    }
  } catch (error) {
    console.error("Erro ao negar pedido:", error);
  }
}
