let globalMatricula; // Variável global para armazenar a matrícula do funcionário

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

  // Carregar dados de categorias para dropdown de item
  loadCategorias();

  // Adicionar eventos de formulário
  document
    .getElementById("cadastrarFuncionarioForm")
    .addEventListener("submit", cadastrarFuncionario);
  document
    .getElementById("buscarFuncionarioForm")
    .addEventListener("submit", buscarFuncionario);
  document
    .getElementById("editarFuncionarioForm")
    .addEventListener("submit", editarFuncionario);
  document
    .getElementById("cadastrarItemForm")
    .addEventListener("submit", cadastrarItem);
  document
    .getElementById("buscarItemForm")
    .addEventListener("submit", buscarItem);
  document
    .getElementById("editarItemForm")
    .addEventListener("submit", editarItem);
  document.getElementById("cep").addEventListener("blur", preencherEndereco);
  document
    .getElementById("editarCep")
    .addEventListener("blur", preencherEnderecoEditar);

  // Carregar clientes ao abrir modal
  $("#clientesModal").on("show.bs.modal", loadClientes);

  // Mostrar ou esconder campos de dependentes no modal de cadastro
  document
    .getElementById("hasDependente")
    .addEventListener("change", function () {
      const dependenteFields = document.getElementById("dependenteFields");
      if (this.checked) {
        dependenteFields.classList.remove("d-none");
      } else {
        dependenteFields.classList.add("d-none");
      }
    });

  // Mostrar ou esconder campos de dependentes no modal de edição
  document
    .getElementById("hasDependenteEditar")
    .addEventListener("change", function () {
      const dependenteFields = document.getElementById(
        "dependenteEditarNovoFields"
      );
      if (this.checked) {
        dependenteFields.classList.remove("d-none");
      } else {
        dependenteFields.classList.add("d-none");
      }
    });
});

async function loadCategorias() {
  try {
    const response = await fetch(`http://localhost:8080/categorias`);
    const categorias = await response.json();
    const categoriasDropdown = document.getElementById("idCategoria");
    const editarCategoriasDropdown =
      document.getElementById("editarIdCategoria");

    categorias.forEach((categoria) => {
      const option = document.createElement("option");
      option.value = categoria.idCategoria;
      option.textContent = categoria.descricao;
      categoriasDropdown.appendChild(option);
      const editarOption = option.cloneNode(true);
      editarCategoriasDropdown.appendChild(editarOption);
    });
  } catch (error) {
    console.error("Erro ao carregar categorias:", error);
  }
}

async function cadastrarFuncionario(event) {
  event.preventDefault();

  const funcionario = {
    matricula: 0,
    cpf: document.getElementById("cpf").value,
    nome: document.getElementById("nome").value,
    dataAdmissao: new Date().toISOString(),
    funcao: document.getElementById("funcao").value,
    salario: parseFloat(document.getElementById("salario").value),
    cep: document.getElementById("cep").value,
    rua: document.getElementById("rua").value,
    numero: parseInt(document.getElementById("numero").value),
    bairro: document.getElementById("bairro").value,
    cidade: document.getElementById("cidade").value,
    uf: document.getElementById("uf").value,
    status: "A",
    senha: "senhaDefault", // Adicione a lógica para a senha
    lojasCnpj: "12345678901234",
  };

  try {
    const response = await fetch("http://localhost:8080/funcionarios", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(funcionario),
    });

    if (response.ok) {
      const funcionariosResponse = await fetch(
        "http://localhost:8080/funcionarios"
      );
      const funcionarios = await funcionariosResponse.json();
      const ultimoFuncionario = funcionarios[funcionarios.length - 1];
      const matriculaFuncionario = ultimoFuncionario.matricula;

      if (document.getElementById("hasDependente").checked) {
        const dependente = {
          cpfDependente: document.getElementById("cpfDependente").value,
          matriculaFuncionario: matriculaFuncionario,
          nome: document.getElementById("nomeDependente").value,
          dataNascimento: new Date(
            document.getElementById("dataNascimentoDependente").value
          ).toISOString(),
        };

        const dependenteResponse = await fetch(
          "http://localhost:8080/dependentes",
          {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify(dependente),
          }
        );

        if (dependenteResponse.ok) {
          alert("Funcionário e dependente cadastrados com sucesso!");
        } else {
          console.error("Erro ao cadastrar dependente");
        }
      } else {
        alert("Funcionário cadastrado com sucesso!");
      }

      $("#cadastrarFuncionarioModal").modal("hide");
      location.reload();
    } else {
      console.error("Erro ao cadastrar funcionário");
    }
  } catch (error) {
    console.error("Erro ao cadastrar funcionário:", error);
  }
}

