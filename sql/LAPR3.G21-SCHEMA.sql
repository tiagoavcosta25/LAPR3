DROP TABLE Address CASCADE CONSTRAINTS;
DROP TABLE Path CASCADE CONSTRAINTS;
DROP TABLE "User" CASCADE CONSTRAINTS;
DROP TABLE Administrator CASCADE CONSTRAINTS;
DROP TABLE Client CASCADE CONSTRAINTS;
DROP TABLE Pharmacy CASCADE CONSTRAINTS;
DROP TABLE Courier CASCADE CONSTRAINTS;
DROP TABLE OrderStatus CASCADE CONSTRAINTS;
DROP TABLE "Order" CASCADE CONSTRAINTS;
DROP TABLE CreditCard CASCADE CONSTRAINTS;
DROP TABLE CreditCardClient CASCADE CONSTRAINTS;
DROP TABLE DeliveryStatus CASCADE CONSTRAINTS;
DROP TABLE DeliveryRun CASCADE CONSTRAINTS;
DROP TABLE Invoice CASCADE CONSTRAINTS;
DROP TABLE InvoiceLine CASCADE CONSTRAINTS;
DROP TABLE Product CASCADE CONSTRAINTS;
DROP TABLE OrderProduct CASCADE CONSTRAINTS;
DROP TABLE Park CASCADE CONSTRAINTS;
DROP TABLE ChargingSlot CASCADE CONSTRAINTS;
DROP TABLE PaymentMethodInvoice CASCADE CONSTRAINTS;
DROP TABLE PharmacyProduct CASCADE CONSTRAINTS;
DROP TABLE Scooter CASCADE CONSTRAINTS;
DROP TABLE ChargingStatus CASCADE CONSTRAINTS;
DROP TABLE ParkingSlot CASCADE CONSTRAINTS;
DROP TABLE Drone CASCADE CONSTRAINTS;
DROP TABLE PharmacyTransfer CASCADE CONSTRAINTS;
DROP TABLE Battery CASCADE CONSTRAINTS;
DROP TABLE Vehicle CASCADE CONSTRAINTS;
DROP TABLE VehicleType CASCADE CONSTRAINTS;


