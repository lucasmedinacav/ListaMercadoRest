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
O relacionamento foi feito entre N - N com uma tabela associativa, onde da mais possibilidades para a aplicacao (gravar quatidade de produtos que maria ira comprar, se ele ja colocou no carrinho ou nao, etc)


Baixar projeto pelo github: https://github.com/lucasmedinacav/listaMercadoRest

Ou comando no terminal: git clone https://github.com/lucasmedinacav/listaMercadoRest.git

Comando para rodar o projeto: mvn spring-boot:run

Servico de listas: http://localhost:8080/listas
Servico de produtos: http://localhost:8080/produtos
Servico de integracoes entre produtos e listas: http://localhost:8080/produtosLista 

O serviço também está deployado no Heroku: https://campanhas-rest-api.herokuapp.com/people

TESTES QUE SOLUCIONAM PRINCIPAIS PONTOS DA HISTORIA DA MARIAZINHA
Os testes foram desenvolvidos em tempo de construcao da aplicacao, ajudando a construir e testar ao mesmo tempo, como uma boa pratica que é.
com.api.jersey.ListaResourceTest
com.api.jersey.ProdutoListaResourceTest
com.api.jersey.ProdutoResourceTest

FRONTEND
Por mais que o foco do teste nao ser frontend, tentei entregar o maximo de funcionalidades em um layout agradavel de se mexer e entender (ux - usabilidade), pois sao conceitos pelo quais me interesso tambem
Tecnologias utilizadas:
Angular 4
Bootstrap 3
Webpack
Javascript
Typescript
Jquery
CSS3
HTML5
AWS-SDK (biblioteca extremamente simples e legal de mexer, onde voce consegue fazer upload de imagens e salvar na nuvem aws da Amazon)
