document.addEventListener('DOMContentLoaded', function () {
    const proximoButton = document.getElementById('proximoButton');
    const cadastrarButton = document.getElementById('cadastrarButton');
    const parte1 = document.getElementById('parte1');
    const parte2 = document.getElementById('parte2');
    const form = document.getElementById('cadastro-form');

    proximoButton.addEventListener('click', function (event) {
        let isValid = true;
        const fieldsParte1 = [document.getElementById('nome'), document.getElementById('sobrenome'),
                              document.getElementById('email'), document.getElementById('senha'),
                              document.getElementById('telefone')];

        fieldsParte1.forEach(field => {
            if (!field.checkValidity()) {
                isValid = false;
                field.classList.add('is-invalid');
            } else {
                field.classList.remove('is-invalid');
            }
        });

        if (isValid) {
            parte1.style.display = 'none';
            parte2.style.display = 'block';
            proximoButton.style.display = 'none';
            cadastrarButton.style.display = 'block';
        } else {
            form.classList.add('was-validated');
        }
    });

    form.addEventListener('submit', async function (event) {
        event.preventDefault();
        let isValid = true;
        const fieldsParte2 = [document.getElementById('CEP'), document.getElementById('endereco'),
                              document.getElementById('numero'), document.getElementById('bairro'),
                              document.getElementById('cidade'), document.getElementById('estado')];

        fieldsParte2.forEach(field => {
            if (!field.checkValidity()) {
                isValid = false;
                field.classList.add('is-invalid');
            } else {
                field.classList.remove('is-invalid');
            }
        });

        if (isValid) {
            const clienteData = {
                nome: document.getElementById('nome').value,
                sobrenome: document.getElementById('sobrenome').value,
                email: document.getElementById('email').value,
                senha: document.getElementById('senha').value,
                telefone: document.getElementById('telefone').value,
                rua: document.getElementById('endereco').value,
                numero: document.getElementById('numero').value,
                complemento: document.getElementById('complemento').value,
                bairro: document.getElementById('bairro').value,
                cidade: document.getElementById('cidade').value,
                uf: document.getElementById('estado').value,
                pontoReferencia: document.getElementById('pontoReferencia').value,
                obeservacao: 'Observação N/A',
                dataCadastro: new Date().toISOString()
            };

            try {
                const response = await fetch('http://localhost:8080/clientes', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(clienteData)
                });

                if (response.ok) {
                    $('#modalConfirmacao').modal('show');
                } else {
                    throw new Error('Erro ao cadastrar cliente.');
                }
            } catch (error) {
                alert(error.message);
            }
        } else {
            form.classList.add('was-validated');
        }
    });

    // Vai mandar o usuario para a página de login quando o usuário clicar em OK no modal
    document.getElementById('irParaLogin').addEventListener('click', function() {
        window.location.href = '../login/cliente/LoginCliente.html';
    });

    // Função para buscar informações de CEP
    const cep = document.getElementById('CEP');
    const endereco = document.getElementById('endereco');
    const bairro = document.getElementById('bairro');
    const cidade = document.getElementById('cidade');
    const estado = document.getElementById('estado');

    cep.addEventListener('focusout', async () => {
        const validarCep = /^[0-9]+$/;
        const cepValido = /^[0-9]{8}$/;

        try {
            if (!validarCep.test(cep.value) || !cepValido.test(cep.value)) {
                throw new Error('CEP inválido!');
            }

            const resposta = await fetch(`https://viacep.com.br/ws/${cep.value}/json/`);
            if (!resposta.ok) throw new Error('Erro ao buscar CEP');

            const dadosCep = await resposta.json();
            if (dadosCep.erro) {
                throw new Error('CEP não encontrado!');
            }

            endereco.value = dadosCep.logradouro || '';
            bairro.value = dadosCep.bairro || '';
            cidade.value = dadosCep.localidade || '';
            estado.value = dadosCep.uf || '';
            cep.classList.remove('is-invalid');
            cep.classList.add('is-valid');
        } catch (error) {
            cep.classList.add('is-invalid');
            cep.classList.remove('is-valid');
            cep.nextElementSibling.textContent = error.message;

            endereco.value = '';
            bairro.value = '';
            cidade.value = '';
            estado.value = '';
        }
    });
});