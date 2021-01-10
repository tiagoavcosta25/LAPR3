INSERT INTO ADDRESS(LATITUDE, LONGITUDE,DOORNUMBER,STREETNAME, POSTALCODE, LOCALITY, COUNTRY) VALUES(41.1784687, -8.6111664, '431','R. Dr. António Bernardino de Almeida','4200-072','Porto','Portugal');
INSERT INTO ADDRESS(LATITUDE, LONGITUDE,DOORNUMBER,STREETNAME, POSTALCODE, LOCALITY, COUNTRY) VALUES(41.139559, -8.5378971, '50','R. S. João Deus','4220-210','Porto','Portugal');
INSERT INTO ADDRESS(LATITUDE, LONGITUDE,DOORNUMBER,STREETNAME, POSTALCODE, LOCALITY, COUNTRY) VALUES(38.7678423, -9.1015336, '100','Av. D. João II','1900-233','Lisboa','Portugal');
INSERT INTO ADDRESS(LATITUDE, LONGITUDE,DOORNUMBER,STREETNAME, POSTALCODE, LOCALITY, COUNTRY) VALUES(40.2092717, -8.4254476, '25','R. Larga 2','3000-370','Coimbra','Portugal');
INSERT INTO ADDRESS(LATITUDE, LONGITUDE,DOORNUMBER,STREETNAME, POSTALCODE, LOCALITY, COUNTRY) VALUES(41.7944244, -6.7777143, '0','Alameda de Santa Apolónia','5300-253','Bragança','Portugal');
INSERT INTO ADDRESS(LATITUDE, LONGITUDE,DOORNUMBER,STREETNAME, POSTALCODE, LOCALITY, COUNTRY) VALUES(41.5615136, -8.3989243, '0','Campus de Psicologia da UM','4100-057','Braga','Portugal');

INSERT INTO "User"(EMAIL,PASSWORD,NIF,NAME) VALUES('user1@gmail.com','user1123',123456789,'user 1');
INSERT INTO "User"(EMAIL,PASSWORD,NIF,NAME) VALUES('user2@gmail.com','user2123',987654321,'user 2');
INSERT INTO "User"(EMAIL,PASSWORD,NIF,NAME) VALUES('user3@gmail.com','user3123',111111111,'user 3');
INSERT INTO "User"(EMAIL,PASSWORD,NIF,NAME) VALUES('user4@gmail.com','user4123',222222222,'user 4');
INSERT INTO "User"(EMAIL,PASSWORD,NIF,NAME) VALUES('user5@gmail.com','user5123',333333333,'user 5');
INSERT INTO "User"(EMAIL,PASSWORD,NIF,NAME) VALUES('user6@gmail.com','user6123',444444444,'user 6');

INSERT INTO PHARMACYMANAGER(USERID) VALUES(5);

INSERT INTO PHARMACY(NAME,ADDRESSID,MANAGERID) VALUES ('Pharmacy 1', 1,5);

INSERT INTO ADMINISTRATOR(USERID) VALUES (1);

INSERT INTO CLIENT(USERID, CREDITS, ADDRESSID) VALUES (2,0,2);
INSERT INTO CLIENT(USERID, CREDITS, ADDRESSID) VALUES (3,0,3);
INSERT INTO CLIENT(USERID, CREDITS, ADDRESSID) VALUES (4,0,4);

INSERT INTO ORDERSTATUS(DESIGNATION) VALUES ('delivered');
INSERT INTO ORDERSTATUS(DESIGNATION) VALUES ('ordered');

INSERT INTO "Order"(CLIENTID,DESCRIPTION,ORDERSTATUS,ORDERDATE,ADDRESSID,PHARMACYID,TOTALWEIGHT,AMOUNT,ADDITIONALFEE) VALUES(2,'order 1', 'ordered', TO_DATE('2020-12-30','yyyy-mm-dd'), 4, 1, 2.5,45,2);
INSERT INTO "Order"(CLIENTID,DESCRIPTION,ORDERSTATUS,ORDERDATE,ADDRESSID,PHARMACYID,TOTALWEIGHT,AMOUNT,ADDITIONALFEE) VALUES(3,'order 2', 'delivered', TO_DATE('2021-01-03','yyyy-mm-dd'), 2, 1, 2,40,3);
INSERT INTO "Order"(CLIENTID,DESCRIPTION,ORDERSTATUS,ORDERDATE,ADDRESSID,PHARMACYID,TOTALWEIGHT,AMOUNT,ADDITIONALFEE) VALUES(4,'order 3', 'delivered', TO_DATE('2021-01-04','yyyy-mm-dd'), 3, 1, 2.5,30,2);
INSERT INTO "Order"(CLIENTID,DESCRIPTION,ORDERSTATUS,ORDERDATE,ADDRESSID,PHARMACYID,TOTALWEIGHT,AMOUNT,ADDITIONALFEE) VALUES(4,'order 4', 'ordered', TO_DATE('2021-01-08','yyyy-mm-dd'), 3, 1, 2.5,30,2);

