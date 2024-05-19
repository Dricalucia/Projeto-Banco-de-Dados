window.addEventListener('DOMContentLoaded', (event) => {
    const headerContainer = document.getElementById('header-container');
    fetch('../../utils/HeaderFuncionario.html')
        .then(response => response.text())
        .then(data => {
            headerContainer.innerHTML = data;
        });
});
