window.addEventListener("DOMContentLoaded", (event) => {
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

  loadSacolaItems();

  document
    .getElementById("confirmarPedidoButton")
    .addEventListener("click", confirmarPedido);
  document
    .getElementById("ajustarEnderecoButton")
    .addEventListener("click", ajustarEndereco);
  document
    .getElementById("confirmarEnderecoButton")
    .addEventListener("click", abrirModalPagamento);
  document
    .getElementById("confirmarPagamentoButton")
    .addEventListener("click", enviarPedido);
});

function loadSacolaItems() {
  const sacolaItemsPanel = document.getElementById("sacolaItemsPanel");
  const valorTotalPedidoElement = document.getElementById("valorTotalPedido");
  sacolaItemsPanel.innerHTML = "";

  const sacolaItems = JSON.parse(localStorage.getItem("sacola")) || [];

  if (sacolaItems.length === 0) {
    sacolaItemsPanel.innerHTML =
      '<p class="text-center">Sua sacola está vazia.</p>';
    valorTotalPedidoElement.textContent = "";
  } else {
    let valorTotal = 0;
    sacolaItems.forEach((item, index) => {
      const itemTotal = item.precoItem * item.quantidade;
      valorTotal += itemTotal;
      const itemRow = `
                <div class="row mb-3 p-2 border-bottom">
                    <div class="col-md-8">
                        <p class="mb-1"><strong>${item.nomeItem}</strong></p>
                        <p class="mb-1">R$ ${item.precoItem.toFixed(2)}</p>
                        <p class="mb-1">Quantidade: 
                            <button class="btn btn-sm btn-secondary me-1" onclick="updateItemQuantity(${index}, -1)">-</button>
                            ${item.quantidade}
                            <button class="btn btn-sm btn-secondary ms-1" onclick="updateItemQuantity(${index}, 1)">+</button>
                        </p>
                        <p class="mb-1">Preço total: R$ ${itemTotal.toFixed(
                          2
                        )}</p>
                        <p class="mb-1">Observação: ${item.observacao}</p>
                    </div>
                    <div class="col-md-4 d-flex justify-content-end align-items-center">
                        <button class="btn btn-sm btn-danger me-2" onclick="removeItemFromSacola(${index})">Remover</button>
                    </div>
                </div>`;
      sacolaItemsPanel.innerHTML += itemRow;
    });
    valorTotalPedidoElement.textContent = `Valor Total: R$ ${valorTotal.toFixed(
      2
    )}`;
  }
}

function updateItemQuantity(index, change) {
  const sacolaItems = JSON.parse(localStorage.getItem("sacola")) || [];
  if (sacolaItems[index].quantidade + change > 0) {
    sacolaItems[index].quantidade += change;
    localStorage.setItem("sacola", JSON.stringify(sacolaItems));
    loadSacolaItems();
  }
}

function removeItemFromSacola(index) {
  const sacolaItems = JSON.parse(localStorage.getItem("sacola")) || [];
  sacolaItems.splice(index, 1);
  localStorage.setItem("sacola", JSON.stringify(sacolaItems));
  loadSacolaItems();
}

function confirmarPedido() {
  const sacolaItems = JSON.parse(localStorage.getItem("sacola")) || [];
  const loggedInUser = JSON.parse(sessionStorage.getItem("loggedInUser"));

  if (!loggedInUser) {
    alert("Nenhum usuário logado encontrado. Por favor, faça login.");
    window.location.href =
      "/Projeto-Banco-de-Dados/client/src/pages/login/cliente/LoginCliente.html";
    return;
  }

  if (sacolaItems.length === 0) {
    alert("Sua sacola está vazia.");
    return;
  }

  fetch(`http://localhost:8080/clientes/${loggedInUser.idCliente}`)
    .then((response) => response.json())
    .then((cliente) => {
      const enderecoCliente = `
                ${cliente.rua}, ${cliente.numero} - ${cliente.bairro}<br>
                ${cliente.cidade} - ${cliente.uf}<br>
                Complemento: ${cliente.complemento}<br>
                Ponto de Referência: ${cliente.pontoReferencia}
            `;
      document.getElementById("enderecoCliente").innerHTML = enderecoCliente;
      $("#confirmarEnderecoModal").modal("show");
    })
    .catch((error) => {
      console.error("Erro ao buscar endereço do cliente:", error);
      alert("Erro ao buscar endereço do cliente. Por favor, tente novamente.");
    });
}

