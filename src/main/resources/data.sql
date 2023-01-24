--data for customer table
insert into customer_master (id, first_name, last_name, email_id, contact_Number, address, is_inactive, created_On, created_By, updated_On,  updated_By) values(101, 'FN1', 'LN1', 'fn1ln1@mail.com', '1234567890', '1 White house Washington DC ' ,  0, now(), 'Admin', now(), 'Admin');
insert into customer_master (id, first_name, last_name, email_id, contact_Number, address, is_inactive, created_On, created_By, updated_On,  updated_By) values(201, 'FN2', 'LN2', 'fn2ln2@mail.com', '8901234567', '2 White house Washington DC ' ,  0, now(), 'Admin', now(), 'Admin');
insert into customer_master (id, first_name, last_name, email_id, contact_Number, address, is_inactive, created_On, created_By, updated_On,  updated_By) values(301, 'FN3', 'LN3', 'fn3ln3@mail.com', '5671234890', '3 White house Washington DC ' ,  0, now(), 'Admin', now(), 'Admin');
insert into customer_master (id, first_name, last_name, email_id, contact_Number, address, is_inactive, created_On, created_By, updated_On,  updated_By) values(401, 'FN4', 'LN4', 'fn4ln4@mail.com', '1171234890', '4 White house Washington DC ' ,  0, now(), 'Admin', now(), 'Admin');

--data for product table
insert into product_master (id, product_code, product_name, price, created_On, created_By, updated_On,  updated_By) values(1001, 'PRD-01', 'Camera', 165.73,  now(), 'Admin', now(), 'Admin');
insert into product_master (id, product_code, product_name, price, created_On, created_By, updated_On,  updated_By) values(1002, 'PRD-02', 'Memory Card', 55.22,  now(), 'Admin', now(), 'Admin');
insert into product_master (id, product_code, product_name, price, created_On, created_By, updated_On,  updated_By) values(1003, 'PRD-03', 'Flash', 45.15,  now(), 'Admin', now(), 'Admin'); 


--data for transaction table
insert into order_details (id, order_id, customer_id, product_id, quantity, unit_price, amount, created_On, created_By, updated_On,  updated_By) values(1, 'CUST-ORDER-1', 101, 1001, 1, 165.73, 165.73,  '2022-10-15', 'SalesAgent-1', '2022-10-15', 'SalesAgent-1');
insert into order_details (id, order_id, customer_id, product_id, quantity, unit_price, amount, created_On, created_By, updated_On,  updated_By) values(2, 'CUST-ORDER-2', 101, 1001, 1, 165.73, 165.73,  '2022-12-25', 'SalesAgent-5', '2022-12-25', 'SalesAgent-5');
insert into order_details (id, order_id, customer_id, product_id, quantity, unit_price, amount, created_On, created_By, updated_On,  updated_By) values(3, 'CUST-ORDER-3', 101, 1003, 1, 45.15,  45.15,   '2023-01-05', 'SalesAgent-3', '2023-01-05', 'SalesAgent-3');


insert into order_details (id, order_id, customer_id, product_id, quantity, unit_price, amount, created_On, created_By, updated_On,  updated_By ) values(4, 'CUST-ORDER-7', 301, 1002, 1, 55.22,  55.22,   '2022-11-30', 'SalesAgent-2', '2022-11-30', 'SalesAgent-2');
insert into order_details (id, order_id, customer_id, product_id, quantity, unit_price, amount, created_On, created_By, updated_On,  updated_By ) values(5, 'CUST-ORDER-8', 301, 1001, 1, 165.73, 165.73,  '2023-01-05', 'SalesAgent-4', '2023-01-05', 'SalesAgent-4');
insert into order_details (id, order_id, customer_id, product_id, quantity, unit_price, amount, created_On, created_By, updated_On,  updated_By ) values(6, 'CUST-ORDER-8', 301, 1003, 2, 45.15,  90.30,   '2023-01-05', 'SalesAgent-4', '2023-01-05', 'SalesAgent-4');


insert into order_details (id, order_id, customer_id, product_id, quantity, unit_price, amount, created_On, created_By, updated_On,  updated_By) values(7, 'CUST-ORDER-9', 401, 1003, 1, 45.15,  45.15,   '2023-01-05', 'SalesAgent-3', '2023-01-05', 'SalesAgent-3');

