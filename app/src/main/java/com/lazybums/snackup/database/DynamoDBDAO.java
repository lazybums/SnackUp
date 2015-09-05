package com.lazybums.snackup.database;


import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.lazybums.snackup.constants.Constants;
import com.lazybums.snackup.database.ItemTable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by amsingha on 9/5/2015.
 */
public class DynamoDBDAO {

    public static List<String> getMallList(AmazonDynamoDBClient ddb,  String city) {
        if(null == ddb || null == city || city.isEmpty()) {
            return null;
        }
        DynamoDBMapper mapper = new DynamoDBMapper(ddb);
        CityMallTable cityMallTable = mapper.load(CityMallTable.class, city);
        String mallsString = cityMallTable.getMalls();
        if(null == mallsString || mallsString.isEmpty()) {
            return null;
        }
        List<String> mallsList = Arrays.asList(mallsString.split(Constants.VALUES_SEPARATOR));
        return mallsList;
    }

    public static Map<String, List<String>> getVendorItemsMap(AmazonDynamoDBClient ddb, String city, String mall) {
        if(null == ddb || null == city || city.isEmpty() || null == mall || mall.isEmpty()) {
            return null;
        }
        String hashKey = city + "_" + mall;
        ItemTable hashQuery = new ItemTable();
        hashQuery.setCityMall(hashKey);
        DynamoDBQueryExpression<ItemTable> queryCityMall = new DynamoDBQueryExpression<ItemTable>().withHashKeyValues(hashQuery);

        DynamoDBMapper mapper = new DynamoDBMapper(ddb);
        List<ItemTable> vendorItemList = mapper.query(ItemTable.class, queryCityMall);
        if(vendorItemList.isEmpty()) {
            return null;
        }

        Map<String, List<String>> vendorItemsMap = new HashMap<String, List<String>>();
        for(ItemTable vendorItem : vendorItemList) {
            String vendor = vendorItem.getVendor();
            List<String> itemList = null;
            String itemString = vendorItem.getItems();
            if(!(null == itemString || itemString.isEmpty())) {
                itemList = Arrays.asList(itemString.split(Constants.VALUES_SEPARATOR));
            }
            vendorItemsMap.put(vendor, itemList);
        }
        return vendorItemsMap;
    }

}
