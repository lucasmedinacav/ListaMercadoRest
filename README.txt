Principais Tecnologias Utilizadas Para Desenvolvimento da Api REST:

-Java 8
-Maven 3
-Spring Boot 1.1.9.RELEASE
-Jersey 2.7
-Jackson 2.8.1
-Hibernate 4
-PostgreSQL
-JUnit 4.11

*O sistema consome banco de dados Postgres, os dados de conexao estao no SqlInitialization.java porem quem for avaliar o sistema nao precisara criar banco nem nada pois criei remoto com Heroku

Baixar projeto pelo github: https://github.com/lucasmedinacav/listaMercadoRest

Ou comando no terminal: git clone https://github.com/lucasmedinacav/listaMercadoRest.git

Comando para rodar o projeto: mvn spring-boot:run

*Se nao tiver maven configurado na maquina, pode rodar pelo eclipse > import maven projects > botao direito no listaMercadoRest> Run As > maven build... > no campo Goals: spring-boot:run

Servico de listas: http://localhost:8080/listas/ 
Servico de produtos: http://localhost:8080/produtos/ 

TESTES QUE SOLUCIONAM PRINCIPAIS PONTOS DA HISTORIA DA MARIAZINHA
