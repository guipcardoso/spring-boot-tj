--
--  Dataset de importação de dados para exemplo do sistema, carregados ao subir a aplicação em seu banco em memória
--

-- =================================================================================================
-- Comarca Cuiabá

insert into comarca (nome) values ('Cuiabá')

-- =========================================================================
insert into vara (nome, comarca_id) values ('Vara 1 de Cuiabá', 1)

insert into vara_competencia (vara_id, competencia) values (1, 'FEITOS_GERAIS_CIVEIS')
insert into vara_competencia (vara_id, competencia) values (1, 'FAMILIA')
insert into vara_competencia (vara_id, competencia) values (1, 'FAZENDA')
insert into vara_competencia (vara_id, competencia) values (1, 'INFANCIA')

insert into processo (vara_id) values (1)
insert into processo_competencia (processo_numeroUnico, competencia) values (1, 'FEITOS_GERAIS_CIVEIS')
insert into processo_competencia (processo_numeroUnico, competencia) values (1, 'FAMILIA')

insert into processo (vara_id) values (1)
insert into processo_competencia (processo_numeroUnico, competencia) values (2, 'FAZENDA')

insert into processo (vara_id) values (1)
insert into processo_competencia (processo_numeroUnico, competencia) values (3, 'INFANCIA')

-- =========================================================================

insert into vara (nome, comarca_id) values ('Vara 2 de Cuiabá', 1)

insert into vara_competencia (vara_id, competencia) values (2, 'FAZENDA')
insert into vara_competencia (vara_id, competencia) values (2, 'INFANCIA')
insert into vara_competencia (vara_id, competencia) values (2, 'DIRETORIA')
insert into vara_competencia (vara_id, competencia) values (2, 'FALENCIA')

-- =========================================================================

insert into vara (nome, comarca_id) values ('Vara 3 de Cuiabá', 1)

insert into vara_competencia (vara_id, competencia) values (3, 'DIRETORIA')
insert into vara_competencia (vara_id, competencia) values (3, 'FALENCIA')
insert into vara_competencia (vara_id, competencia) values (3, 'JUIZADO_ESPECIAL_CIVEL')
insert into vara_competencia (vara_id, competencia) values (3, 'JUIZADO_ESPECIAL_CRIME')
