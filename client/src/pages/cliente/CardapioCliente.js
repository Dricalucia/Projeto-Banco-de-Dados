window.addEventListener('DOMContentLoaded', (event) => {
    const headerContainer = document.getElementById('header-container');
    fetch('../../utils/HeaderCliente.html')
        .then(response => response.text())
        .then(data => {
            headerContainer.innerHTML = data;
        });
});
