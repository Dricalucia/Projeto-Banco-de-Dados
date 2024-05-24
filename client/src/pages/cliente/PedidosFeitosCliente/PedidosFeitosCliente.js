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
});
