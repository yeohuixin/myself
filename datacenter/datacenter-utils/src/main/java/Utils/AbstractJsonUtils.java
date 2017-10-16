package Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public abstract class AbstractJsonUtils {

	public final static Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().registerTypeAdapter(String.class, new StringConverter()).create();

	public static String toJson(Object src) {
		return GSON.toJson(src);
	}

	public static String toJson(Object src, Type objType) {
		return GSON.toJson(src, objType);
	}

    public static <T> String toJson1(T obj) {
        Type type = new TypeToken<T>() {}.getType();
        return toJson(obj, type);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
		return GSON.fromJson(json, typeOfT);
	}
}
