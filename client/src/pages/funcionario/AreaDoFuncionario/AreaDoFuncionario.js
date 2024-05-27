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

  loadFuncionarios();
});

async function loadFuncionarios() {
  try {
    const response = await fetch(`http://localhost:8080/funcionarios`);
    const funcionarios = await response.json();
    const funcionariosTabelaCorpo = document.getElementById(
      "funcionariosTabelaCorpo"
    );
    funcionariosTabelaCorpo.innerHTML = "";

    funcionarios.forEach((funcionario) => {
      const row = document.createElement("tr");
      row.innerHTML = `
        <td>${funcionario.matricula}</td>
        <td>${funcionario.nome}</td>
        <td>${funcionario.funcao}</td>
        <td>R$ ${funcionario.salario.toFixed(2)}</td>
        <td>${funcionario.status}</td>
        <td>
          <button type="button" class="btn btn-sm btn-outline-secondary" data-bs-toggle="modal" data-bs-target="#verMaisModal" onclick="showMoreDetails('${
            funcionario.matricula
          }')">...</button>
        </td>
      `;
      funcionariosTabelaCorpo.appendChild(row);
    });
  } catch (error) {
    console.error("Erro ao carregar funcionários:", error);
  }
}

async function showMoreDetails(matricula) {
  try {
    const response = await fetch(
      `http://localhost:8080/funcionarios/${matricula}`
    );
    if (!response.ok) {
      console.error(
        "Erro ao obter detalhes do funcionário:",
        await response.text()
      );
      return;
    }
    const funcionario = await response.json();

    // Preencher detalhes do funcionário no modal
    document.getElementById(
      "verMaisModalLabel"
    ).innerText = `Detalhes do Funcionário: ${funcionario.nome}`;
    const funcionarioDetalhesTabelaCorpo = document.getElementById(
      "funcionarioDetalhesTabelaCorpo"
    );
    funcionarioDetalhesTabelaCorpo.innerHTML = `
      <tr>
        <td>${funcionario.matricula}</td>
        <td>${funcionario.nome}</td>
        <td>${funcionario.funcao}</td>
        <td>R$ ${funcionario.salario.toFixed(2)}</td>
        <td>${funcionario.status}</td>
        <td>${funcionario.cpf}</td>
        <td>${new Date(funcionario.dataAdmissao).toLocaleDateString()}</td>
      </tr>
    `;

    // Preencher endereço do funcionário
    document.getElementById("enderecoDetalhes").innerHTML = `
      ${funcionario.rua}, ${funcionario.numero}, ${funcionario.bairro}, ${funcionario.cidade} - ${funcionario.uf}
      <br>CEP: ${funcionario.cep}
    `;

    // Buscar e preencher dependentes do funcionário
    const dependentesResponse = await fetch(
      `http://localhost:8080/dependentes/${matricula}`
    );
    const dependentesContainer = document.getElementById(
      "dependentesContainer"
    );
    dependentesContainer.innerHTML = "";

    if (dependentesResponse.ok) {
      const dependentes = await dependentesResponse.json();
      if (dependentes.length > 0) {
        const dependentesTabela = document.createElement("table");
        dependentesTabela.className =
          "table table-dark table-striped shadow-lg border border-2 border-black rounded-3 overflow-hidden";
        dependentesTabela.innerHTML = `
          <thead>
            <tr class="mt-4">
              <th scope="col">Nome Dependente</th>
              <th scope="col">CPF Dependente</th>
              <th scope="col">Data de Nascimento Dependente</th>
            </tr>
          </thead>
          <tbody>
            ${dependentes
              .map(
                (dep) => `
              <tr>
                <td>${dep.nome}</td>
                <td>${dep.cpfDependente}</td>
                <td>${new Date(dep.dataNascimento).toLocaleDateString()}</td>
              </tr>
            `
              )
              .join("")}
          </tbody>
        `;
        dependentesContainer.appendChild(dependentesTabela);
      } else {
        dependentesContainer.innerHTML = "<p>Nenhum dependente.</p>";
      }
    } else if (dependentesResponse.status === 404) {
      dependentesContainer.innerHTML = "<p>Nenhum dependente.</p>";
    } else {
      console.error(
        "Erro ao obter dependentes do funcionário:",
        await dependentesResponse.text()
      );
    }
  } catch (error) {
    console.error("Erro ao carregar detalhes do funcionário:", error);
  }
}
