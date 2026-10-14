
-- 1. Produto 207, pedidos pares, ano < 2018, qtd 100, R$ 10,00
INSERT INTO item_produto (codigo_pro, numero_ped, quantidade, preco_unitario, valor_item)
SELECT 207, p.numero, 100, 10.00, 100 * 10.00
  FROM pedido p
 WHERE MOD(p.numero, 2) = 0
   AND EXTRACT(YEAR FROM p.data) < 2018;

-- 2. Produto 206, pedidos impares, ano = 2018, qtd 50, R$ 12,00
INSERT INTO item_produto (codigo_pro, numero_ped, quantidade, preco_unitario, valor_item)
SELECT 206, p.numero, 50, 12.00, 50 * 12.00
  FROM pedido p
 WHERE MOD(p.numero, 2) = 1
   AND EXTRACT(YEAR FROM p.data) = 2018;

-- 3. Produto 207, pedidos pares, ano = 2018, qtd 150, R$ 14,00
INSERT INTO item_produto (codigo_pro, numero_ped, quantidade, preco_unitario, valor_item)
SELECT 207, p.numero, 150, 14.00, 150 * 14.00
  FROM pedido p
 WHERE MOD(p.numero, 2) = 0
   AND EXTRACT(YEAR FROM p.data) = 2018;

-- 4. Produto 206, pedidos impares, ano < 2018, qtd 200, R$ 8,00
INSERT INTO item_produto (codigo_pro, numero_ped, quantidade, preco_unitario, valor_item)
SELECT 206, p.numero, 200, 8.00, 200 * 8.00
  FROM pedido p
 WHERE MOD(p.numero, 2) = 1
   AND EXTRACT(YEAR FROM p.data) < 2018;

-- 5. Produto 208, sem endereco de entrega, ano = 2017, qtd 80, R$ 18,00
INSERT INTO item_produto (codigo_pro, numero_ped, quantidade, preco_unitario, valor_item)
SELECT 208, p.numero, 80, 18.00, 80 * 18.00
  FROM pedido p
 WHERE p.rua IS NULL
   AND EXTRACT(YEAR FROM p.data) = 2017;

-- 6. Produto 208, sem endereco de entrega, ano = 2018, qtd 70, R$ 20,00
INSERT INTO item_produto (codigo_pro, numero_ped, quantidade, preco_unitario, valor_item)
SELECT 208, p.numero, 70, 20.00, 70 * 20.00
  FROM pedido p
 WHERE p.rua IS NULL
   AND EXTRACT(YEAR FROM p.data) = 2018;

-- 7. Produto 202, com endereco de entrega, DIA IMPAR DA ENTREGA (prazo_entrega), qtd 60, R$ 15,00
INSERT INTO item_produto (codigo_pro, numero_ped, quantidade, preco_unitario, valor_item)
SELECT 202, p.numero, 60, 15.00, 60 * 15.00
  FROM pedido p
 WHERE p.rua IS NOT NULL
   AND MOD(EXTRACT(DAY FROM p.prazo_entrega), 2) = 1;

-- 8. Produto 205, com endereco de entrega, dia PAR da data do pedido, qtd 90, R$ 11,50
INSERT INTO item_produto (codigo_pro, numero_ped, quantidade, preco_unitario, valor_item)
SELECT 205, p.numero, 90, 11.50, 90 * 11.50
  FROM pedido p
 WHERE p.rua IS NOT NULL
   AND MOD(EXTRACT(DAY FROM p.data), 2) = 0;

-- 9. Produto 203, com endereco de entrega, dia IMPAR da data do pedido, qtd 550, R$ 21,35
INSERT INTO item_produto (codigo_pro, numero_ped, quantidade, preco_unitario, valor_item)
SELECT 203, p.numero, 550, 21.35, 550 * 21.35
  FROM pedido p
 WHERE p.rua IS NOT NULL
   AND MOD(EXTRACT(DAY FROM p.data), 2) = 1;

-- 10. Produto 204, sem endereco de entrega, dia PAR da data do pedido, qtd 150, R$ 25,85
INSERT INTO item_produto (codigo_pro, numero_ped, quantidade, preco_unitario, valor_item)
SELECT 204, p.numero, 150, 25.85, 150 * 25.85
  FROM pedido p
 WHERE p.rua IS NULL
   AND MOD(EXTRACT(DAY FROM p.data), 2) = 0;

COMMIT;

-- Conferencia: itens por produto
SELECT codigo_pro, COUNT(*) pedidos, SUM(valor_item) total
  FROM item_produto
 GROUP BY codigo_pro
 ORDER BY codigo_pro;
