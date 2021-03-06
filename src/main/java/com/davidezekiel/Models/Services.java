package com.davidezekiel.Models;

import com.davidezekiel.Models.Everything.Article;
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
    public static List<Article> processAllStuff(Response response) {
        ArrayList<Article> results = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            logger.info("jsonData: " + jsonData);
            if (response.isSuccessful()){
                JSONObject jsonObject = new JSONObject(jsonData);
                JSONArray jsonArray = jsonObject.getJSONArray("articles");
                Type collectionType = new TypeToken<List<Article>>() {
                }.getType();
                Gson gson = new GsonBuilder().create();
                results = gson.fromJson(jsonArray.toString(),collectionType);
            }
        } catch (JSONException | NullPointerException | IOException e){
            e.printStackTrace();
        }
        return results;
    }

    //For Technology
    public static List<Article> processTechNews(Response response) throws IOException{
        ArrayList<Article> techResults = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()){
                JSONObject jsonObject = new JSONObject(jsonData);
                JSONArray jsonArray = jsonObject.getJSONArray("articles");
                Type collection = new TypeToken<List<Article>>(){}.getType();
                Gson gson = new GsonBuilder().create();
                techResults = gson.fromJson(jsonArray.toString(),collection);
            }
        } catch (JSONException | NullPointerException | IOException e){
            e.printStackTrace();
            logger.info(e.getMessage());
        }
        return techResults;
    }

    //For Business
    public  static List<Article> processBusinessNews(Response response)throws IOException{
        ArrayList<Article> businessResults = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("articles");
            Type collectionType = new TypeToken<List<Article>>(){}.getType();
            Gson gson = new GsonBuilder().create();
            businessResults = gson.fromJson(jsonArray.toString(),collectionType);
        } catch (JSONException | NullPointerException e){
            logger.info(e.getMessage());
        }
        return businessResults;
    }

    //For Sports
    public static List<Article> processSportsNews(Response response) throws IOException{
        ArrayList<Article> sportsResults = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("articles");
            Type collectionType = new TypeToken<List<Article>>(){}.getType();
            Gson gson = new GsonBuilder().create();
            sportsResults = gson.fromJson(jsonArray.toString(),collectionType);
        } catch (JSONException | NullPointerException e){
            logger.info(e.getMessage());
        }
        return sportsResults;
    }

    //For Health
    public static List<Article> processHealthNews(Response response) throws IOException {
        ArrayList<Article> healthResults = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("articles");
            Type collectionType = new TypeToken<List<Article>>() {
            }.getType();
            Gson gson = new GsonBuilder().create();
            healthResults = gson.fromJson(jsonArray.toString(), collectionType);
        } catch (JSONException | NullPointerException e) {
            logger.info(e.getMessage());
        }
        return healthResults;
    }

    //For TV&Showbiz
    public static List<Article> processTvShowbizNews(Response response) throws IOException {
        ArrayList<Article> TvShowbizNewsResults = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("articles");
            Type collectionType = new TypeToken<List<Article>>() {
            }.getType();
            Gson gson = new GsonBuilder().create();
            TvShowbizNewsResults = gson.fromJson(jsonArray.toString(), collectionType);
        } catch (JSONException | NullPointerException e) {
            logger.info(e.getMessage());
        }
        return TvShowbizNewsResults;
    }
}