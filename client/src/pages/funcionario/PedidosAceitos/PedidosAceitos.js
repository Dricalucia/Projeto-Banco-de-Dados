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

  loadPedidosAceitos();
  setInterval(loadPedidosAceitos, 60000); // Atualiza a cada 1 minuto
});

async function loadPedidosAceitos() {
  try {
    const response = await fetch(`http://localhost:8080/pedidos`);
    const pedidos = await response.json();
    const pedidosAceitosContainer =
      document.getElementById("pedidosTabelaCorpo");
    pedidosAceitosContainer.innerHTML = "";

    const promises = pedidos.map(async (pedido) => {
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
        if (!itensPedidoResponse.ok) {
          console.warn(`Itens do pedido ${pedido.nrPedido} não encontrados`);
          return null; // Ignora o pedido se os itens não forem encontrados
        }
        const itensPedido = await itensPedidoResponse.json();

        const itemPromises = itensPedido.map(async (item) => {
          const itemResponse = await fetch(
            `http://localhost:8080/itens/${item.itemIdItem}`
          );
          if (!itemResponse.ok) {
            console.warn(`Detalhes do item ${item.itemIdItem} não encontrados`);
            return null; // Ignora o item se os detalhes não forem encontrados
          }
          const itemDetails = await itemResponse.json();
          return {
            nomeItem: itemDetails.nomeItem,
            qtde: item.quantidadeItem,
            preco: itemDetails.precoItem,
          };
        });

        const items = (await Promise.all(itemPromises)).filter(Boolean); // Remove itens nulos
        if (items.length === 0) {
          console.warn(`Pedido ${pedido.nrPedido} não possui itens válidos`);
          return null;
        }

        const itemDetails = items
          .map((item) => `${item.qtde} ${item.nomeItem}`)
          .join("<br>");
        const truncatedItemDetails = truncateText(itemDetails, 2);

        const valorTotal = items.reduce(
          (total, item) => total + item.qtde * item.preco,
          0
        );
        const tempoPedido = Math.floor(
          (Date.now() - new Date(pedido.dataHoraPedido).getTime()) / 60000
        ); // em minutos
        const corStatus = getCorStatus(tempoPedido);

        return {
          pedido,
          cliente,
          itemDetails,
          truncatedItemDetails,
          valorTotal,
          tempoPedido,
          corStatus,
        };
      }
    });

    const pedidosDetalhados = (await Promise.all(promises)).filter(Boolean); // Remove pedidos nulos

    pedidosDetalhados.sort(
      (a, b) =>
        new Date(b.pedido.dataHoraPedido) - new Date(a.pedido.dataHoraPedido)
    );

    pedidosDetalhados.forEach(
      ({
        pedido,
        cliente,
        itemDetails,
        truncatedItemDetails,
        valorTotal,
        tempoPedido,
        corStatus,
      }) => {
        const isConcluido = pedido.statusPedido === "Concluído";
        const isNegado = pedido.statusPedido === "Negado";
        const row = document.createElement("tr");
        row.innerHTML = `
              <th scope="row">${pedido.nrPedido}</th>
              <td>${cliente.nome} ${cliente.sobrenome}</td>
              <td id="itemDetails-${
                pedido.nrPedido
              }">${truncatedItemDetails}</td>
              <td>
                <div class="dropdown">
                  <button class="btn btn-${corStatus} dropdown-toggle" type="button" id="dropdownMenuButton-${
          pedido.nrPedido
        }" data-bs-toggle="dropdown" aria-expanded="false" ${
          isConcluido || isNegado ? "disabled" : ""
        }>
                    ${pedido.statusPedido}
                  </button>
                  <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton-${
                    pedido.nrPedido
                  }">
                    <li><a class="dropdown-item" href="#" onclick="changePedidoStatus(${
                      pedido.nrPedido
                    }, 'Em Aberto')">Em Aberto</a></li>
                    <li><a class="dropdown-item" href="#" onclick="changePedidoStatus(${
                      pedido.nrPedido
                    }, 'Em Preparo')">Em Preparo</a></li>
                    <li><a class="dropdown-item" href="#" onclick="changePedidoStatus(${
                      pedido.nrPedido
                    }, 'Em Rota')">Em Rota</a></li>
                    <li><a class="dropdown-item" href="#" onclick="changePedidoStatus(${
                      pedido.nrPedido
                    }, 'Concluído')">Concluído</a></li>
                  </ul>
                </div>
              </td>
              <td>${tempoPedido} minutos</td>
              <td>R$ ${valorTotal.toFixed(2)}</td>
              <td>
              <button type="button" class="btn btn-sm btn-outline-secondary" data-bs-toggle="modal" data-bs-target="#verMaisModal" onclick="showMoreDetails('${
                pedido.nrPedido
              }', '${cliente.nome}', '${
          cliente.sobrenome
        }', '${itemDetails}', ${valorTotal})">...</button>
              </td>
            `;
        pedidosAceitosContainer.appendChild(row);
      }
    );
  } catch (error) {
    console.error("Erro ao carregar pedidos aceitos:", error);
  }
}

function truncateText(text, maxLines) {
  const lines = text.split("<br>");
  if (lines.length <= maxLines) return text;
  return lines.slice(0, maxLines).join("<br>") + "...";
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
  document.getElementById("modalItemDetails").innerHTML = itemDetails.replace(
    /, /g,
    "<br>"
  );
  document.getElementById(
    "modalClientDetails"
  ).innerHTML = `<i class="bi bi-person"></i> ${nomeCliente} ${sobrenomeCliente}`;
  document.getElementById(
    "modalTotalValue"
  ).innerText = `Total: R$ ${valorTotal.toFixed(2)}`;
}

async function changePedidoStatus(nrPedido, novoStatus) {
  try {
    // Obter dados atuais do pedido
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

    // Atualizar os campos necessários de acordo com o novo status
    pedido.statusPedido = novoStatus;
    const now = getLocalISOString(); // Use a função para obter a hora local

    if (novoStatus === "Em Rota") {
      pedido.dataHoraSaidaEntrega = now;
    } else if (novoStatus === "Concluído") {
      pedido.dataHoraEntrega = now;
    }

    // Enviar todos os dados necessários na solicitação PUT
    const response = await fetch(`http://localhost:8080/pedidos/${nrPedido}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(pedido),
    });
    if (response.ok) {
      console.log(`Pedido ${nrPedido} atualizado para ${novoStatus}`);
      loadPedidosAceitos(); // Recarrega a lista de pedidos
    } else {
      console.error(
        "Erro ao atualizar status do pedido",
        await response.text()
      );
    }
  } catch (error) {
    console.error("Erro ao atualizar status do pedido:", error);
  }
}

function getLocalISOString() {
  const now = new Date();
  const offset = now.getTimezoneOffset();
  const localISOTime = new Date(now.getTime() - offset * 60 * 1000)
    .toISOString()
    .slice(0, -1);
  return localISOTime;
}

function getCorStatus(tempoPedido) {
  if (tempoPedido < 10) {
    return "success"; // Verde
  } else if (tempoPedido < 20) {
    return "warning"; // Amarelo
  } else {
    return "danger"; // Vermelho
  }
}
