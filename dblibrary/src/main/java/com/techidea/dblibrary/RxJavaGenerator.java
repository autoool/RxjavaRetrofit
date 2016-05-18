package com.techidea.dblibrary;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

/**
 * Created by zchao on 2016/4/1.
 */
public class RxJavaGenerator {

    public static void main(String[] args) throws Exception {
        Schema schma = new Schema(1, "com.techidea.dblibrary.db");
        addPrintDataItem(schma);
//        addCustomerOrder(schma);
//        addUserInfo(schma);
//        addProductCategory(schma);
//        addOrder(schma);
        addItem(schma);
        new DaoGenerator().generateAll(schma, "../RxjavaDemo/dblibrary/src/main/java/");
    }

    public static void addUserInfo(Schema schema) {
        Entity userInfo = schema.addEntity("UserInfo");
        userInfo.addIdProperty();
        userInfo.addStringProperty("type");
        userInfo.addStringProperty("username");
    }

    public static void addProductCategory(Schema schema) {
        Entity productCategory = schema.addEntity("ProductCategory");
        productCategory.addIdProperty();
        productCategory.addStringProperty("name");
    }

    public static void addOrder(Schema schema) {
        Entity order = schema.addEntity("Order");
        order.addIdProperty();
        order.addStringProperty("orderNo");
        order.addStringProperty("orderId");

        Entity orderline = schema.addEntity("OrderLine");
        orderline.setTableName("ORDERLINE"); // "ORDER" is a reserved keyword
        orderline.addIdProperty();
        orderline.addStringProperty("productId");
        orderline.addStringProperty("title");
        orderline.addStringProperty("itemMakeTags");
        Property orderId = orderline.addLongProperty("orderId").notNull().getProperty();
        orderline.addToOne(order, orderId);

        ToMany orderToOrderlines = order.addToMany(orderline, orderId);
        orderToOrderlines.setName("orderlines");

//        Entity productItem = schema.addEntity("ProductItem");
//        productItem.setTableName("PRODUCTITEM");
//        productItem.addIdProperty();
//        productItem.addStringProperty("title");
//        productItem.addStringProperty("productId");

//        Property orderlineId = productItem.addLongProperty("orderlineId").notNull().getProperty();
//        productItem.addToOne(orderline, orderlineId);
//        Entity salesProp = schema.addEntity("SalesProp");
//        salesProp.addIdProperty();
//        salesProp.addStringProperty("title");
//        salesProp.addStringProperty("price");

//        Entity salesProp1 = schema.addEntity("SalesProp1");
//        salesProp1.addIdProperty();
//        salesProp1.addStringProperty("title");
//        salesProp1.addStringProperty("price");

//        Property salesId1 = productItem.addLongProperty("salespropId1").notNull().getProperty();
//        productItem.addToOne(salesProp, salesId1);
//        Property salesId2 = productItem.addLongProperty("salespropId2").notNull().getProperty();
//        productItem.addToOne(salesProp, salesId2);
    }


    //to many
    public static void addCustomerOrder(Schema schema) {
        Entity customer = schema.addEntity("Customer");
        customer.addIdProperty();
        customer.addStringProperty("name").notNull();

        Entity order = schema.addEntity("Order");
        order.setTableName("ORDERS"); // "ORDER" is a reserved keyword
        order.addIdProperty();
        Property orderDate = order.addDateProperty("date").getProperty();
        Property customerId = order.addLongProperty("customerId").notNull().getProperty();
        order.addToOne(customer, customerId);

        ToMany customerToOrders = customer.addToMany(order, customerId);
        customerToOrders.setName("orders");
        customerToOrders.orderAsc(orderDate);
    }

    public static void addPrintDataItem(Schema schema) {
        Entity printDataItem = schema.addEntity("PrintDataItem");
        printDataItem.addIdProperty();
        printDataItem.addStringProperty("printdata");
        printDataItem.addIntProperty("position");
        printDataItem.addIntProperty("doubleformat");
        printDataItem.addIntProperty("printermode");
        printDataItem.addLongProperty("inserttime");
    }


    public static void addItem(Schema schema) {
        Entity item = schema.addEntity("Item");
        item.addIdProperty();
        item.addStringProperty("name");


        Entity listItem = schema.addEntity("ListItem");
        listItem.addIdProperty();
        listItem.addStringProperty("name");

        Property listId = item.addLongProperty("listId").notNull().getProperty();
        ToMany listToItem = listItem.addToMany(item, listId);
        listToItem.setName("listitems");

    }
}
