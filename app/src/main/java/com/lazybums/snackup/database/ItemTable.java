package com.lazybums.snackup.database;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;
import com.lazybums.snackup.constants.Constants;

/**
 * Created by amsingha on 9/5/2015.
 */
@DynamoDBTable(tableName = Constants.ITEM_TABLE_NAME)
public class ItemTable {
    private String City_Mall;
    private String Vendor;
    private String Items;

    @DynamoDBHashKey(attributeName = "City_Mall")
    public String getCityMall() {
        return City_Mall;
    }

    public void setCityMall(String City_Mall) {
        this.City_Mall = City_Mall;
    }

    @DynamoDBRangeKey(attributeName = "Vendor")
    public String getVendor() {
        return Vendor;
    }

    public void setVendor(String vendor) {
        Vendor = vendor;
    }

    @DynamoDBAttribute(attributeName = "Items")
    public String getItems() {
        return Items;
    }

    public void setItems(String items) {
        Items = items;
    }
}