insert into Accounts (LOGIN, ACTIVE, PASSWORD, ROLE) values ('admin', 1, 'admin123', 'ADMIN');
insert into FLOWERS (NAME, PRICE) values ('Rose', 90);
insert into FLOWERS(name, price) VALUES ( 'Violes', 50 );
insert into STOCK(quantity, flower_name) VALUES ( 200,select name from FLOWERS where name = 'Violes') ;
insert into STOCK(quantity, flower_name) VALUES ( 300,select name from FLOWERS where name = 'Rose') ;