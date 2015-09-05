package com.lazybums.snackup.database;

import android.content.Context;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import static com.lazybums.snackup.constants.Constants.ACCOUNT_ID;
import static com.lazybums.snackup.constants.Constants.DYNAMODB_REGION;
import static com.lazybums.snackup.constants.Constants.IDENTITY_POOL_ID;
import static com.lazybums.snackup.constants.Constants.UNAUTH_ROLE_ARN;

/**
 * Created by amsingha on 9/5/2015.
 */
public class DynamoDBClientManager {

    private Context context;
    private AmazonDynamoDBClient ddb = null;


    public DynamoDBClientManager(Context context) {
        this.context = context;
    }

    public AmazonDynamoDBClient ddb() {
        validateCredentials();
        return ddb;
    }

    public void validateCredentials() {

        if (ddb == null) {
            initClients();
        }
    }

    private void initClients() {
        CognitoCachingCredentialsProvider credentials = new CognitoCachingCredentialsProvider(
                context,
                ACCOUNT_ID,
                IDENTITY_POOL_ID,
                UNAUTH_ROLE_ARN,
                null,
                DYNAMODB_REGION);

        ddb = new AmazonDynamoDBClient(credentials);
        ddb.setRegion(Region.getRegion(DYNAMODB_REGION));
    }
}
