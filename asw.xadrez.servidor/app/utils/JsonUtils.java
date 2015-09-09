package utils;

import play.Logger;
import play.libs.Json;
import play.mvc.Controller;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonUtils {

	public static <T> T parse(final JsonNode node, final Class<T> clazz) {
		if (node == null || clazz == null) {
			return null;
		}
		try {
			return Json.fromJson(node, clazz);
		} catch (final Exception e) {
			Logger.error("There was an issue parsing the json " + node + " to " + clazz, e);
			return null;
		}
	}

	public static <T> T parse(final Class<T> clazz) {
		return parse(Controller.request().body().asJson(), clazz);
	}

	public static String toJson(final Object object) {
		return Json.toJson(object).toString();
	}
}
