import static spark.Spark.staticFileLocation;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        //layout template
        String layout = "templates/layout.vtl";
    }
}
