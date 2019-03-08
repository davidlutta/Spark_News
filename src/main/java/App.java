import com.davidezekiel.Models.Everything.Results;
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

            HttpUrl.Builder everyStuffBuilder = HttpUrl.parse(Constants.BASE_URL).newBuilder();
            everyStuffBuilder.addEncodedPathSegment(Constants.EVERYTHING_ENDPOINT);
            everyStuffBuilder.addEncodedPathSegment(Constants.API_PREFIX);
            everyStuffBuilder.addEncodedPathSegment(Constants.API_KEY);


//            String url = everyStuffBuilder.build().toString();
            String url = "https://newsapi.org/v2/everything?q=bitcoin&apiKey=b2957d4f828d426eb6f76054f45ca235";
            logger.info("The URL is: "+url);

            Request request1 = new Request.Builder()
                    .url(url)
                    .build();
            try (Response response1 = okHttpClient.newCall(request1).execute()){
                List<Results> results = Services.processAllStuff(response1);
                if (results != null) {
                    model.put("News",results);
                    for (int i = 0; i < results.size(); i++) {
                        logger.info("Data: "+results.get(i).getArticles());
                    }
                }
            } catch (IOException e){
                e.printStackTrace();
            }


            model.put("template","templates/index.vtl");
            return new ModelAndView(model,layout);
        }, new VelocityTemplateEngine());
    }
}
