window.addEventListener("DOMContentLoaded", (event) => {
  const headerContainer = document.getElementById("header-container");
  fetch("/Projeto-Banco-de-Dados/client/src/utils/HeaderCliente.html")
    .then((response) => response.text())
    .then((data) => {
      headerContainer.innerHTML = data;
    });

  // Recupera o usuário logado do sessionStorage
  const loggedInUser = JSON.parse(sessionStorage.getItem("loggedInUser"));
  if (!loggedInUser) {
    console.error("Nenhum usuário logado encontrado");
  } else {
    console.log("Usuário logado encontrado:", loggedInUser);
  }

  document
    .getElementById("alterarEmailForm")
    .addEventListener("submit", alterarEmail);
  document
    .getElementById("alterarSenhaForm")
    .addEventListener("submit", alterarSenha);
  document
    .getElementById("alterarEnderecoForm")
    .addEventListener("submit", alterarEndereco);
  document
    .getElementById("excluirContaForm")
    .addEventListener("submit", excluirConta);

  document
    .getElementById("confirmarLogoutButton")
    .addEventListener("click", logout);

  document
    .getElementById("confirmarExclusaoButton")
    .addEventListener("click", deleteAccountAndLogout);

  // Adiciona evento de focusout para o campo de CEP
  const novoCEP = document.getElementById("novoCEP");
  novoCEP.addEventListener("focusout", buscarCEP);
});

function showAlert(message, type) {
  const alertContainer = document.getElementById("alert-container");
  const alert = document.createElement("div");
  alert.className = `alert alert-${type} alert-dismissible fade show`;
  alert.role = "alert";
  alert.innerHTML = `
            ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        `;
  alertContainer.appendChild(alert);

  setTimeout(() => {
    alert.classList.remove("show");
  }, 5000);
}

// Função para buscar informações de CEP
async function buscarCEP() {
  const novoCEP = document.getElementById("novoCEP");
  const novaRua = document.getElementById("novaRua");
  const novoBairro = document.getElementById("novoBairro");
  const novaCidade = document.getElementById("novaCidade");
  const novoEstado = document.getElementById("novoEstado");

  const validarCep = /^[0-9]+$/;
  const cepValido = /^[0-9]{8}$/;

  try {
    if (!validarCep.test(novoCEP.value) || !cepValido.test(novoCEP.value)) {
      throw new Error("CEP inválido!");
    }

    const resposta = await fetch(
      `https://viacep.com.br/ws/${novoCEP.value}/json/`
    );
    if (!resposta.ok) throw new Error("Erro ao buscar CEP");

    const dadosCep = await resposta.json();
    if (dadosCep.erro) {
      throw new Error("CEP não encontrado!");
    }

    novaRua.value = dadosCep.logradouro || "";
    novoBairro.value = dadosCep.bairro || "";
    novaCidade.value = dadosCep.localidade || "";
    novoEstado.value = dadosCep.uf || "";
    novoCEP.classList.remove("is-invalid");
    novoCEP.classList.add("is-valid");
  } catch (error) {
    novoCEP.classList.add("is-invalid");
    novoCEP.classList.remove("is-valid");
    novoCEP.nextElementSibling.textContent = error.message;

    novaRua.value = "";
    novoBairro.value = "";
    novaCidade.value = "";
    novoEstado.value = "";
  }
}

