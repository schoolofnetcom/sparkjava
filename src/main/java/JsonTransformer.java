import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by Leonan-Mac on 8/18/16.
 */
public class JsonTransformer implements ResponseTransformer {

    private Gson gson = new Gson();

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }
}
