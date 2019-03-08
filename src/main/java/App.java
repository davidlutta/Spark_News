import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

public class App {
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