// Atualiza o e-mail do cliente
function alterarEmail(event) {
  event.preventDefault();
  const emailAtual = document.getElementById("emailAtual").value;
  const novoEmail = document.getElementById("novoEmail").value;
  const senha = document.getElementById("senhaEmail").value;

  const loggedInUser = JSON.parse(sessionStorage.getItem("loggedInUser"));
  if (!loggedInUser) {
    console.error("Nenhum usuário logado encontrado");
    return;
  }

  // Busca todos os clientes para ver se o novo e-mail existe no banco
  fetch(`http://localhost:8080/clientes`)
    .then((response) => response.json())
    .then((clientes) => {
      const emailEmUso = clientes.some(
        (cliente) => cliente.email === novoEmail
      );
      if (emailEmUso) {
        showAlert(
          "O e-mail já está em uso. Por favor, escolha outro.",
          "danger"
        );
        throw new Error("E-mail já está em uso");
      }
      return fetch(`http://localhost:8080/clientes/${emailAtual}/${senha}`);
    })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Verificação falhou");
      }
      return response.json();
    })
    .then((cliente) => {
      return fetch(`http://localhost:8080/clientes/${cliente.idCliente}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ ...cliente, email: novoEmail }),
      });
    })
    .then((response) => {
      if (response.ok) {
        $("#alterarEmailModal").modal("hide");
        showAlert("E-mail alterado com sucesso!", "success");
        setTimeout(() => {
          $("#avisoLogoutModal").modal("show");
          document
            .getElementById("confirmarLogoutButton")
            .addEventListener("click", logout);
        }, 2000); // Tempo do alert antes de fazer logout
      } else {
        alert("Erro ao alterar e-mail. Por favor, tente novamente.");
      }
    })
    .catch((error) => {
      console.error("Erro ao alterar e-mail:", error);
      if (error.message !== "E-mail já está em uso") {
        alert("Erro ao alterar e-mail. Por favor, tente novamente.");
      }
    });
}

// Atualiza a senha do cliente
function alterarSenha(event) {
  event.preventDefault();
  const email = document.getElementById("emailSenha").value;
  const senhaAtual = document.getElementById("senhaAtual").value;
  const novaSenha = document.getElementById("novaSenha").value;

  const loggedInUser = JSON.parse(sessionStorage.getItem("loggedInUser"));
  if (!loggedInUser) {
    console.error("Nenhum usuário logado encontrado");
    return;
  }

  fetch(`http://localhost:8080/clientes/${email}/${senhaAtual}`)
    .then((response) => {
      if (response.ok) {
        return response.json();
      } else {
        throw new Error("Verificação falhou");
      }
    })
    .then((cliente) => {
      return fetch(`http://localhost:8080/clientes/${cliente.idCliente}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ ...cliente, senha: novaSenha }),
      });
    })
    .then((response) => {
      if (response.ok) {
        $("#alterarSenhaModal").modal("hide");
        showAlert("Senha alterada com sucesso!", "success");
        setTimeout(() => {
          $("#avisoLogoutModal").modal("show");
          document
            .getElementById("confirmarLogoutButton")
            .addEventListener("click", logout);
        }, 2000); // Tempo do alert antes de fazer logout
      } else {
        alert("Erro ao alterar senha. Por favor, tente novamente.");
      }
    })
    .catch((error) => {
      console.error("Erro ao alterar senha:", error);
      alert("Erro ao alterar senha. Por favor, tente novamente.");
    });
}

// Atualiza o endereço de entrega do cliente
function alterarEndereco(event) {
  event.preventDefault();
  const novoCEP = document.getElementById("novoCEP").value;
  const novaRua = document.getElementById("novaRua").value;
  const novoNumero = document.getElementById("novoNumero").value;
  const novoBairro = document.getElementById("novoBairro").value;
  const novoComplemento = document.getElementById("novoComplemento").value;
  const novoPontoReferencia = document.getElementById(
    "novoPontoReferencia"
  ).value;

  const loggedInUser = JSON.parse(sessionStorage.getItem("loggedInUser"));
  if (!loggedInUser) {
    console.error("Nenhum usuário logado encontrado");
    return;
  }

  fetch(`http://localhost:8080/clientes/${loggedInUser.idCliente}`)
    .then((response) => response.json())
    .then((cliente) => {
      return fetch(`http://localhost:8080/clientes/${cliente.idCliente}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          ...cliente,
          cep: novoCEP,
          rua: novaRua,
          numero: novoNumero,
          bairro: novoBairro,
          complemento: novoComplemento,
          pontoReferencia: novoPontoReferencia,
        }),
      });
    })
    .then((response) => {
      if (response.ok) {
        $("#alterarEnderecoModal").modal("hide");
        showAlert("Endereço atualizado com sucesso.", "success");
      } else {
        alert("Erro ao alterar endereço. Por favor, tente novamente.");
      }
    })
    .catch((error) => {
      console.error("Erro ao alterar endereço:", error);
      alert("Erro ao alterar endereço. Por favor, tente novamente.");
    });
}

function excluirConta(event) {
  event.preventDefault();
  const email = document.getElementById("emailExcluir").value;
  const senha = document.getElementById("senhaExcluir").value;

  const loggedInUser = JSON.parse(sessionStorage.getItem("loggedInUser"));
  if (!loggedInUser) {
    console.error("Nenhum usuário logado encontrado");
    return;
  }

  fetch(`http://localhost:8080/clientes/${email}/${senha}`)
    .then((response) => {
      if (response.ok) {
        return response.json();
      } else {
        throw new Error("Verificação falhou");
      }
    })
    .then((cliente) => {
      // Gerar uma senha aleatória
      const novaSenha = Math.random().toString(36).slice(-8);

      return fetch(`http://localhost:8080/clientes/${cliente.idCliente}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ ...cliente, senha: novaSenha }),
      });
    })
    .then((response) => {
      if (response.ok) {
        $("#excluirContaModal").modal("hide");
        setTimeout(() => {
          $("#confirmacaoExclusaoModal").modal("show");
          document
            .getElementById("confirmarExclusaoButton")
            .addEventListener("click", logout);
        }, 2000); // Tempo do alert antes de fazer logout
      } else {
        alert("Erro ao excluir conta. Por favor, tente novamente.");
      }
    })
    .catch((error) => {
      console.error("Erro ao excluir conta:", error);
      alert("Erro ao excluir conta. Por favor, tente novamente.");
    });
}

// Deslogar o usuário e redirecionar para a página de login
function logout() {
  sessionStorage.removeItem("loggedInUser");
  window.location.href =
    "/Projeto-Banco-de-Dados/client/src/pages/login/cliente/LoginCliente.html";
}

function deleteAccountAndLogout() {
  logout();
}
