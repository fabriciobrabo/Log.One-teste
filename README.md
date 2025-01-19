A arquitetura do projeto foi desenvolvida com base na evolução dos princípios do modelo MVC (Model-View-Controller), 
onde cada parte do projeto tem sua responsabilidade bem definida. Conceitualmente foi aplicado técnicas de programção clássicas
ao projeto, que hoje estão mais atuais e perfomáticas que nunca, são elas: Programação orientada a genéricos e Programação Orientada a Interfaces.

Os genéricos facilitam reutilização de código, performance e atendem perfeitamente o Clean Code necessário para a saúde do projeto e da equipe.
Dos Frameworks utilizados fiz o uso do Spring Boot em conjunto com o Spring Data(que tem como base o JPA e o Hibernate) para obter maiores ganhos.
Para a arquitetura de dados na camada de visão fiz o uso de DTOs(Data Transfer Object). E utilização de genéricos também para os Beans.

Para as páginas JSF utilizei um padrão de telas tradicional de CRUD.
Estou utilizando o recurso de application.yml por ser mais moderno e mais fácil de manter que o application.properties.

Para rodar a aplicação você pode seguir esses 2 caminhos: 

1 - No terminal digite "mvn clean"; depois install e depois "mvn package -DskipTests=true" 
e depois "java -jar target/Teste-Pratico-Desenvolvedor-Java-0.0.2-SNAPSHOT.jar"

ou

2 - No terminal digite "mvn clean"; depois install e depois "mvn package -DskipTests=true" 
e depois "mvn spring-boot:run -DskipTests=true"

