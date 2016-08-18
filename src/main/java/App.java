/**
 * Created by Leonan-Mac on 8/17/16.
 */

import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {

    private static Gson gson = new Gson();

    public static void main(String[] args) {

        //http verb
        //req e res
        //function (method)
        staticFiles.location("/public");

        before("/protected/*", (req, res) -> {
            System.out.print("Before Filter");
        });

        after((req, res) -> {
           System.out.print("After Filter");
        });

        get("/", (req, res) -> {
            return "School of net";
        });

        get("/protected/a", (req, res) -> {
            return "protected filter";
        });

        get("/hello", (req, res) -> {
           return "Hello";
        });

        post("/hello", (req, res) -> action(req, res));

        post("/hi", (req, res) -> {
            return new User("Leonan", "Luppi");
        }, gson::toJson);

        get("/hbs", (req, res) -> {
            Map map = new HashMap();

            map.put("name", "Leonan");
            map.put("lastname", "Luppi");

            return new ModelAndView(map, "hello.hbs");
        }, new HandlebarsTemplateEngine());

    }

    public static String action(spark.Request req, spark.Response res) {
        System.out.print(req.queryMap().get("name").value());
        return "Hello " + req.params("name") + req.body();
    }

}