async function buscarFuncionario(event) {
  event.preventDefault();

  const matricula = document.getElementById("matriculaBuscar").value;
  globalMatricula = matricula; // Armazenar a matrícula globalmente

  try {
    const response = await fetch(
      `http://localhost:8080/funcionarios/${matricula}`
    );
    if (!response.ok) {
      alert("Funcionário não encontrado.");
      $("#editarFuncionarioModal").modal("hide");
      return;
    }

    const funcionario = await response.json();

    // Preencher formulário de edição com os dados do funcionário
    document.getElementById("editarMatricula").value = funcionario.matricula;
    document.getElementById("editarCpf").value = funcionario.cpf;
    document.getElementById("editarNome").value = funcionario.nome;
    document.getElementById("editarDataAdmissao").value = new Date(
      funcionario.dataAdmissao
    )
      .toISOString()
      .split("T")[0];
    document.getElementById("editarFuncao").value = funcionario.funcao;
    document.getElementById("editarSalario").value = funcionario.salario;
    document.getElementById("editarCep").value = funcionario.cep;
    document.getElementById("editarRua").value = funcionario.rua;
    document.getElementById("editarNumero").value = funcionario.numero;
    document.getElementById("editarBairro").value = funcionario.bairro;
    document.getElementById("editarCidade").value = funcionario.cidade;
    document.getElementById("editarUf").value = funcionario.uf;
    document.getElementById("editarStatus").value = funcionario.status;

    // Verificar e preencher dependentes
    try {
      const dependenteResponse = await fetch(
        `http://localhost:8080/dependentes/${matricula}`
      );
      if (dependenteResponse.ok) {
        const dependente = await dependenteResponse.json();
        if (dependente && dependente.length > 0) {
          const dependenteData = dependente[0];
          document.getElementById("editarCpfDependente").value =
            dependenteData.cpfDependente;
          document.getElementById("editarNomeDependente").value =
            dependenteData.nome;
          document.getElementById("editarDataNascimentoDependente").value =
            new Date(dependenteData.dataNascimento).toISOString().split("T")[0];
          document
            .getElementById("dependenteEditarFields")
            .classList.remove("d-none");

          // Ocultar checkbox de novo dependente se já existir um dependente
          document
            .getElementById("hasDependenteEditarDiv")
            .classList.add("d-none");
        }
      } else {
        // Mostrar checkbox de novo dependente se não existir dependente
        document
          .getElementById("hasDependenteEditarDiv")
          .classList.remove("d-none");
      }
    } catch (dependenteError) {
      if (dependenteError.message.includes("404")) {
        // Funcionário não possui dependentes, mostrar checkbox
        document
          .getElementById("hasDependenteEditarDiv")
          .classList.remove("d-none");
      } else {
        console.error("Erro ao buscar dependente:", dependenteError);
      }
    }

    // Mostrar formulário de edição
    document.getElementById("buscarFuncionarioForm").classList.add("d-none");
    document.getElementById("editarFuncionarioForm").classList.remove("d-none");
  } catch (error) {
    console.error("Erro ao buscar funcionário:", error);
  }
}

