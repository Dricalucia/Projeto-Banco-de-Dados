document.addEventListener('DOMContentLoaded', () => {
    'use strict';

    // Seletores de elementos do DOM para o endereço
    const cep = document.querySelector("#CEPCadastro");
    const endereco = document.querySelector("#enderecoCadastro");
    const bairro = document.querySelector("#bairroCadastro");
    const cidade = document.querySelector("#cidadeCadastro");
    const estado = document.querySelector("#estadoCadastro");
    const message = document.querySelector("#message"); // Supondo que você tenha um elemento para mensagens de erro

    // Adiciona validação de formulários do Bootstrap
    const forms = document.querySelectorAll('.needs-validation');
    Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }
            form.classList.add('was-validated');
        }, false);
    });

    // Evento 'focusout' para o CEP
    cep.addEventListener('focusout', async () => {
        try {
            const validarCep = /^[0-9]+$/;
            const cepValido = /^[0-9]{8}$/;

            if (!validarCep.test(cep.value) || !cepValido.test(cep.value)) {
                throw { cepInvalido: 'CEP inválido!' };
            }

            const resposta = await fetch(`https://viacep.com.br/ws/${cep.value}/json/`);

            if (!resposta.ok) {
                throw await resposta.json();
            }

            const dadosCep = await resposta.json();
            endereco.value = dadosCep.logradouro;
            bairro.value = dadosCep.bairro;
            cidade.value = dadosCep.localidade;
            estado.value = dadosCep.uf;
        } catch (error) {
            if (error?.cepInvalido) {
                message.textContent = error.cepInvalido;
                setTimeout(() => {
                    message.textContent = '';
                }, 5000);
                cep.value = '';
                endereco.value = '';
                bairro.value = '';
                cidade.value = '';
                estado.value = '';
            }
        }
    });
});
