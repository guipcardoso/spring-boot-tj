# Desafio TJ - Distribuição de Processos



URL GET para consulta de processo:
http://localhost:8080/processo/{numeroUnico}

URL POST para cadastro de um novo processo:
http://localhost:8080/processo/

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
