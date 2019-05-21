insert into Accounts (LOGIN, ACTIVE, PASSWORD, ROLE) values ('admin', 1, 'admin123', 'ADMIN');
insert into Accounts (LOGIN, ACTIVE, PASSWORD, ROLE) values ('user', 1, 'user123', 'CUSTOMER');
insert into Accounts (LOGIN, ACTIVE, PASSWORD, ROLE) values ('user2', 1, 'user123', 'CUSTOMER');
insert into ACCOUNT_CONTACT(address, name, patronymic, phone, surname, account_login) VALUES ( 'test address', 'test name', 'test patronymic', 'test phone', 'test surname', select login from ACCOUNTS where LOGIN = 'user');
insert into ACCOUNT_COMMERCE(balance, discount, account_login) values ( 2000, 5, select login from ACCOUNTS where LOGIN = 'user');
insert into ACCOUNT_CONTACT(address, name, patronymic, phone, surname, account_login) VALUES ( 'test address', 'test name', 'test patronymic', 'test phone', 'test surname', select login from ACCOUNTS where LOGIN = 'user2');
insert into ACCOUNT_COMMERCE(balance, discount, account_login) values ( 2000, 5, select login from ACCOUNTS where LOGIN = 'user2');
insert into FLOWERS (NAME, PRICE) values ('Red rose', 1.40);
insert into FLOWERS(name, price) VALUES ( 'Violes', 0.5 );
insert into FLOWERS (NAME, PRICE) values ('Black rose', 2.10);
insert into FLOWERS(name, price) VALUES ( 'White rose', 1.95 );
insert into FLOWERS (NAME, PRICE) values ('Rainbow rose', 2.00);
insert into FLOWERS(name, price) VALUES ( 'Pink rose', 1.3 );
insert into FLOWERS(name, price) values ( 'White lilium', 2.25 );
insert into FLOWERS(name, price) values ( 'Yellow lilium', 2.25 );
insert into FLOWERS(name, price) values ( 'Pink lilium', 2.25 );
insert into FLOWERS(name, price) values ( 'Lowa rose white lilium', 2.25 );
insert into FLOWERS(name, price) values ( 'Lowa rose black lilium', 2.25 );
insert into STOCK(quantity, flower_name) VALUES ( 200,select name from FLOWERS where name = 'Violes') ;
insert into STOCK(quantity, flower_name) VALUES ( 300,select name from FLOWERS where name = 'Red rose') ;
insert into STOCK(quantity, flower_name) VALUES ( 300,select name from FLOWERS where name = 'Black rose') ;
insert into STOCK(quantity, flower_name) VALUES ( 300,select name from FLOWERS where name = 'White rose') ;
insert into STOCK(quantity, flower_name) VALUES ( 300,select name from FLOWERS where name = 'Rainbow rose') ;
insert into STOCK(quantity, flower_name) VALUES ( 300,select name from FLOWERS where name = 'Pink rose') ;
insert into STOCK(quantity, flower_name) VALUES ( 300,select name from FLOWERS where name = 'White lilium') ;
insert into STOCK(quantity, flower_name) VALUES ( 300,select name from FLOWERS where name = 'Yellow lilium') ;
insert into STOCK(quantity, flower_name) VALUES ( 300,select name from FLOWERS where name = 'Pink lilium') ;
insert into STOCK(quantity, flower_name) VALUES ( 300,select name from FLOWERS where name = 'Lowa rose white lilium') ;
insert into STOCK(quantity, flower_name) VALUES ( 300,select name from FLOWERS where name = 'Lowa rose black lilium') ;