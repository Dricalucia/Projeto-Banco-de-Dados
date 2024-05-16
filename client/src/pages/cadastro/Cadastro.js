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
