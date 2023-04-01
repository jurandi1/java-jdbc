<h1 align="center"> Java e JDBC: trabalhando com um banco de dados </h1>

# Índice 

* [Introdução](#Introdução)
* [Ferramentas utilizadas](#Ferramentas-utilizadas)
* [Criação de tabela pelo MySQL](#Criação-de-tabela-pelo-MySQL)
* [Principaís funções do projeto](#Principaís-funções-do-projeto)
* [Abrir conta](#Abrir-conta)
* [Realizar depósito](#Realizar-depósito)
* [Realizar saque](#Realizar-saque)
* [Realizar transferência](#Realizar-transferência)
* [Encerrando uma conta](#Encerrando-uma-conta)
* [Verificando tabela direto no MySQL](#Verificando-tabela-direto-no-MySQL)
* [Acesso ao projeto](#Acesso-ao-projeto)
* [Desenvolvedor](#Desenvolvedor)

<h2>Introdução</h2>

Projeto criado a partir do curso de Java e JDBC da [Alura](https://cursos.alura.com.br/course/java-jdbc-banco-dados) com o instrutor [João Victor](https://www.linkedin.com/in/jvcmartins/). Onde criamos o banco bytebank com a linguagem Java e integramos a aplicação com o banco de dados MySQL, aprendemos a utilizar o Factory Pattern e as cláusulas de inserção do SQL para salvar dados no banco, como reaproveitar as conexões com o banco de dados usando um pool de conexões, como fazer alterações em dados já existentes no banco, utilizando a cláusula UPDATE do SQL e como usar a cláusula DELETE para apagar dados do banco, além de entender como realizar a exclusão lógica.

![jdbc](https://user-images.githubusercontent.com/105133847/229301474-2554f827-a5bb-428a-a6ec-2bffb22a3616.PNG)

<h2>Ferramentas utilizadas</h2>

<a href="https://github.com/jurandi1/java-jdbc"> <img align="center" alt="Junior-JAVA" height="40" width="40" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original-wordmark.svg">
   <a href="https://github.com/jurandi1/java-jdbc"> <img align="center" alt="Junior-MySQL" height="40" width="40" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original-wordmark.svg">
   <a href="https://github.com/jurandi1/java-jdbc"> <img align="center" alt="Junior-STS" height="40" width="40" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original-wordmark.svg">
   
<h2>Criação de tabela pelo MySQL</h2>

``` SQL
CREATE TABLE `conta` (
  `numero` int NOT NULL,
  `saldo` decimal(10,0) DEFAULT NULL,
  `cliente_nome` varchar(50) DEFAULT NULL,
  `cliente_cpf` varchar(11) DEFAULT NULL,
  `cliente_email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
               
               
<h2>Princípais funções do projeto</h2>

<h3>Abrir conta</h3>

https://user-images.githubusercontent.com/105133847/229301299-549d21f7-e16c-4f02-9477-cbf0f85e91da.mp4

<h3>Realizar depósito</h3>

https://user-images.githubusercontent.com/105133847/229301727-38627740-0c7d-4caf-a44c-134eb9096e51.mp4

<h3>Realizar saque</h3>

https://user-images.githubusercontent.com/105133847/229302007-b3bf0574-1302-40ec-a985-e733d2ab2583.mp4

<h3>Realizar transferência</h3>

https://user-images.githubusercontent.com/105133847/229302234-e080829b-23d4-4d85-9aa1-64a45ba58ac5.mp4

<h3>Encerrando uma conta</h3>

https://user-images.githubusercontent.com/105133847/229302634-92bf78e9-b7b0-4d5c-a9eb-a0b96b5bc39b.mp4

<h3>Verificando tabela direto no MySQL</h3>

https://user-images.githubusercontent.com/105133847/229304713-593e359d-5ca5-497d-ac3a-7c3a5b31be6b.mp4

<h2>Acesso ao projeto</h2>

Você pode acessar o avanço do projeto em cada [commit](https://github.com/jurandi1/java-jdbc/commits/main) ou [baixar o projeto completo](https://github.com/jurandi1/java-jdbc/archive/refs/heads/main.zip).

<h2>Desenvolvedor</h2>

[<img src="https://user-images.githubusercontent.com/105133847/215238362-763c8d76-55d2-4fd0-8b5f-f7080fbc4114.jpg" width=115><br><sub>Jurandi Junior</sub>](https://github.com/jurandi1)
