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

  // Adicionar eventos de formulário
  document
    .getElementById("itensVendidosForm")
    .addEventListener("submit", function (event) {
      event.preventDefault();
      fetchItensVendidos();
    });
  document
    .getElementById("pedidoMaiorValorForm")
    .addEventListener("submit", function (event) {
      event.preventDefault();
      fetchPedidoMaiorValor();
    });
  document
    .getElementById("pedidoPorClienteForm")
    .addEventListener("submit", function (event) {
      event.preventDefault();
      fetchPedidoPorCliente();
    });
});

let myChart = null;

async function fetchItensVendidos() {
  const dataInicial = document.getElementById("dataInicialItensVendidos").value;
  const dataFinal = document.getElementById("dataFinalItensVendidos").value;

  try {
    const response = await fetch(
      `http://localhost:8080/itens/vendidos-entre/${dataInicial}/${dataFinal}`
    );
    const itensVendidos = await response.json();
    renderTable(
      itensVendidos,
      ["item_idItem", "nome_item", "total_vendas"],
      "Itens Vendidos Por Período"
    );
    // Esconder o modal
    $("#itensVendidosModal").modal("hide");
    renderChart(
      itensVendidos.map((item) => item.nome_item),
      itensVendidos.map((item) => item.total_vendas),
      "Total de Vendas por Item",
      "myChart"
    );
  } catch (error) {
    console.error("Erro ao buscar itens vendidos:", error);
  }
}

async function fetchPedidoMaiorValor() {
  const dataInicial = document.getElementById("dataInicialMaiorValor").value;
  const dataFinal = document.getElementById("dataFinalMaiorValor").value;

  try {
    const response = await fetch(
      `http://localhost:8080/pedido-cliente/total-max-entre-datas/${dataInicial}/${dataFinal}`
    );
    const pedidoMaiorValor = await response.json();
    renderTable(
      pedidoMaiorValor,
      [
        "pedido_nrPedido",
        "nome_cliente",
        "sobrenome_cliente",
        "data_hora_pedido",
        "valor_total_pedido",
      ],
      "Pedido de maior valor por Período"
    );
    // Esconder o modal
    $("#pedidoMaiorValorModal").modal("hide");
    clearChart();
  } catch (error) {
    console.error("Erro ao buscar pedido de maior valor", error);
  }
}

async function fetchPedidoPorCliente() {
  const dataInicial = document.getElementById("dataInicialPedidoCliente").value;
  const dataFinal = document.getElementById("dataFinalPedidoCliente").value;

  try {
    const response = await fetch(
      `http://localhost:8080/pedido-cliente/count-entre-datas/${dataInicial}/${dataFinal}`
    );
    const pedidoPorCliente = await response.json();
    renderTable(
      pedidoPorCliente,
      ["nome_cliente", "sobrenome_cliente", "quantidade_pedidos"],
      "N° de Pedidos por Cliente"
    );
    // Esconder o modal
    $("#pedidoPorClienteModal").modal("hide");
    renderChart(
      pedidoPorCliente.map(
        (item) => `${item.nome_cliente} ${item.sobrenome_cliente}`
      ),
      pedidoPorCliente.map((item) => item.quantidade_pedidos),
      "N° de Pedidos por Cliente",
      "myChart"
    );
  } catch (error) {
    console.error("Erro ao buscar pedidos por cliente:", error);
  }
}

function renderTable(data, columns, title) {
  const resultContainer = document.getElementById("result-container");
  resultContainer.innerHTML = "";

  if (data.length === 0) {
    resultContainer.innerHTML = "<p>Nenhum dado encontrado.</p>";
    return;
  }

  const tableTitle = document.createElement("h3");
  tableTitle.innerText = title;
  resultContainer.appendChild(tableTitle);

  const table = document.createElement("table");
  table.className =
    "table table-dark table-striped shadow-lg border border-2 border-black rounded-3 overflow-hidden";
  const thead = document.createElement("thead");
  const tbody = document.createElement("tbody");

  const headerRow = document.createElement("tr");
  columns.forEach((column) => {
    const th = document.createElement("th");
    th.scope = "col";
    switch (column) {
      case "item_idItem":
        th.innerText = "Item ID";
        break;
      case "nome_item":
        th.innerText = "Nome";
        break;
      case "total_vendas":
        th.innerText = "Total de Vendas";
        break;
      case "pedido_nrPedido":
        th.innerText = "N° do Pedido";
        break;
      case "nome_cliente":
        th.innerText = "Nome";
        break;
      case "sobrenome_cliente":
        th.innerText = "Sobrenome";
        break;
      case "data_hora_pedido":
        th.innerText = "Data e Hora";
        break;
      case "valor_total_pedido":
        th.innerText = "Valor Total do Pedido";
        break;
      case "quantidade_pedidos":
        th.innerText = "Quantidade de Pedidos";
        break;
      default:
        th.innerText = column.replace(/_/g, " ");
    }
    headerRow.appendChild(th);
  });
  thead.appendChild(headerRow);

  data.forEach((item) => {
    const row = document.createElement("tr");
    columns.forEach((column) => {
      const td = document.createElement("td");
      if (column === "data_hora_pedido") {
        const date = new Date(item[column]);
        td.innerText = `${date.toLocaleDateString()} - ${date.toLocaleTimeString()}`;
      } else {
        td.innerText = item[column];
      }
      row.appendChild(td);
    });
    tbody.appendChild(row);
  });

  table.appendChild(thead);
  table.appendChild(tbody);
  resultContainer.appendChild(table);
}

function renderChart(labels, values, title, canvasId) {
  if (myChart) {
    myChart.destroy();
  }

  const ctx = document.getElementById(canvasId).getContext("2d");
  const colors = values.map(
    () =>
      `rgba(${Math.floor(Math.random() * 255)}, ${Math.floor(
        Math.random() * 255
      )}, ${Math.floor(Math.random() * 255)}, 0.2)`
  );
  const borderColors = values.map(
    () =>
      `rgba(${Math.floor(Math.random() * 255)}, ${Math.floor(
        Math.random() * 255
      )}, ${Math.floor(Math.random() * 255)}, 1)`
  );

  myChart = new Chart(ctx, {
    type: "bar",
    data: {
      labels: labels,
      datasets: [
        {
          label: title,
          data: values,
          backgroundColor: colors,
          borderColor: borderColors,
          borderWidth: 1,
        },
      ],
    },
    options: {
      scales: {
        y: {
          beginAtZero: true,
        },
      },
    },
  });
}

function clearChart() {
  if (myChart) {
    myChart.destroy();
    myChart = null;
  }
}
