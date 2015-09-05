package com.lazybums.snackup.constants;

import com.amazonaws.regions.Regions;

/**
 * Keep constants in one place.
 */
public class Constants {
    public static final String[] cities = {"", "Hyderabad", "Bangalore"};
    public static final String latitude = "latitude";
    public static final String longitude = "longitude";
    public static final String itemList = "itemList";
    public static final String VALUES_SEPARATOR = ",";
    public static final String ACCOUNT_ID = "648902644685";
    public static final String IDENTITY_POOL_ID = "eu-west-1:7e72853d-4bba-4a36-b4f9-b8e1d7feabcd";
    public static final String UNAUTH_ROLE_ARN = "arn:aws:iam::648902644685:role/Cognito_SnackUpUnauth_Role";
    public static final String MALL_TABLE_NAME = "CityMall";
    public static final String ITEM_TABLE_NAME = "CityMallVendor";
    public static final Regions DYNAMODB_REGION= Regions.EU_WEST_1;
}
