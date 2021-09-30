CREATE TRIGGER modifyDateOrder
AFTER UPDATE OF delivery_mark, delivery_date, address, comm, su_id
ON order_
BEGIN
    UPDATE order_ SET modified_date = TIMESTAMP() WHERE order_id = NEW.order_id;
END;

CREATE TRIGGER modifyDateOrder
AFTER UPDATE OF order_status
ON order_
BEGIN
    UPDATE order_ SET modified_date_os = TIMESTAMP() WHERE order_id = NEW.order_id;
    IF NEW.order_status = 4
    THEN
        UPDATE sales_unit SET quantity = quantity + 1 WHERE su_id = NEW.su_id AND shop_id = NEW.shop_id AND product_id = NEW.product_if;
    END IF;
END;

CREATE or replace TRIGGER modifyDateProduct
    FOR UPDATE of desc_, article, product_model, weight, dimensions, date_released, category_id, manufacturer_id  ON product
    compound trigger
    bUpdPainters  boolean;
    pId number;
before each row is
begin
    if :NEW.PRODUCT_ID IS NOT NULL then
        bUpdPainters := true;
        pId := :NEW.product_id;
    end if;
end before each row;
    after statement is
    begin
        if bUpdPainters then
            UPDATE product SET modified_date = CURRENT_DATE WHERE product_id = pId;
        end if;
    end after statement;
    end modifyDateProduct;

CREATE TRIGGER modifyDateSU
AFTER UPDATE 
ON sales_unit
BEGIN
    UPDATE sales_unit SET modified_date = TIMESTAMP() WHERE su_id = NEW.su_id;
END;

CREATE TRIGGER modifyDateShop
AFTER UPDATE
ON shop
BEGIN
    UPDATE shop SET modified_date = TIMESTAMP() WHERE shop_id = NEW.shop_id;
END;
//Проверить
CREATE TRIGGER checkorder
BEFORE DELETE
ON shop
BEGIN
    IF EXISTS(SELECT * FROM order_ WHERE shop_id = NEW.shop_id AND (order_status != 4 OR order_status != 5))
    THEN
        RAISE_APPLICATION_ERROR(-20000,'There are open orders');
    END IF;
END;
//Проверить
CREATE TRIGGER minusSU
AFTER INSERT
ON order_
BEGIN
    UPDATE sales_unit SET quantity = quantity - 1 WHERE su_id = NEW.su_id AND shop_id = NEW.shop_id AND product_id = NEW.product_if;
END;