INSERT INTO PRODUCT(NAME,DESCRIPTION,UNITARYPRICE,UNITARYWEIGHT) VALUES('COVID-19 Vaccine','Pfizer SARS-CoV-2 Vaccine', 18,0.5);
INSERT INTO PRODUCT(NAME,DESCRIPTION,UNITARYPRICE,UNITARYWEIGHT) VALUES('Ben-u-ron','Paracetamol', 2.5,0.3);

INSERT INTO PHARMACYPRODUCT(PHARMACYID, PRODUCTID, STOCK) VALUES (1,1,1000);
INSERT INTO PHARMACYPRODUCT(PHARMACYID, PRODUCTID, STOCK) VALUES (1,2,250);

INSERT INTO ORDERPRODUCT(PRODUCTID, ORDERID, QUANTITY) VALUES (1,2,2);
INSERT INTO ORDERPRODUCT(PRODUCTID, ORDERID, QUANTITY) VALUES (2,3,5);
INSERT INTO ORDERPRODUCT(PRODUCTID, ORDERID, QUANTITY) VALUES (1,3,1);
INSERT INTO ORDERPRODUCT(PRODUCTID, ORDERID, QUANTITY) VALUES (1,4,1);

INSERT INTO COURIER(USERID, IBAN, PHARMACYID) VALUES (6,'PT50002700000001234567833',1);

INSERT INTO CHARGINGSTATUS(DESIGNATION) VALUES('charged');
INSERT INTO CHARGINGSTATUS(DESIGNATION) VALUES('uncharged');

INSERT INTO SCOOTER(PHARMACYID,BATTERYPERC,POTENCY,WEIGHT,BATTERYCAPACITY,CHARGINGSTATUS) VALUES(1,50,50,10,500,'charged');

INSERT INTO DELIVERYSTATUS(DESIGNATION) VALUES ('idle');
INSERT INTO DELIVERYSTATUS(DESIGNATION) VALUES ('in progress');
INSERT INTO DELIVERYSTATUS(DESIGNATION) VALUES ('finished');

INSERT INTO DELIVERYRUN(COURIERID,SCOOTERID,DELIVERYSTATUS) VALUES(6,1,'finished');
INSERT INTO DELIVERYRUN(COURIERID,SCOOTERID,DELIVERYSTATUS) VALUES(6,1,'idle');

INSERT INTO DELIVERY(ORDERID, DELIVERYDATE, NOTES, DELIVERYRUNID) VALUES (2,TO_DATE('2021-01-04','yyyy-mm-dd'), 'left on door', 1);
INSERT INTO DELIVERY(ORDERID, DELIVERYDATE, NOTES, DELIVERYRUNID) VALUES (3,TO_DATE('2021-01-05','yyyy-mm-dd'), 'left on door', 1);
INSERT INTO DELIVERY(ORDERID, DELIVERYDATE, DELIVERYRUNID) VALUES (4,TO_DATE('2021-01-09','yyyy-mm-dd'),  2);

INSERT INTO CREDITCARD(creditcardnr, validitydate, ccv) VALUES (1234567812345678,TO_DATE('2022-01','yyyy-mm'),111);
INSERT INTO CREDITCARD(creditcardnr, validitydate, ccv) VALUES (1472583698765432,TO_DATE('2023-01','yyyy-mm'),222);

INSERT INTO CREDITCARDCLIENT(creditcardnr, clientid) VALUES (1234567812345678,3);
INSERT INTO CREDITCARDCLIENT(creditcardnr, clientid) VALUES (1472583698765432,4);

INSERT INTO ORDERSTATUS(DESIGNATION) VALUES ('Ordered');
INSERT INTO ORDERSTATUS(DESIGNATION) VALUES ('Delivering');
INSERT INTO ORDERSTATUS(DESIGNATION) VALUES ('Delivered');


INSERT INTO DELIVERYSTATUS(DESIGNATION) VALUES ('Idle');
INSERT INTO DELIVERYSTATUS(DESIGNATION) VALUES ('InProgress');
INSERT INTO DELIVERYSTATUS(DESIGNATION) VALUES ('Finished');

INSERT INTO PARK(PHARMACYID,MAXSLOTSNUMBER) VALUES (1,10);

INSERT INTO CHARGINGSLOT(PARKPHARMACYID,SCOOTERID,OUTPUTPOWER) VALUES (1,null,200);

INSERT INTO PATH(ADDRESSIDA, ADDRESSIDB, NAME) VALUES (1,2, 'Rua 1');
INSERT INTO PATH(ADDRESSIDA, ADDRESSIDB, NAME) VALUES (2,3, 'Rua 2');
INSERT INTO PATH(ADDRESSIDA, ADDRESSIDB, NAME) VALUES (3,4, 'Rua 3');