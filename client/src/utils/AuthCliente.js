document.addEventListener("DOMContentLoaded", function () {
  // Verificar se a sessão do cliente está ativa
  if (!sessionStorage.getItem("loggedInUser")) {
    // Se não há usuário logado, redirecionar para a página de login
    window.location.href =
      "/Projeto-Banco-de-Dados/client/src/pages/login/cliente/LoginCliente.html";
  }

  // Adicionar funcionalidade de logout para cliente
  document.addEventListener("headerLoaded", function () {
    const logoutButton = document.getElementById("logoutButton");
    if (logoutButton) {
      logoutButton.addEventListener("click", function () {
        sessionStorage.removeItem("loggedInUser");
        window.location.href =
          "/Projeto-Banco-de-Dados/client/src/pages/login/cliente/LoginCliente.html";
      });
    }

    // Definir o link ativo com base na URL atual
    setNavLinkActive();
  });
});

function setNavLinkActive() {
  const path = window.location.pathname;
  console.log("Current path:", path);
  const navLinks = {
    "/Projeto-Banco-de-Dados/client/src/pages/cliente/CardapioCliente/CardapioCliente.html":
      "navCardapio",
    "/Projeto-Banco-de-Dados/client/src/pages/cliente/PedidosFeitosCliente/PedidosFeitosCliente.html":
      "navPedidos",
    "/Projeto-Banco-de-Dados/client/src/pages/cliente/SacolaCliente/SacolaCliente.html":
      "navSacola",
    "/Projeto-Banco-de-Dados/client/src/pages/cliente/configuracoesDeContaCliente/ConfiguracoesDeContaCliente.html":
      "navConfiguracoes",
  };
  const activeLinkId = navLinks[path];

  console.log("Active link ID:", activeLinkId);

  if (activeLinkId) {
    const activeLink = document.getElementById(activeLinkId);
    if (activeLink) {
      activeLink.classList.add("active");
      activeLink.setAttribute("aria-current", "page");
    } else {
      console.error(`Element with ID ${activeLinkId} not found.`);
    }
  } else {
    console.error(`Path ${path} does not match any known navigation link.`);
  }
}
