INSERT INTO orders (order_number, status, created_at)
VALUES ('ORD-1001', 'CREATED', '2026-10-09T10:30:00');

INSERT INTO order_items (order_id, name, quantity, unit_price)
VALUES
    ((SELECT id FROM orders WHERE order_number = 'ORD-1001'), 'Laptop', 1, 1200.00),
    ((SELECT id FROM orders WHERE order_number = 'ORD-1001'), 'Mouse', 2, 25.00);