CREATE TABLE Address (id number(10) GENERATED AS IDENTITY, latitude double precision NOT NULL, longitude double precision NOT NULL, doorNumber varchar2(40) NOT NULL, streetName varchar2(100) NOT NULL, postalCode varchar2(8) NOT NULL, locality varchar2(70) NOT NULL, country varchar2(50) NOT NULL, PRIMARY KEY (id));
CREATE TABLE Administrator (userId number(10) NOT NULL, PRIMARY KEY (userId));
CREATE TABLE Battery (id number(10) GENERATED AS IDENTITY, batteryPerc float(5) NOT NULL, batteryCapacity number(10) NOT NULL, batteryVoltage float(10) NOT NULL, PRIMARY KEY (id));
CREATE TABLE ChargingSlot (id number(10) GENERATED AS IDENTITY, vehicleType varchar2(20) NOT NULL, outputCurrent float(10) NOT NULL, pharmacyId number(10) NOT NULL, vehicleId number(10) NOT NULL, PRIMARY KEY (id));
CREATE TABLE ChargingStatus (designation varchar2(20) NOT NULL, PRIMARY KEY (designation));
CREATE TABLE Client (userId number(10) NOT NULL, credits number(6) DEFAULT 0 NOT NULL, addressId number(10) NOT NULL, PRIMARY KEY (userId));
CREATE TABLE Courier (userId number(10) NOT NULL, iban varchar2(34) NOT NULL, pharmacyId number(10) NOT NULL, PRIMARY KEY (userId));
CREATE TABLE CreditCard (creditCardNr number(16), validityDate date NOT NULL, ccv number(3) NOT NULL, PRIMARY KEY (creditCardNr));
CREATE TABLE CreditCardClient (creditCardNr number(16) NOT NULL, clientId number(10) NOT NULL, PRIMARY KEY (creditCardNr, clientId));
CREATE TABLE DeliveryRun (id number(10) GENERATED AS IDENTITY, courierId number(10) NOT NULL, deliveryStatus varchar2(20) NOT NULL, vehicleid number(10) NOT NULL, PRIMARY KEY (id));
CREATE TABLE DeliveryStatus (designation varchar2(20) NOT NULL, PRIMARY KEY (designation));
CREATE TABLE Drone (vehicleId number(10) NOT NULL, PRIMARY KEY (vehicleId));
CREATE TABLE Invoice (id number(10) GENERATED AS IDENTITY, orderId number(10) NOT NULL UNIQUE, invoiceDate date NOT NULL, totalPrice float(10) NOT NULL, PRIMARY KEY (id));
CREATE TABLE InvoiceLine (invoiceId number(10) NOT NULL, id number(10) NOT NULL, orderId number(10) NOT NULL, productId number(10) NOT NULL, value float(10) NOT NULL, PRIMARY KEY (invoiceId, id));
CREATE TABLE "Order" (id number(10) GENERATED AS IDENTITY, clientId number(10) NOT NULL, description varchar2(255), orderStatus varchar2(20) NOT NULL, orderDate date NOT NULL, addressId number(10), pharmacyId number(10) NOT NULL, deliveryRunId number(10) NOT NULL, totalWeight float(10) NOT NULL, amount float(10) NOT NULL, additionalFee float(10) DEFAULT 0 NOT NULL, PRIMARY KEY (id));
CREATE TABLE OrderProduct (productId number(10) NOT NULL, orderId number(10) NOT NULL, quantity number(5) NOT NULL, PRIMARY KEY (productId, orderId));
CREATE TABLE OrderStatus (designation varchar2(20) DEFAULT 'ordered' NOT NULL, PRIMARY KEY (designation));
CREATE TABLE Park (Pharmacyid number(10) NOT NULL, maxSlotsNumber number(3) NOT NULL, PRIMARY KEY (Pharmacyid));
CREATE TABLE ParkingSlot (id number(10) GENERATED AS IDENTITY, vehicleType varchar2(20) NOT NULL, pharmacyId number(10) NOT NULL, vehicleId number(10) NOT NULL, PRIMARY KEY (id));
CREATE TABLE Path (addressIdA number(10) NOT NULL, addressIdB number(10) NOT NULL, name nvarchar2(255) NOT NULL, PRIMARY KEY (addressIdA, addressIdB));
CREATE TABLE PaymentMethodInvoice (creditCardNr number(16) NOT NULL, invoiceId number(10) NOT NULL, value float(10) NOT NULL, PRIMARY KEY (creditCardNr, invoiceId));
CREATE TABLE Pharmacy (id number(10) GENERATED AS IDENTITY, name varchar2(70) NOT NULL, addressId number(10) NOT NULL, PRIMARY KEY (id));
CREATE TABLE PharmacyProduct (pharmacyId number(10) NOT NULL, productId number(10) NOT NULL, stock number(10) DEFAULT 0 NOT NULL, PRIMARY KEY (pharmacyId, productId));
CREATE TABLE PharmacyTransfer (id number(10) GENERATED AS IDENTITY, nearbyPharmacyId number(10) NOT NULL, transferDate date NOT NULL, productId number(10) NOT NULL, orderId number(10) NOT NULL, PRIMARY KEY (id));
CREATE TABLE Product (id number(10) GENERATED AS IDENTITY, name varchar2(70) NOT NULL UNIQUE, description varchar2(255), unitaryPrice float(10) NOT NULL, unitaryWeight float(10) NOT NULL, PRIMARY KEY (id));
CREATE TABLE Scooter (vehicleId number(10) NOT NULL, PRIMARY KEY (vehicleId));
CREATE TABLE "User" (id number(10) GENERATED AS IDENTITY, email varchar2(320) NOT NULL UNIQUE, password varchar2(128) NOT NULL, nif number(10) NOT NULL UNIQUE, name varchar2(100) NOT NULL, PRIMARY KEY (id));
CREATE TABLE Vehicle (id number(10) GENERATED AS IDENTITY, pharmacyId number(10) NOT NULL, batteryId number(10) NOT NULL, vehicleType varchar2(20) NOT NULL, potency float(10) NOT NULL, weight float(10) NOT NULL, maxPayload float(10) NOT NULL, chargingStatus varchar2(20) NOT NULL, PRIMARY KEY (id));
CREATE TABLE VehicleType (designation varchar2(20) NOT NULL, PRIMARY KEY (designation));
ALTER TABLE Courier ADD CONSTRAINT FKCourier212758 FOREIGN KEY (userId) REFERENCES "User" (id);
ALTER TABLE Client ADD CONSTRAINT FKClient741658 FOREIGN KEY (userId) REFERENCES "User" (id);
ALTER TABLE Client ADD CONSTRAINT FKClient338409 FOREIGN KEY (addressId) REFERENCES Address (id);
ALTER TABLE "Order" ADD CONSTRAINT FKOrder16232 FOREIGN KEY (clientId) REFERENCES Client (userId);
ALTER TABLE Administrator ADD CONSTRAINT FKAdministra650549 FOREIGN KEY (userId) REFERENCES "User" (id);
ALTER TABLE InvoiceLine ADD CONSTRAINT FKInvoiceLin942482 FOREIGN KEY (invoiceId) REFERENCES Invoice (id);
ALTER TABLE Pharmacy ADD CONSTRAINT FKPharmacy831467 FOREIGN KEY (addressId) REFERENCES Address (id);
ALTER TABLE PharmacyProduct ADD CONSTRAINT FKPharmacyPr239106 FOREIGN KEY (pharmacyId) REFERENCES Pharmacy (id);
ALTER TABLE PharmacyProduct ADD CONSTRAINT FKPharmacyPr488377 FOREIGN KEY (productId) REFERENCES Product (id);
ALTER TABLE ParkingSlot ADD CONSTRAINT FKParkingSlo991906 FOREIGN KEY (pharmacyId) REFERENCES Park (Pharmacyid);
ALTER TABLE ChargingSlot ADD CONSTRAINT FKChargingSl134584 FOREIGN KEY (pharmacyId) REFERENCES Park (Pharmacyid);
ALTER TABLE DeliveryRun ADD CONSTRAINT FKDeliveryRu756281 FOREIGN KEY (courierId) REFERENCES Courier (userId);
ALTER TABLE Invoice ADD CONSTRAINT FKInvoice770217 FOREIGN KEY (orderId) REFERENCES "Order" (id);
ALTER TABLE OrderProduct ADD CONSTRAINT FKOrderProdu234573 FOREIGN KEY (productId) REFERENCES Product (id);
ALTER TABLE InvoiceLine ADD CONSTRAINT FKInvoiceLin581847 FOREIGN KEY (productId, orderId) REFERENCES OrderProduct (productId, orderId);
ALTER TABLE PaymentMethodInvoice ADD CONSTRAINT FKPaymentMet386868 FOREIGN KEY (creditCardNr) REFERENCES CreditCard (creditCardNr);
ALTER TABLE PaymentMethodInvoice ADD CONSTRAINT FKPaymentMet430112 FOREIGN KEY (invoiceId) REFERENCES Invoice (id);
ALTER TABLE CreditCardClient ADD CONSTRAINT FKCreditCard210344 FOREIGN KEY (creditCardNr) REFERENCES CreditCard (creditCardNr);
ALTER TABLE CreditCardClient ADD CONSTRAINT FKCreditCard659380 FOREIGN KEY (clientId) REFERENCES Client (userId);
ALTER TABLE OrderProduct ADD CONSTRAINT FKOrderProdu772110 FOREIGN KEY (orderId) REFERENCES "Order" (id);
ALTER TABLE DeliveryRun ADD CONSTRAINT FKDeliveryRu899164 FOREIGN KEY (deliveryStatus) REFERENCES DeliveryStatus (designation);
ALTER TABLE "Order" ADD CONSTRAINT FKOrder414266 FOREIGN KEY (orderStatus) REFERENCES OrderStatus (designation);
ALTER TABLE "Order" ADD CONSTRAINT FKOrder962887 FOREIGN KEY (addressId) REFERENCES Address (id);
ALTER TABLE "Order" ADD CONSTRAINT FKOrder50057 FOREIGN KEY (pharmacyId) REFERENCES Pharmacy (id);
ALTER TABLE Courier ADD CONSTRAINT FKCourier219859 FOREIGN KEY (pharmacyId) REFERENCES Pharmacy (id);
ALTER TABLE Path ADD CONSTRAINT FKPath480618 FOREIGN KEY (addressIdA) REFERENCES Address (id);
ALTER TABLE Path ADD CONSTRAINT FKPath480619 FOREIGN KEY (addressIdB) REFERENCES Address (id);
ALTER TABLE PharmacyTransfer ADD CONSTRAINT FKPharmacyTr532715 FOREIGN KEY (productId) REFERENCES Product (id);
ALTER TABLE PharmacyTransfer ADD CONSTRAINT FKPharmacyTr526031 FOREIGN KEY (orderId) REFERENCES "Order" (id);
ALTER TABLE PharmacyTransfer ADD CONSTRAINT FKPharmacyTr333233 FOREIGN KEY (nearbyPharmacyId) REFERENCES Pharmacy (id);
ALTER TABLE Scooter ADD CONSTRAINT FKScooter798348 FOREIGN KEY (vehicleId) REFERENCES Vehicle (id);
ALTER TABLE Drone ADD CONSTRAINT FKDrone895710 FOREIGN KEY (vehicleId) REFERENCES Vehicle (id);
ALTER TABLE Vehicle ADD CONSTRAINT FKVehicle12372 FOREIGN KEY (chargingStatus) REFERENCES ChargingStatus (designation);
ALTER TABLE DeliveryRun ADD CONSTRAINT FKDeliveryRu625835 FOREIGN KEY (vehicleid) REFERENCES Vehicle (id);
ALTER TABLE Park ADD CONSTRAINT FKPark427298 FOREIGN KEY (Pharmacyid) REFERENCES Pharmacy (id);
ALTER TABLE ParkingSlot ADD CONSTRAINT FKParkingSlo290138 FOREIGN KEY (vehicleId) REFERENCES Vehicle (id);
ALTER TABLE ChargingSlot ADD CONSTRAINT FKChargingSl852538 FOREIGN KEY (vehicleId) REFERENCES Vehicle (id);
ALTER TABLE "Order" ADD CONSTRAINT FKOrder724574 FOREIGN KEY (deliveryRunId) REFERENCES DeliveryRun (id);
ALTER TABLE Vehicle ADD CONSTRAINT FKVehicle650965 FOREIGN KEY (pharmacyId) REFERENCES Pharmacy (id);
ALTER TABLE ChargingSlot ADD CONSTRAINT FKChargingSl179944 FOREIGN KEY (vehicleType) REFERENCES VehicleType (designation);
ALTER TABLE ParkingSlot ADD CONSTRAINT FKParkingSlo648969 FOREIGN KEY (vehicleType) REFERENCES VehicleType (designation);
ALTER TABLE Vehicle ADD CONSTRAINT FKVehicle813452 FOREIGN KEY (batteryId) REFERENCES Battery (id);

