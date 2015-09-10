package asw.xadrez.versao4;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestHelper {
	private static final int OK_STATUS = 200;

	public static boolean makeRequest(final String method, final String url, final String json) throws IOException {
		final URL obj = new URL(url);

		final HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

		// Sets the method of the request
		conn.setRequestMethod(method);

		if (json != null) {
			conn.setDoOutput(true);

			// Warns the server we're sending a json
			conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");

			// Sets the body of the request to the json
			final OutputStream os = new BufferedOutputStream(conn.getOutputStream());
			os.write(json.getBytes());
			os.flush();
		}

		return conn.getResponseCode() == OK_STATUS;
	}
}
