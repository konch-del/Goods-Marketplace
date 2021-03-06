CREATE TABLE Category_
(
    Category_ID          INTEGER  NOT NULL ,
    Parent_ID            INTEGER  NULL ,
    Category_name        VARCHAR2(100)  NOT NULL
);

CREATE UNIQUE INDEX XPKCategory ON Category_
(Category_ID   ASC);

ALTER TABLE Category_
	ADD CONSTRAINT  XPKCategory PRIMARY KEY (Category_ID);

CREATE TABLE Characteristic
(
	Charact_ID           INTEGER  NOT NULL ,
	Character_name       VARCHAR2(300)  NOT NULL ,
	Type_character       VARCHAR2(10)  NOT NULL ,
	Category_ID          INTEGER  NOT NULL
);

CREATE UNIQUE INDEX XPKCharacteristic ON Characteristic
(Charact_ID   ASC);

ALTER TABLE Characteristic
	ADD CONSTRAINT  XPKCharacteristic PRIMARY KEY (Charact_ID);

CREATE TABLE Manufacturer
(
	Manufacturer_ID      INTEGER  NOT NULL ,
	Manufacturer_name    VARCHAR2(70)  NOT NULL ,
	Desc_                VARCHAR2(1000)  NULL ,
	ID_picture           VARCHAR2(500)  NULL
);

CREATE UNIQUE INDEX XPKManufacturer ON Manufacturer
(Manufacturer_ID   ASC);

ALTER TABLE Manufacturer
	ADD CONSTRAINT  XPKManufacturer PRIMARY KEY (Manufacturer_ID);

CREATE TABLE Users_
(
	User_ID              INTEGER  NOT NULL ,
	Account_type        VARCHAR2(20)  NOT NULL ,
	Username             VARCHAR2(50)  NOT NULL ,
	Pass                 VARCHAR2(300)  NOT NULL ,
	FCS                  VARCHAR2(300)  NOT NULL ,
	City                 VARCHAR2(200)  NOT NULL ,
	Address              VARCHAR2(200)  NULL ,
	Email                VARCHAR2(100)  NOT NULL ,
	Phone_number         VARCHAR(12)  NOT NULL ,
	Date_of_Creation     DATE  NOT NULL ,
	Last_login_date      DATE  NOT NULL ,
	Activation           SMALLINT  NOT NULL ,
	Shop_ID              INTEGER  NULL
);

CREATE UNIQUE INDEX XPKUsers ON Users_
(User_ID   ASC);

ALTER TABLE Users_
	ADD CONSTRAINT  XPKUsers PRIMARY KEY (User_ID);

CREATE TABLE Order_
(
	Order_ID             INTEGER  NOT NULL ,
	Delivery_mark        SMALLINT  NOT NULL ,
	Delivery_date        DATE  NULL ,
	Address              VARCHAR2(200)  NOT NULL ,
	Comm                 VARCHAR2(500)  NULL ,
	Order_status         VARCHAR2(50)  NOT NULL ,
	Date_created         DATE  NOT NULL ,
	Modified_date        DATE  NOT NULL ,
	Modified_date_OS     DATE  NOT NULL ,
	User_ID              INTEGER  NOT NULL ,
	SU_ID                INTEGER  NOT NULL
);

CREATE UNIQUE INDEX XPKOrder ON Order_
(Order_ID   ASC);

ALTER TABLE Order_
	ADD CONSTRAINT  XPKOrder PRIMARY KEY (Order_ID);

CREATE TABLE Shop
(
	Shop_ID              INTEGER  NOT NULL ,
	Shop_name            VARCHAR2(200)  NOT NULL ,
	Desc_                VARCHAR2(500)  NULL ,
	Email                VARCHAR2(100)  NULL ,
	Phone_number         INTEGER  NULL ,
	Work_time            VARCHAR2(200)  NULL ,
	Address              VARCHAR2(200)  NULL ,
	Date_create          DATE  NOT NULL ,
	Modified_date        DATE  NOT NULL
);

CREATE UNIQUE INDEX XPKShop ON Shop
(Shop_ID   ASC);

ALTER TABLE Shop
	ADD CONSTRAINT  XPKShop PRIMARY KEY (Shop_ID);

CREATE TABLE Product
(
	Product_ID           INTEGER  NOT NULL ,
	Product_name         VARCHAR2(100)  NOT NULL ,
	Product_model        VARCHAR2(75)  NULL ,
	Article              VARCHAR2(20)  NULL ,
	Desc_                VARCHAR2(100)  NULL ,
	Weight               INTEGER  NOT NULL ,
	Dimensions           VARCHAR2(100)  NOT NULL ,
	Date_released        DATE  NULL ,
	Date_creation        DATE  NOT NULL ,
	Modified_date        DATE  NOT NULL ,
	Category_ID          INTEGER  NULL ,
	Manufacturer_ID      INTEGER  NULL
);

CREATE UNIQUE INDEX XPKProduct ON Product
(Product_ID   ASC);

ALTER TABLE Product
	ADD CONSTRAINT  XPKProduct PRIMARY KEY (Product_ID);

CREATE TABLE Picture_product
(
	PP_ID                INTEGER  NOT NULL ,
	Picture_ID           VARCHAR(500),
	Date_created         DATE  NOT NULL ,
	Product_ID           INTEGER  NOT NULL
);

CREATE UNIQUE INDEX XPKPicture_product ON Picture_product
(PP_ID   ASC);

ALTER TABLE Picture_product
	ADD CONSTRAINT  XPKPicture_product PRIMARY KEY (PP_ID);

