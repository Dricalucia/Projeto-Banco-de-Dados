document.addEventListener("DOMContentLoaded", function () {
  "use strict";

  const emailInput = document.getElementById("email");
  const senhaInput = document.getElementById("senha");
  const form = document.getElementById("login-form");

  form.addEventListener("submit", async function (event) {
    event.preventDefault();
    if (!this.checkValidity()) {
      this.classList.add("was-validated");
      return;
    }

    const email = emailInput.value;
    const senha = senhaInput.value;
    const url = `http://localhost:8080/clientes/${email}/${senha}`;

    try {
      const response = await fetch(url, { method: "GET" });
      if (response.ok) {
        const user = await response.json();
        console.log('Login successful, user:', user);
        // Salva o usuário inteiro no sessionStorage pra ser usado em outras páginas
        sessionStorage.setItem('loggedInUser', JSON.stringify(user)); 
        const redirectUrl = "/Projeto-Banco-de-Dados/client/src/pages/cliente/CardapioCliente/CardapioCliente.html";
        window.location.href = redirectUrl;
      } else {
        senhaInput.classList.add("is-invalid");
        senhaInput.nextElementSibling.textContent = "Senha ou e-mail incorreto(a).";
      }
    } catch (error) {
      console.error("Erro na requisição:", error);
      alert("Erro ao tentar fazer login. Por favor, tente novamente mais tarde.");
    }
  });
});
