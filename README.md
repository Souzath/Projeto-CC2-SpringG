# Projeto-CC2-SpringG
# AUTORES: Thiago Bretas de Souza, Guilherme Neves, Gustavo David Bastos


O projeto foi feito em Java, utilizando o ANTLR para gerar a gramática.
Pré-rqeuisitos: Ter o JDK e o Maven instalados no sistema, bem como suas variáveis de ambiente configuradas. Para configurar as variáveis de ambiente, foi utilizado o programa Rapid Environment Editor. Basta criar uma variável JAVA_HOME e uma MAVEN_HOME, apontando para o diretorio do seu JDK e Maven e então adicioná-las no path do seu sistema.
O projeto foi feito no Eclipse, mas qualquer IDE é válida. Basta importar o projeto como um projeto Maven.

A ideia é receber um arquivo .txt genérico aceito pela gramática e gerar, na saída, as classes java equivalentes em um formato aceito pelo SPRING.

O projeto foi feito seguindo como base o tutorial do SPRING: https://spring.io/guides/tutorials/rest/

Antes de testar o projeto, recomenda-se gerar o projeto feito no tutorial acima ao menos uma vez.

Para testar o projeto deste repositório, primeiro vá ao SPRING Initializr(https://start.spring.io). Selecione Maven Project, escolha a versão do java, nomeie o projeto e adicione as dependencias WEB, JPA e H2. Feito isso, basta baixar o pacote e extraí-lo em uma pasta. Para este tutorial, vamos utilizar um arquivo .txt chamado TESTE.txt, que contem a nossa entidade escrita em uma gramática genérica, que será interpretada pelo nosso compilador.

Para gerar as classes SPRING correspondentes, vá ao terminal e digite o seguinte comando:

java -jar C:\Users\thiag\eclipse-workspace\SpringG\target\SpringG-0.0.1-SNAPSHOT-jar-with-dependencies.jar C:\Users\<seu usuário>\Desktop\TESTE.txt .\<pasta baixada pelo SPRING Initializr>

O comando ira chamar o compilador, passando o arquivo de texto TESTE.txt e irá salvar as classes em uma pasta com o nome da entidade, dentro de uma pasta pacote.


OBS.: Note que ao baixar os arquivos do Initializr, ele gera uma classe Application.java. Essa classe funciona como a "main" do nosso programa SPRING. Os arquivos gerados pelo compilador devem estar na mesma pasta.

Antes de iniciar o nosso programa, seria interessante carregá-lo com dados. Para isso, podemos fazer um arquivo .java na mão mesmo, chamado LoadDatabase. Pense nele como um "INSERT" em uma tabela. Para isso, o exemplo fornecido pelo tutorial do SPRING será utilizado. Modifique apenas os dados em si e salve tudo junto com as outras classes.

Por fim, no seu terminal, dentro da pasta onde encontra-se o arquivo .pom do seu projeto, basta rodar o comando mvn clean spring-boot:run para rodar o projeto. Uma vez rodando, voce pode verificar se os dados foram inseridos corretamente digitando em outro terminal o comando curl -v localhost:8080/<nome da entidade>(se utilizar o arquivo teste fornecido pelo repositorio, digite produtos).


