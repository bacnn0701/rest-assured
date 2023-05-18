package builder;

import com.google.gson.Gson;

public class BuildJSONContent {

    public static <T> String getBodyJSONContent(T dataObject) {
        return new Gson().toJson(dataObject);
    }
}
