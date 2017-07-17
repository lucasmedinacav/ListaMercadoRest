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

Baixar projeto pelo github: https://github.com/lucasmedinacav/Api_Rest_Campanhas

Ou comando no terminal: git clone https://github.com/lucasmedinacav/Api_Rest_Campanhas.git

Comando para rodar o projeto: mvn spring-boot:run

*Se nao tiver maven configurado na maquina, pode rodar pelo eclipse > import maven projects > botao direito no campaignBootRest> Run As > maven build... > no campo Goals: spring-boot:run

Servico de usuarios: http://localhost:8080/people/ 
Servico de times: http://localhost:8080/teams/ 
Servico de camapanhas: http://localhost:8080/campaings/

TESTES QUE SOLUCIONAM PROPOSTAS DOS EXERCICIOS
1.a - Cadastro de Campanhas com atributos solicitados: CampaignResourceTest.java -  test1_RegisterCampaign1And2()
1.b - O sistema não deverá retornar campanhas com a data de vigencia vencida: CampaignResourceTest.java - test3_RegisterCampaignWithInvalidPeriodAndGetCampaigns()
1.c - Controle de cadastro de vigencias com periodos iguais, somando um dia na data de termino das ja cadastradas: CampaignResourceTest.java - test2_RegisterThreeCampaignsWithTwoSamePeriod()
1.d - Recurso para avisar que houve alteracoes em campanhas existentes: CampaignResourceTest.java - test4_FindCampaignsWithHasChanges()

2.a - Cadastro de usuarios: PersonResourceTest.java - test1.. ao test4...
2.b - Dado um email que ja existe informar que o cadastro ja foi efetuado: 
PersonResourceTest.java - test5_FoundPersonWithNameJoao() e test6_FoundPersonEmptyWithEmailWrong()
2.c - Buscar campanhas que o usuario nao esteja cadastrado e que sejam do time do coracao dele e que nao tenham periodo vencido :PersonResourceTest.java - test4_ValidationOfGetCampaignsValidsAndNotRegisterWithPerson()

Justificativa para não execução do Exercício 3

Eu fiquei entre duas alternativas para resolver o exercício. A primeira seria varrer um array de char baseado na String input, fazendo toda a lógica em cima disso, porém eu tive uma idéia que seria mais performática e mais enxuta: a execução de expressão regular(regex) onde só seria necessario varrer os matches encontrados (os metodos hasNext e getNext trabalhariam em cima desses matches).
Eu formulei uma expresao muito proxima ao enunciado da questao, encontrou varios matches corretos que validei, inclusive ao enunciado (aAbBABacafe encontrou a letra e), porem com alguns testes vi que não estava 100% acertivo, e por questão de tempo para entregar o teste não consegui finalizar.

expressao regular para vogal-consoante-vogalQueNaoSeRepete: ([aAeEiIoOuU][bB-dDfF-hHJj-nNpP-tTvV-zZ]([aAeEiIoOuU])\1)

Resposta Exercicio 4
Deadlock em banco de dados: Quando transações ficam bloqueadas de maneira que uma acabe esperando pelo termino da outra. 

Por exemplo: a transação 01 bloqueia determinados registros enquanto a transação 02 tenta atualiza-los, porem, antes da transacao 02 tentar acessar os registros, ela teve que atualizar outros registros que a transacao 01 ira acessar em seguida, porem quando a transacao 01 tentar acessar, nao conseguira pois a transacao 02 não efetivou. Nisso a transacao 01 fica esperando a transacao 02 e vice versa. Porem hoje em dia existem recursos como timeout para efetuar rollback evitando deadlocks

Deadlock em threads: Quando threads ficam paradas sem finalizar suas tarefas esperando uma acabar para a outra continuar

Por exemplo: A thread 01 acessa um arquivo A e bloqueia ele por um tempo, nesse periodo uma outra Thread 02 bloqueia um outro arquivo B e tambem mantem ele bloqueado por um tempo. Se por acaso a Thread 01 tentar acessar o arquivo B por conta de um processo synchronized, onde somente uma thread pode acessar um arquivo de cada vez, ela ficará esperando, e se a Thread 02 tentar acessar o arquivo A, acontecera assim um deadLock pois as duas ficariam paradas esperando um arquivo ser liberado.

Em resumo deadlock define-se como uma situação em que ocorre um impasse entre dois ou mais processos onde ficam impedidos de continuar suas execuções, ou seja, ficam bloqueados. 

Resposta Exercicio 5

As streams surgiram no Java 8 para permitir que sejam realizadas algumas consultas mais sofisticadas em processamento de dados, suportando diversos tipos de operacoes como filter, reduce, map, iterate (bastante similar a biblioteca Array de Javascript). Nas Streams tudo acontece na mesma Thread, porem se houver uma lista com milhões de registros, o processo pode ser mais custoso que o esperado. Uma alternativa para resolver essa preocupação é a utilização do ParallelStream, o Stream Paralelo, pois ele que se encarrega da decisão de quantas Threads o processamento irá precisar e utilizar. Porém ele não é recomendado para utilizacação em conjunto de coleções pequenas, pois o overhead, todo o gerenciamento de Threads tornaria um processamento que deveria ser simples e rápido em um processamento mais lento.