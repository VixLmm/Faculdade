INSERT INTO cliente SELECT * FROM demo.cliente;
INSERT INTO vendedor SELECT * FROM demo.vendedor;
INSERT INTO produto SELECT * FROM demo.produto;
INSERT INTO prateleira SELECT * FROM demo.prateleira;

INSERT INTO fone_cliente SELECT * FROM demo.fone_cliente;
INSERT INTO fone_vendedor SELECT * FROM demo.fone_vendedor;
INSERT INTO pf SELECT * FROM demo.pf;
INSERT INTO pj SELECT * FROM demo.pj;

INSERT INTO pedido SELECT * FROM demo.pedido;

INSERT INTO capacidade_estoque SELECT * FROM demo.capacidade_estoque;
INSERT INTO estoque SELECT * FROM demo.estoque;

COMMIT;
SELECT 'CLIENTE' tabela, COUNT(*) qtd FROM cliente UNION ALL
SELECT 'PEDIDO', COUNT(*) FROM pedido UNION ALL
SELECT 'PRODUTO', COUNT(*) FROM produto UNION ALL
SELECT 'ESTOQUE', COUNT(*) FROM estoque;
