# Desafio TJ - Distribuição de Processos

Para rodar você pode fazer de duas maneiras:

1) Baixar o projeto e importar como um Projeto Maven no Eclipse, procurar a classe DistribuicaoProjetoTJApplication.java e usar o comando Run As -> Java Application

2) Com o maven instalado, através do terminal entre na pasta principal do projeto e rode o comando: mvn spring-boot:run

URL GET para consulta de processo:
http://localhost:8080/processos/{numeroUnico}

URL POST para cadastro de um novo processo:
http://localhost:8080/processos/

Passando no body um JSON no formato do exemplo:

{
    "comarcaId": 1,
    "classeProcessual": ["FAMILIA", "FAZENDA"]
}

Comarca cadastrada a ser utilizada será a de id = 1

Possíveis Competências para a Classe Processual:

FEITOS_GERAIS_CIVEIS

FAMILIA

FAZENDA

INFANCIA

DIRETORIA

FALENCIA

JUIZADO_ESPECIAL_CIVEL

JUIZADO_ESPECIAL_CRIME

Alterar arquivo import.sql do projeto e recompilar se quiser adicionar novas varas e comarcas, seguindo o mesmo padrão mostrado no arquivo.