async function editarFuncionario(event) {
  event.preventDefault();

  const funcionario = {
    matricula: parseInt(globalMatricula),
    cpf: document.getElementById("editarCpf").value,
    nome: document.getElementById("editarNome").value,
    dataAdmissao: new Date(
      document.getElementById("editarDataAdmissao").value
    ).toISOString(),
    funcao: document.getElementById("editarFuncao").value,
    salario: parseFloat(document.getElementById("editarSalario").value),
    cep: document.getElementById("editarCep").value,
    rua: document.getElementById("editarRua").value,
    numero: parseInt(document.getElementById("editarNumero").value),
    bairro: document.getElementById("editarBairro").value,
    cidade: document.getElementById("editarCidade").value,
    uf: document.getElementById("editarUf").value,
    status: document.getElementById("editarStatus").value,
    senha: "senhaDefault", // Adicione a lógica para a senha
    lojasCnpj: "12345678901234",
  };

  try {
    const response = await fetch(
      `http://localhost:8080/funcionarios/${globalMatricula}`,
      {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(funcionario),
      }
    );

    if (response.ok) {
      // Verificar e atualizar dependente
      const cpfDependente = document.getElementById(
        "editarCpfDependente"
      ).value;
      if (cpfDependente) {
        const dependente = {
          cpfDependente: cpfDependente,
          matriculaFuncionario: globalMatricula,
          nome: document.getElementById("editarNomeDependente").value,
          dataNascimento: new Date(
            document.getElementById("editarDataNascimentoDependente").value
          ).toISOString(),
        };

        const dependenteResponse = await fetch(
          `http://localhost:8080/dependentes/${cpfDependente}/${globalMatricula}`,
          {
            method: "PUT",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify(dependente),
          }
        );

        if (!dependenteResponse.ok) {
          console.error("Erro ao editar dependente");
        }
      } else if (document.getElementById("hasDependenteEditar").checked) {
        const novoDependente = {
          cpfDependente: document.getElementById("cpfDependenteNovo").value,
          matriculaFuncionario: globalMatricula,
          nome: document.getElementById("nomeDependenteNovo").value,
          dataNascimento: new Date(
            document.getElementById("dataNascimentoDependenteNovo").value
          ).toISOString(),
        };

        const novoDependenteResponse = await fetch(
          "http://localhost:8080/dependentes",
          {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify(novoDependente),
          }
        );

        if (!novoDependenteResponse.ok) {
          console.error("Erro ao cadastrar novo dependente");
        }
      }

      console.log("Funcionário editado com sucesso!");
      $("#editarFuncionarioModal").modal("hide");
      location.reload();
    } else {
      const errorText = await response.text();
      console.error("Erro ao editar funcionário:", errorText);
    }
  } catch (error) {
    console.error("Erro ao editar funcionário:", error);
  }
}

async function cadastrarItem(event) {
  event.preventDefault();

  const item = {
    idItem: 0,
    nomeItem: document.getElementById("nomeItem").value,
    descricaoItem: document.getElementById("descricaoItem").value,
    itemAtivo: document.getElementById("itemAtivo").value === "true",
    precoItem: parseFloat(document.getElementById("precoItem").value),
    idCategoria: parseInt(document.getElementById("idCategoria").value),
  };

  try {
    const response = await fetch("http://localhost:8080/itens", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(item),
    });

    if (response.ok) {
      console.log("Item cadastrado com sucesso!");
      $("#cadastrarItemModal").modal("hide");
      location.reload();
    } else {
      console.error("Erro ao cadastrar item");
    }
  } catch (error) {
    console.error("Erro ao cadastrar item:", error);
  }
}

async function buscarItem(event) {
  event.preventDefault();

  const idItem = document.getElementById("idItemBuscar").value;

  try {
    const response = await fetch(`http://localhost:8080/itens/${idItem}`);
    if (!response.ok) {
      alert("Item não encontrado.");
      $("#editarItemModal").modal("hide");
      return;
    }

    const item = await response.json();

    // Preencher formulário de edição com os dados do item
    document.getElementById("editarIdItem").value = item.idItem;
    document.getElementById("editarNomeItem").value = item.nomeItem;
    document.getElementById("editarDescricaoItem").value = item.descricaoItem;
    document.getElementById("editarItemAtivo").value =
      item.itemAtivo.toString();
    document.getElementById("editarPrecoItem").value = item.precoItem;
    document.getElementById("editarIdCategoria").value = item.idCategoria;

    // Mostrar formulário de edição
    document.getElementById("buscarItemForm").classList.add("d-none");
    document.getElementById("editarItemForm").classList.remove("d-none");
  } catch (error) {
    console.error("Erro ao buscar item:", error);
  }
}

