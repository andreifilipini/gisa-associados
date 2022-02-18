INSERT INTO gisaassociadosdb.associado(dt_nascimento, nome, user_id)
VALUES
('1991-11-01 08:15:00', 'Associado da Silva', '4925fa6a4058728a45bbfc8f79f7276c6d10dbbf47cff1041df420aaf5acc3cc1a1e04291cb0de286c70351d7170c9044bba4c94c87beed9f005667c0cf7074');

INSERT INTO gisaassociadosdb.historico_plano (atualizacao, plano_id, status, tipo_atendimento, tipo_plano, associado_id)
VALUES
(NOW(), (SELECT id FROM gisaplanodb.plano where descricao = 'Básico'), 'ATIVO', (SELECT tipo_atendimento  FROM gisaplanodb.plano where descricao = 'Básico'),
(SELECT tipo_plano  FROM gisaplanodb.plano where descricao = 'Básico'), (SELECT id from gisaassociadosdb.associado WHERE nome = 'Associado da Silva'));