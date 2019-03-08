import com.davidezekiel.Models.Everything.Results;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

public class App {
//    Logger for json data
    static Logger logger = (Logger) LoggerFactory.getLogger(App.class);

    //Everything
    private static List<Results> process
    public static void main(String[] args) {
        staticFileLocation("/public");
        //layout template
        String layout = "templates/layout.vtl";

        get("/",(request, response) -> {
            Map<String,Object> model = new HashMap<>();
            model.put("template","templates/index.vtl");
            return new ModelAndView(model,layout);
        }, new VelocityTemplateEngine());
    }
}