async function editarItem(event) {
  event.preventDefault();

  const idItem = document.getElementById("editarIdItem").value;

  const item = {
    idItem: parseInt(idItem),
    nomeItem: document.getElementById("editarNomeItem").value,
    descricaoItem: document.getElementById("editarDescricaoItem").value,
    itemAtivo: document.getElementById("editarItemAtivo").value === "true",
    precoItem: parseFloat(document.getElementById("editarPrecoItem").value),
    idCategoria: parseInt(document.getElementById("editarIdCategoria").value),
  };

  try {
    const response = await fetch(`http://localhost:8080/itens/${idItem}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(item),
    });

    if (response.ok) {
      console.log("Item editado com sucesso!");
      $("#editarItemModal").modal("hide");
      location.reload();
    } else {
      console.error("Erro ao editar item");
    }
  } catch (error) {
    console.error("Erro ao editar item:", error);
  }
}

async function loadClientes() {
  try {
    const response = await fetch(`http://localhost:8080/clientes`);
    const clientes = await response.json();
    const clientesTabelaCorpo = document.getElementById("clientesTabelaCorpo");
    clientesTabelaCorpo.innerHTML = "";

    clientes.forEach((cliente) => {
      const row = document.createElement("tr");
      row.innerHTML = `
              <td>${cliente.idCliente}</td>
              <td>${cliente.nome}</td>
              <td>${cliente.sobrenome}</td>
              <td>${cliente.telefone}</td>
              <td>${cliente.email}</td>
              <td>${new Date(cliente.dataCadastro).toLocaleDateString()}</td>
          `;
      row.addEventListener("click", () => {
        document.getElementById(
          "enderecoCliente"
        ).innerText = `Endereço de ${cliente.nome} ${cliente.sobrenome}`;
        document.getElementById(
          "enderecoClienteDetalhes"
        ).innerText = `${cliente.rua}, ${cliente.numero}, ${cliente.bairro}, ${cliente.cidade} - ${cliente.uf}`;
        document.getElementById("complementoCliente").innerText =
          cliente.complemento ? `Complemento: ${cliente.complemento}` : "";
        document.getElementById("referenciaCliente").innerText =
          cliente.referencia
            ? `Ponto de Referência: ${cliente.referencia}`
            : "";
      });
      clientesTabelaCorpo.appendChild(row);
    });
  } catch (error) {
    console.error("Erro ao carregar clientes:", error);
  }
}

async function preencherEndereco(event) {
  const cep = event.target.value;

  if (cep.length !== 8) {
    document.getElementById("cepInvalido").classList.remove("d-none");
    return;
  }

  try {
    const response = await fetch(`https://viacep.com.br/ws/${cep}/json/`);
    const data = await response.json();

    if (data.erro) {
      document.getElementById("cepInvalido").classList.remove("d-none");
      return;
    }

    document.getElementById("cepInvalido").classList.add("d-none");
    document.getElementById("rua").value = data.logradouro;
    document.getElementById("bairro").value = data.bairro;
    document.getElementById("cidade").value = data.localidade;
    document.getElementById("uf").value = data.uf;
  } catch (error) {
    console.error("Erro ao buscar endereço pelo CEP:", error);
  }
}

async function preencherEnderecoEditar(event) {
  const cep = event.target.value;

  if (cep.length !== 8) {
    document.getElementById("editarCepInvalido").classList.remove("d-none");
    return;
  }

  try {
    const response = await fetch(`https://viacep.com.br/ws/${cep}/json/`);
    const data = await response.json();

    if (data.erro) {
      document.getElementById("editarCepInvalido").classList.remove("d-none");
      return;
    }

    document.getElementById("editarCepInvalido").classList.add("d-none");
    document.getElementById("editarRua").value = data.logradouro;
    document.getElementById("editarBairro").value = data.bairro;
    document.getElementById("editarCidade").value = data.localidade;
    document.getElementById("editarUf").value = data.uf;
  } catch (error) {
    console.error("Erro ao buscar endereço pelo CEP:", error);
  }
}