CREATE TABLE Feedback_
(
	Feedback_ID          INTEGER  NOT NULL ,
	Visibility           SMALLINT  NOT NULL ,
	Text_fb              VARCHAR2(1000)  NULL ,
	Rating               INTEGER  NOT NULL ,
	Date_created         DATE  NOT NULL ,
	User_ID              INTEGER  NOT NULL ,
	Product_ID           INTEGER  NOT NULL ,
	Order_ID             INTEGER
);

CREATE UNIQUE INDEX XPKFeedback ON Feedback_
(Feedback_ID   ASC);

ALTER TABLE Feedback_
	ADD CONSTRAINT  XPKFeedback PRIMARY KEY (Feedback_ID);

CREATE TABLE Sales_unit
(
	SU_ID                INTEGER  NOT NULL ,
	Quantity             INTEGER  NOT NULL ,
	Price                DECIMAL(19,4)  NOT NULL ,
	Shipping_cost        DECIMAL(19,4)  NULL ,
	Desc_                VARCHAR2(1000)  NULL ,
	Date_created         DATE  NOT NULL ,
	Modified_date        DATE  NOT NULL ,
	Shop_ID              INTEGER  NOT NULL ,
	Product_ID           INTEGER  NOT NULL
);

CREATE UNIQUE INDEX XPKSales_unit ON Sales_unit
(SU_ID   ASC);

ALTER TABLE Sales_unit
	ADD CONSTRAINT  XPKSales_unit PRIMARY KEY (SU_ID);

CREATE TABLE Values_character
(
	VC_ID                INTEGER  NOT NULL ,
	Val                  VARCHAR2(500)  NOT NULL ,
	Charact_ID           INTEGER  NOT NULL
);

CREATE UNIQUE INDEX XPKValues_character ON Values_character
(VC_ID   ASC);

ALTER TABLE Values_character
	ADD CONSTRAINT  XPKValues_character PRIMARY KEY (VC_ID);

CREATE TABLE Product_Values_character
(
	PVC_ID INTEGER NOT NULL,
	Product_ID           INTEGER  NOT NULL ,
	VC_ID                INTEGER  NOT NULL
);

CREATE UNIQUE INDEX XPKProduct_Values_character ON Product_Values_character
(PVC_ID   ASC);

ALTER TABLE Product_Values_character
	ADD CONSTRAINT  XPKProduct_Values_character PRIMARY KEY (PVC_ID);

ALTER TABLE Category_
	ADD (
CONSTRAINT Category_to_Category FOREIGN KEY (Parent_ID) REFERENCES Category_ (Category_ID) ON DELETE SET NULL);

ALTER TABLE Characteristic
	ADD (
CONSTRAINT Category_to_Characteristic FOREIGN KEY (Category_ID) REFERENCES Category_ (Category_ID) ON DELETE CASCADE);

ALTER TABLE Users_
	ADD (
CONSTRAINT Shop_to_User FOREIGN KEY (Shop_ID) REFERENCES Shop (Shop_ID) ON DELETE SET NULL);

ALTER TABLE Order_
	ADD (
CONSTRAINT User_to_Order FOREIGN KEY (User_ID) REFERENCES Users_ (User_ID) ON DELETE CASCADE);

ALTER TABLE Order_
	ADD (
CONSTRAINT SU_to_Order FOREIGN KEY (SU_ID) REFERENCES Sales_unit (SU_ID));

ALTER TABLE Product
	ADD (
CONSTRAINT Category_to_Product FOREIGN KEY (Category_ID) REFERENCES Category_ (Category_ID) ON DELETE SET NULL);

ALTER TABLE Product
	ADD (
CONSTRAINT Manufacturer_to_Product FOREIGN KEY (Manufacturer_ID) REFERENCES Manufacturer (Manufacturer_ID) ON DELETE SET NULL);

ALTER TABLE Picture_product
	ADD (
CONSTRAINT Product_to_PP FOREIGN KEY (Product_ID) REFERENCES Product (Product_ID) ON DELETE CASCADE);

ALTER TABLE Feedback_
	ADD (
CONSTRAINT User_to_Feedback FOREIGN KEY (User_ID) REFERENCES Users_ (User_ID));

ALTER TABLE Feedback_
	ADD (
CONSTRAINT Product_to_Feedback FOREIGN KEY (Product_ID) REFERENCES Product (Product_ID) ON DELETE CASCADE);

ALTER TABLE Feedback_
	ADD (
CONSTRAINT Order_Feedback FOREIGN KEY (Order_ID) REFERENCES Order_ (Order_ID) ON DELETE CASCADE);

ALTER TABLE Sales_unit
	ADD (
CONSTRAINT Shop_to_SU FOREIGN KEY (Shop_ID) REFERENCES Shop (Shop_ID) ON DELETE CASCADE);

ALTER TABLE Sales_unit
	ADD (
CONSTRAINT Product_to_SU FOREIGN KEY (Product_ID) REFERENCES Product (Product_ID) ON DELETE CASCADE);

ALTER TABLE Values_character
	ADD (
CONSTRAINT Characteristic_to_ValuesCh FOREIGN KEY (Charact_ID) REFERENCES Characteristic (Charact_ID) ON DELETE CASCADE);

ALTER TABLE Product_Values_character
	ADD (
CONSTRAINT Product_to_VC FOREIGN KEY (Product_ID) REFERENCES Product (Product_ID) ON DELETE CASCADE);

ALTER TABLE Product_Values_character
	ADD (
CONSTRAINT VC_to_Product FOREIGN KEY (VC_ID) REFERENCES Values_character (VC_ID) ON DELETE CASCADE);
