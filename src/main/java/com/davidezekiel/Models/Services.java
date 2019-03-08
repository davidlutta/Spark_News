package com.davidezekiel.Models;

import com.davidezekiel.Models.Everything.Results;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Services {
    //    Logger for json data
    static Logger logger = LoggerFactory.getLogger(Services.class);

    //For EveryStuff
    public static List<Results> processAllStuff(Response response){
        List<Results> results = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            logger.info("jsonData: " + jsonData);
            if (response.isSuccessful()){
                JSONObject jsonObject = new JSONObject(jsonData);
                JSONArray jsonArray = jsonObject.getJSONArray("articles");
                Type collectionType = new TypeToken<List<Results>>(){}.getType();
                Gson gson = new GsonBuilder().create();
                results = gson.fromJson(jsonArray.toString(),collectionType);
            }
        } catch (JSONException | NullPointerException | IOException e){
            e.printStackTrace();
        }
//        logger.info("jsonArray: " + results);
        return results;
    }
}
