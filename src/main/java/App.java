import com.davidezekiel.Models.Everything.Article;
import com.davidezekiel.Models.Services;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

public class App {
    static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        OkHttpClient okHttpClient = new OkHttpClient();
        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

        get("/",(request, response) -> {
            Map<String,Object> model = new HashMap<>();

            HttpUrl.Builder everyStuffBuilder = HttpUrl.parse(Constants.BASE_EVERYTHING_URL).newBuilder();

            String url = everyStuffBuilder.build().toString();
            logger.info("The URL is: "+url);

            Request request1 = new Request.Builder()
                    .url(url)
                    .build();
            try (Response response1 = okHttpClient.newCall(request1).execute()){
                List<Article> results = Services.processAllStuff(response1);
                if (results != null) {
                    model.put("news", results);
                }
            } catch (IOException e){
                e.printStackTrace();
            }


            model.put("template","templates/index.vtl");
            return new ModelAndView(model,layout);
        }, new VelocityTemplateEngine());

        get("/tech",(request, response) -> {
            String techVTLpagepath = "templates/tech.vtl";
            Map<String,Object> model = new HashMap<>();

            HttpUrl.Builder techBuilder = HttpUrl.parse(Constants.BASE_TECH_URL).newBuilder();

            String url = techBuilder.build().toString();

            Request techRequest = new Request.Builder()
                    .url(url)
                    .build();

            try(Response techResponse = okHttpClient.newCall(techRequest).execute()){
                List<Article> techResults = Services.processTechNews(techResponse);
                if (techResults != null){
                    model.put("news",techResults);
                }
            } catch (IOException e){
                e.printStackTrace();
                logger.info(e.getMessage());
            }
            model.put("template",techVTLpagepath);
            return new ModelAndView(model,layout);
        }, new VelocityTemplateEngine());

        get("/business",(request, response) -> {
            String path = "templates/business.vtl";
            Map<String,Object> model = new HashMap<>();
            HttpUrl.Builder businessBuilder = HttpUrl.parse(Constants.BASE_BUSINESS_URL).newBuilder();

            String url = businessBuilder.build().toString();

            Request businessRequest = new Request.Builder()
                    .url(url)
                    .build();

            try(Response businessResponse = okHttpClient.newCall(businessRequest).execute()){
                List<Article> businessResults = Services.processTechNews(businessResponse);
                if (businessResults != null){
                    model.put("news",businessResults);
                }
            } catch (IOException e){
                e.printStackTrace();
                logger.info(e.getMessage());
            }
            model.put("template",path);
            return new ModelAndView(model,layout);
        },new VelocityTemplateEngine());

        get("/sports",(request, response) -> {
            String path = "templates/sports.vtl";
            Map<String,Object> model = new HashMap<>();

            HttpUrl.Builder sportsBuilder = HttpUrl.parse(Constants.BASE_SPORTS_URL).newBuilder();

            String url = sportsBuilder.build().toString();

            Request sportsRequest = new Request.Builder()
                    .url(url)
                    .build();

            try(Response sportsResponse = okHttpClient.newCall(sportsRequest).execute()){
                List<Article> sportsResults = Services.processTechNews(sportsResponse);
                if (sportsResults != null){
                    model.put("news",sportsResults);
                }
            } catch (IOException e){
                e.printStackTrace();
                logger.info(e.getMessage());
            }
            model.put("template",path);
            return new ModelAndView(model,layout);
        },new VelocityTemplateEngine());

        get("/health",(request, response) -> {
            String path = "templates/health.vtl";
            Map<String,Object> model = new HashMap<>();
            HttpUrl.Builder healthBuilder = HttpUrl.parse(Constants.BASE_HEALTH_URL).newBuilder();

            String url = healthBuilder.build().toString();

            Request healthRequest = new Request.Builder()
                    .url(url)
                    .build();

            try(Response healthResponse = okHttpClient.newCall(healthRequest).execute()){
                List<Article> healthResults = Services.processTechNews(healthResponse);
                if (healthResults != null){
                    model.put("news",healthResults);
                }
            } catch (IOException e){
                e.printStackTrace();
                logger.info(e.getMessage());
            }
            model.put("template",path);
            return new ModelAndView(model,layout);
        },new VelocityTemplateEngine());

        get("/tv&showbiz",(request, response) -> {
            String path = "templates/tv&showbiz.vtl";
            Map<String,Object> model = new HashMap<>();
            HttpUrl.Builder tvBuilder = HttpUrl.parse(Constants.BASE_TVSHOWBIZ_URL).newBuilder();

            String url = tvBuilder.build().toString();

            Request tvRequest = new Request.Builder()
                    .url(url)
                    .build();

            try(Response tvResponse = okHttpClient.newCall(tvRequest).execute()){
                List<Article> tvResults = Services.processTechNews(tvResponse);
                if (tvResults != null){
                    model.put("news",tvResults);
                }
            } catch (IOException e){
                e.printStackTrace();
                logger.info(e.getMessage());
            }
            model.put("template",path);
            return new ModelAndView(model,layout);
        },new VelocityTemplateEngine());
    }
}