function ajustarEndereco() {
  window.location.href =
    "/Projeto-Banco-de-Dados/client/src/pages/cliente/configuracoesDeContaCliente/configuracoesDeContaCliente.html";
}

function abrirModalPagamento() {
  $("#confirmarEnderecoModal").modal("hide");
  $("#pagamentoModal").modal("show");
}


// NÃO MEXER NUNCA MAIS! ESTÁ FUNCIONANDO!
async function enviarPedido() {
  const sacolaItems = JSON.parse(localStorage.getItem("sacola")) || [];
  const loggedInUser = JSON.parse(sessionStorage.getItem("loggedInUser"));
  const formaPagamento = document.getElementById("formaPagamento").value;

  try {
    const response = await fetch("http://localhost:8080/pedidos");
    if (!response.ok) {
      throw new Error("Erro ao obter pedidos");
    }
    const pedidos = await response.json();
    const ultimoPedido = pedidos[pedidos.length - 1];
    const nrPedido = ultimoPedido ? ultimoPedido.nrPedido + 1 : 1;

    const pedido = {
      nrPedido: nrPedido,
      dataHoraPedido: new Date().toISOString(),
      dataHoraPrevistaEntrega: new Date(
        new Date().getTime() + 30 * 60 * 1000
      ).toISOString(),
      dataHoraSaidaEntrega: null,
      dataHoraEntrega: null,
      statusPedido: "Em Aberto",
      canalSolicitacaoPedido: "site",
      canalEntrega: "domicilio",
      formaPagamento: formaPagamento,
      valorTotalPedido: sacolaItems.reduce(
        (total, item) => total + item.precoItem * item.quantidade,
        0
      ),
    };

    console.log("Enviando pedido:", pedido);

    const responsePedido = await fetch("http://localhost:8080/pedidos", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(pedido),
    });

    const responsePedidoText = await fetch("http://localhost:8080/pedidos/" + nrPedido).then((response) => response.text());
    console.log("Resposta do servidor (texto):", responsePedidoText);

    if (!responsePedido.ok) {
      console.error("Erro ao criar pedido:", responsePedidoText);
      throw new Error("Erro ao criar pedido");
    }

    let pedidoCriado = {};
    if (responsePedidoText) {
      try {
        pedidoCriado = JSON.parse(responsePedidoText);
      } catch (error) {
        console.error(
          "Erro ao analisar o JSON:",
          error,
          "Resposta do servidor:",
          responsePedidoText
        );
        throw new Error("Erro ao analisar o JSON");
      }
    }

    const pedidoCliente = {
      clienteIdCliente: loggedInUser.idCliente,
      pedidoNrPedido: pedidoCriado.nrPedido,
    };

    console.log("Enviando pedido do cliente:", pedidoCliente);

    await fetch("http://localhost:8080/pedido-cliente", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(pedidoCliente),
    });

    const itensPedidos = sacolaItems.map((item) => ({
      pedidoNrPedido: pedidoCriado.nrPedido,
      itemIdItem: item.idItem,
      quantidadeItem: item.quantidade,
    }));

    await Promise.all(
      itensPedidos.map((itensPedido) => {
        return fetch("http://localhost:8080/itens-pedido", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(itensPedido),
        });
      })
    );

    $("#pagamentoModal").modal("hide");
    $("#confirmacaoModal").modal("show");
    localStorage.removeItem("sacola");
    loadSacolaItems();
  } catch (error) {
    console.error("Erro ao enviar pedido:", error);
    alert("Erro ao enviar o pedido. Por favor, tente novamente.");
  }
}
