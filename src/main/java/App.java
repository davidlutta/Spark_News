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
    }
}
