package com.lazybums.snackup.database;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;
import com.lazybums.snackup.constants.Constants;

/**
 * Created by amsingha on 9/5/2015.
 */
@DynamoDBTable(tableName = Constants.MALL_TABLE_NAME)
public class CityMallTable {
    private String City;
    private String Malls;

    @DynamoDBHashKey(attributeName = "City")
    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    @DynamoDBAttribute(attributeName = "Malls")
    public String getMalls() {
        return Malls;
    }

    public void setMalls(String Malls) {
        this.Malls = Malls;
    }
}