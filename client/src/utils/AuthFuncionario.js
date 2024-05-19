document.addEventListener("DOMContentLoaded", function() {
    // Verificar se a sessão do Funcionario está ativa
    if (!sessionStorage.getItem("userToken")) {
        // Se não há token de sessão, redirecionar para a página de login
        window.location.href = '../pages/login/funcionario/LoginFuncionario.html';
    }

    // Adicionar funcionalidade de logout para Funcionario
    const logoutButton = document.getElementById('logoutButton');
    if (logoutButton) {
        logoutButton.addEventListener('click', function() {
            sessionStorage.removeItem('userToken');
            window.location.href = '../pages/login/funcionario/LoginFuncionario.html';
        });
    }
});
