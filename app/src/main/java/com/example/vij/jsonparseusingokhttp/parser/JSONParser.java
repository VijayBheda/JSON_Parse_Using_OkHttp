package com.example.vij.jsonparseusingokhttp.parser;

import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * @author Pratik Butani
 */
public class JSONParser {

    /********
     * URLS
     *******/
    private static final String MAIN_URL = "http://pratikbutani.x10.mx/json_data.json";

    /**
     * TAGs Defined Here...
     */
    public static final String TAG = "TAG";

    /**
     * Key to Send
     */
    private static final String KEY_USER_ID = "user_id";

    /**
     * Response
     */
    private static Response response;

    /**
     * Get Table Booking Charge
     *
     * @return JSON Object
     */
    public static JSONObject getDataFromWeb() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(MAIN_URL)
                    .build();
            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }

    /**
     * Get Particular Record
     *
     * @param userId UserId
     * @return JSONObject
     */
    public static JSONObject getDataById(int userId) {

        try {
            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormEncodingBuilder()
                    .add(KEY_USER_ID, Integer.toString(userId))
                    .build();

            Request request = new Request.Builder()
                    .url(MAIN_URL)
                    .post(formBody)
                    .build();

            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());

        } catch (IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }
}
