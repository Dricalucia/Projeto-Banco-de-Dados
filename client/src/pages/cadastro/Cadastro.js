// Passar para segunda parte do formulario
document.addEventListener('DOMContentLoaded', function () {
    const proximoButton = document.getElementById('proximoButton');
    const cadastrarButton = document.getElementById('cadastrarButton');
    const parte1 = document.getElementById('parte1');
    const parte2 = document.getElementById('parte2');
    const nome = document.getElementById('nome');
    const sobrenome = document.getElementById('sobrenome');
    const emailCadastro = document.getElementById('emailCadastro');
    const senhaCadastro = document.getElementById('senhaCadastro');
    const telefone = document.getElementById('telefone');
    const form = document.querySelector('.needs-validation');

    proximoButton.addEventListener('click', function (event) {
        // Força a validação dos campos específicos da primeira parte do formulário
        let isValid = nome.checkValidity() && sobrenome.checkValidity() &&
                      emailCadastro.checkValidity() && senhaCadastro.checkValidity() &&
                      telefone.checkValidity();

        if (!isValid) {
            event.preventDefault();
            event.stopPropagation();
            // Adiciona a classe para mostrar feedback de validação do Bootstrap para cada campo da primeira parte
            [nome, sobrenome, emailCadastro, senhaCadastro, telefone].forEach(field => {
                if (!field.checkValidity()) {
                    field.classList.add('is-invalid');
                }
            });
            form.classList.add('was-validated');
        } else {
            // Se os campos são válidos, vai para a segunda parte
            parte1.style.display = 'none';
            parte2.style.display = 'block';
            proximoButton.style.display = 'none';
            cadastrarButton.style.display = 'block';
        }
    });

    form.addEventListener('submit', function (event) {
        if (!form.checkValidity()) {
            event.preventDefault();
            event.stopPropagation();
        }
        form.classList.add('was-validated'); // Assegura que a validação do formulário é mostrada
    });
});






// Incorporação do Script de Exeplo do Bootstrap com Validação de CEP e Preenchimento de Campos de Endereço de forma Assíncrona
document.addEventListener('DOMContentLoaded', () => {
    'use strict';

    // Seletores de elementos do DOM
    const cep = document.querySelector("#CEPCadastro");
    const endereco = document.querySelector("#enderecoCadastro");
    const bairro = document.querySelector("#bairroCadastro");
    const cidade = document.querySelector("#cidadeCadastro");
    const estado = document.querySelector("#estadoCadastro");
    const forms = document.querySelectorAll('.needs-validation');

    // Expressões regulares para validar o CEP
    const validarCep = /^[0-9]+$/;
    const cepValido = /^[0-9]{8}$/;

    forms.forEach(form => {
        form.addEventListener('submit', event => {
            let formIsValid = form.checkValidity();

            // Verifica primeiro a validade do formulário pelo Bootstrap
            if (!formIsValid) {
                event.preventDefault();
                event.stopPropagation();
                form.classList.add('was-validated'); // Isso vai garantir que o feedback de validação do Bootstrap vai ser exibido
            }

            // Verificação específica para o campo CEP
            if (!validarCep.test(cep.value) || !cepValido.test(cep.value)) {
                event.preventDefault();
                event.stopPropagation();
                cep.classList.add('is-invalid');
                cep.classList.remove('is-valid');
                cep.nextElementSibling.textContent = 'CEP inválido!';
                formIsValid = false; // Atualiza o status de validade do formulário
            }

            if (!formIsValid) {
                event.preventDefault();
                event.stopPropagation();
            }
        });

        cep.addEventListener('focusout', async () => {
            try {
                if (!validarCep.test(cep.value) || !cepValido.test(cep.value)) {
                    throw new Error('CEP inválido!');
                }

                const resposta = await fetch(`https://viacep.com.br/ws/${cep.value}/json/`);
                if (!resposta.ok) throw new Error('Erro ao buscar CEP');

                const dadosCep = await resposta.json();
                endereco.value = dadosCep.logradouro;
                bairro.value = dadosCep.bairro;
                cidade.value = dadosCep.localidade;
                estado.value = dadosCep.uf;
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
});
