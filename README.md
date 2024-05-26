# Projeto de Banco de Dadoss

Este é o repositório para disciplina de Banco de Dados e tem como objetivo o desenvolvimento de um sistema em Java utilizando SQL. 

Para gerenciamento de banco de dados será  utilizado o MySQL e o framework escolhido para uso foi é Dbeaver. Para o desenvolvimento das modelagens conceitual e lógica, foi escolhido o programa brModelo.

Este repositório conterá o minimundo do negócio escolhido, a modelagem dos dados e o aplicativo/site do projeto..

![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=YELLOW&style=for-the-badge)

# Requisitos

1. Relatório contendo:
- Descrição das etapas do processo de desenvolvimento
- Modelo conceitual
- Modelo lógico relacional
- Script de criação
- Script de povoamento
- Consultas SQL

2. Apresentação -> apresentar os requisitos com:
- slides e/ou interação interface/banco 


# Tecnologias utilizadas e pré-requisitos
![](https://img.shields.io/badge/HTML-239120?style=for-the-badge&logo=html5&logoColor=white) 	![](https://img.shields.io/badge/CSS-239120?&style=for-the-badge&logo=css3&logoColor=white) ![](https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white)

![](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white) ![](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)

# Instalação e Criação de Ambiente Virtual

- Clone este repositório remoto no seu diretório local;
```shell
instrução
```
-  Utilize os seguintes comandos no terminal:

- Windows
```shell
instrução
```
- MacOS e Linux
```shell
instrução
```
Após a conclusão, execute também o seguinte comando a partir da mesma pasta.

- Windows

```shell
instrução
```
- MacOS e Linux
```shell
instrução
```
- Rodar o projeto [Windows e MacOS e Linux]

```shell
finstrução
```
- Abra no seu navegador o servidor local em **localhost:5000**
- Ou se preferir, no seu terminal vá até o link do seu servidor local e pressione a tecla Ctrl + click mouse, que o projeto rodará na porta http://127.0.0.1:5000


# Estrutura do diretório

```shell
├── .idea
│    ├── .gitignore
│    ├── BD--Projeto.iml
│    ├── misc.xml
│    ├── modules.xml
│    └── vcs.xml
├── .vscode
│    └── settings.json
├── client/src
│   ├── pages
│   │   ├── cadastro
│   │   │   ├── Cadastro.js
│   │   │   ├── CadastroPage.html
│   │   │   └── style.css
│   │   ├── cliente
│   │   │   ├── CardapioCliente
│   │   │   │   ├── CardapioCliente.html
│   │   │   │   ├── CardapioCliente.js
│   │   │   │   └── style.css
│   │   │   ├── PedidosFeitosCliente
│   │   │   │   ├── PedidosFeitosCliente.html
│   │   │   │   ├── PedidosFeitosCliente.js
│   │   │   │   └── style.css
│   │   │   ├── SacolaCliente
│   │   │   │   ├── SacolaCliente.html
│   │   │   │   ├── SacolaCliente.js
│   │   │   │   └── style.css
│   │   │   └── configuracoesDeContaCliente
│   │   │       ├── ConfiguracoesDeContaCliente.html
│   │   │       ├── ConfiguracoesDeContaCliente.js
│   │   │       └── style.css
│   │   │── funcionario
│   │   │     ├── AreaDoCliente
│   │   │     │   ├── AreaDoCliente.html
│   │   │     │   └── AreaDoCliente.js
│   │   │     ├── AreaDoFuncionario
│   │   │     │   ├── AreaDoFuncionario.html
│   │   │     │   └── AreaDoFuncionario.js
│   │   │     ├── PedidosAceitos
│   │   │     │   ├── PedidosAceitos.html
│   │   │     │   └── PedidosAceitos.js
│   │   │     ├── PedidosEmAberto
│   │   │     │   ├── PedidosEmAberto.html
│   │   │     │   └── PedidosEmAberto.js
│   │   │     ├── configuracoes
│   │   │     │   ├── AdicionarFuncionario.js
│   │   │     │   ├── AdicionarProduto.js
│   │   │     │   ├── Configuracoes.html
│   │   │     │   ├── Configuracoes.js
│   │   │     │   ├── Dashboard.js
│   │   │     │   ├── EditarFuncionario.js
│   │   │     │   └── EditarProduto.js
│   │   │     └── style.css
│   │   └── login
│   │        ├── cliente
│   │        │   ├── LoginCliente.html
│   │        │   ├── LoginCliente.js
│   │        │   └── style.css
│   │        └── funcionario
│   │            ├── LoginFuncionario.html
│   │            ├── LoginFuncionario.js
│   │            └── style.css
│   └── utils
│       ├── AuthCliente.js
│       ├── AuthFuncionario.js
│       ├── HeaderCliente.html
│       └── HeaderFuncionario.html
├── db
│   ├── ModelagemBD
│   │   ├── Projeto_BD_ModeloConceitual.brM3
│   │   ├── Projeto_BD_ModeloConceitual.png
│   │   ├── Projeto_BD_ModeloFisico.png
│   │   ├── Projeto_BD_ModeloLogico.brM3
│   │   └── Projeto_BD_ModeloLogico.png
│   ├── Scripts
│   │   ├── BD Projeto (CRUD).txt
│   │   ├── ScriptPopularTabelas.sql
│   │   ├── ScriptPortalDoTemaki.sql
│   │   └── ScriptSQLAvancado.sql
│   ├── Minimundo.pdf
│   └── Swagger UI.pdf
├── img
│   ├── Portal_Temaki.png
│   └── logo-provisoria.png
├── server
│   ├── mvn/wrapper
│   │   ├── maven-wrapper.jar
│   │   └── maven-wrapper.properties
│   ├── src
│   │   ├── main
│   │   │   ├── test/java/com/cesar/portaltemaki
│   │   │   │   ├── config
│   │   │   │   │   └── CorsConfig.java
│   │   │   │   ├── controller
│   │   │   │   │   ├── CategoriaController.java
│   │   │   │   │   ├── ClienteController.java
│   │   │   │   │   ├── DependenteController.java
│   │   │   │   │   ├── FuncionarioController.java
│   │   │   │   │   ├── ItemController.java
│   │   │   │   │   ├── ItensPedidoController.java
│   │   │   │   │   ├── LojaController.java
│   │   │   │   │   ├── PedidoClienteController.java
│   │   │   │   │   ├── PedidoController.java
│   │   │   │   │   └── PromocaoController.java
│   │   │   │   ├── model
│   │   │   │   │   ├── Categoria.java
│   │   │   │   │   ├── Cliente.java
│   │   │   │   │   ├── Dependente.java
│   │   │   │   │   ├── Funcionario.java
│   │   │   │   │   ├── Item.java
│   │   │   │   │   ├── ItensPedido.java
│   │   │   │   │   ├── Loja.java
│   │   │   │   │   ├── Pedido.java
│   │   │   │   │   ├── PedidoCliente.java
│   │   │   │   │   └── Promocao.java
│   │   │   │   ├── repository
│   │   │   │   │   ├── CategoriaRepository.java
│   │   │   │   │   ├── ClienteRepository.java
│   │   │   │   │   ├── DependenteRepository.java
│   │   │   │   │   ├── FuncionarioRepository.java
│   │   │   │   │   ├── ItemRepository.java
│   │   │   │   │   ├── ItensPedidoRepository.java
│   │   │   │   │   ├── LojaRepository.java
│   │   │   │   │   ├── PedidoClienteRepository.java
│   │   │   │   │   ├── PedidoRepository.java
│   │   │   │   │   └── PromocaoRepository.java
│   │   │   │   ├── service
│   │   │   │   │   ├── CategoriaService.java
│   │   │   │   │   ├── CategoriaServiceImpl.java
│   │   │   │   │   ├── ClienteService.java
│   │   │   │   │   ├── ClienteServiceImpl.java
│   │   │   │   │   ├── DependenteService.java
│   │   │   │   │   ├── DependenteServiceImpl.java
│   │   │   │   │   ├── FuncionarioService.java
│   │   │   │   │   ├── FuncionarioServiceImpl.java
│   │   │   │   │   ├── ItemService.java
│   │   │   │   │   ├── ItemServiceImpl.java
│   │   │   │   │   ├── ItensPedidoService.java
│   │   │   │   │   ├── ItensPedidoServiceImpl.java
│   │   │   │   │   ├── LojaService.java
│   │   │   │   │   ├── LojaServiceImpl.java
│   │   │   │   │   ├── PedidoClienteService.java
│   │   │   │   │   ├── PedidoClienteServiceImpl.java
│   │   │   │   │   ├── PedidoService.java
│   │   │   │   │   ├── PedidoServiceImpl.java
│   │   │   │   │   ├── PromocaoService.java
│   │   │   │   │   └── PromocaoServiceImpl.java
│   │   │   │   └── PortalTemakiApplication.java
│   │   │   └── resources
│   │   │       └── application.properties
│   │   └── test/java/com/cesar/portaltemaki
│   ├── target
│   │   ├── classes
│   │   │   ├── com/cesar/portaltemaki
│   │   │   │   ├── config
│   │   │   │   │   ├── CorsConfig$1.class
│   │   │   │   │   └── CorsConfig.class
│   │   │   │   ├── controller
│   │   │   │   │   ├── CategoriaController.class
│   │   │   │   │   ├── ClienteController.class
│   │   │   │   │   ├── DependenteController.class
│   │   │   │   │   ├── FuncionarioController.class
│   │   │   │   │   ├── ItemController.class
│   │   │   │   │   ├── ItensPedidoController.class
│   │   │   │   │   ├── LojaController.class
│   │   │   │   │   ├── PedidoClienteController.class
│   │   │   │   │   ├── PedidoController.class
│   │   │   │   │   └── PromocaoController.class
│   │   │   │   ├── model
│   │   │   │   │   ├── Categoria.class
│   │   │   │   │   ├── Cliente.class
│   │   │   │   │   ├── Dependente.class
│   │   │   │   │   ├── Funcionario.class
│   │   │   │   │   ├── Item.class
│   │   │   │   │   ├── ItensPedido.class
│   │   │   │   │   ├── Loja.class
│   │   │   │   │   ├── Pedido.class
│   │   │   │   │   ├── PedidoCliente.class
│   │   │   │   │   └── Promocao.class
│   │   │   │   ├── repository
│   │   │   │   │   ├── CategoriaRepository.class
│   │   │   │   │   ├── ClienteRepository.class
│   │   │   │   │   ├── DependenteRepository.class
│   │   │   │   │   ├── FuncionarioRepository.class
│   │   │   │   │   ├── ItemRepository.class
│   │   │   │   │   ├── ItensPedidoRepository.class
│   │   │   │   │   ├── LojaRepository.class
│   │   │   │   │   ├── PedidoClienteRepository.class
│   │   │   │   │   ├── PedidoRepository.class
│   │   │   │   │   └── PromocaoRepository.class
│   │   │   │   ├── service
│   │   │   │   │   ├── CategoriaService.class
│   │   │   │   │   ├── CategoriaServiceImpl.class
│   │   │   │   │   ├── ClienteService.class
│   │   │   │   │   ├── ClienteServiceImpl.class
│   │   │   │   │   ├── DependenteService.class
│   │   │   │   │   ├── DependenteServiceImpl.class
│   │   │   │   │   ├── FuncionarioService.class
│   │   │   │   │   ├── FuncionarioServiceImpl.class
│   │   │   │   │   ├── ItemService.class
│   │   │   │   │   ├── ItemServiceImpl.class
│   │   │   │   │   ├── ItensPedidoService.class
│   │   │   │   │   ├── ItensPedidoServiceImpl.class
│   │   │   │   │   ├── LojaService.class
│   │   │   │   │   ├── LojaServiceImpl.class
│   │   │   │   │   ├── PedidoClienteService.class
│   │   │   │   │   ├── PedidoClienteServiceImpl.class
│   │   │   │   │   ├── PedidoService.class
│   │   │   │   │   ├── PedidoServiceImpl.class
│   │   │   │   │   ├── PromocaoService.class
│   │   │   │   │   └── PromocaoServiceImpl.class
│   │   │   │   └── PortalTemakiApplication.class
│   │   │   └── application.properties
│   │   ├── maven-archiver
│   │   │   └── pom.properties
│   │   ├── maven-status/maven-compiler-plugin
│   │   │   ├── compile/default-compile
│   │   │   │   ├── createdFiles.lst
│   │   │   │   └── inputFiles.lst
│   │   │   └── testCompile/default-testCompile
│   │   │       ├── createdFiles.lst
│   │   │       └── inputFiles.lst
│   │   ├── surefire-reports
│   │   │   ├── 2024-05-17T20-10-15_287.dumpstream
│   │   │   ├── TEST-com.cesar.portaltemaki.PortalTemakiApplicationTests.xml
│   │   │   └── com.cesar.portaltemaki.PortalTemakiApplicationTests.txt
│   │   ├── test-classes/com/cesar/portaltemaki
│   │   │   └── PortalTemakiApplicationTests.class
│   │   ├── restaurante-japones-0.0.1-SNAPSHOT.jar
│   │   └── restaurante-japones-0.0.1-SNAPSHOT.jar.original
│   ├── mvnw
│   ├── mvnw.cmd
│   └── pom.xml
└── README.md

```

# Apresentação do Projeto
- Slides 📊: 
- Vídeo da apresentação 🎥:
- [Figma](https://www.figma.com/file/hCwtRKyYw3bSQgvGvJATJT/Webflow-Lofi-Prototype-(Community)?type=design&node-id=1%3A1001&mode=design&t=TmnE8kHre7VyWULf-1)

# Equipe
| [<img loading="lazy" src="https://avatars.githubusercontent.com/u/108764670?v=4" width=115><br><sub>Adriana Lúcia</sub>](https://github.com/Dricalucia) |  [<img loading="lazy" src="https://avatars.githubusercontent.com/u/47667167?v=4" width=115><br><sub>Pedro Villas Boas</sub>](https://github.com/PedroVillasBoas) |   [<img loading="lazy" src="https://avatars.githubusercontent.com/u/99739118?v=4" width=115><br><sub>Vinícius Ventura</sub>](https://github.com/vinivent) |  
| :---: | :---: | :---: |


---
© 2024
