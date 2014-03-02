package learn2crack.asynctask.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {

	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";

	// constructor
	public JSONParser() {

	}

	public JSONObject getJSONFromUrl(String url) {

		// Making HTTP request
		try {
			 Log.w("JSONParser",url);
			 Log.w("JSONParser","getJSONFromUrl");
			// defaultHttpClient
			DefaultHttpClient httpClient = new DefaultHttpClient();
			Log.w("JSONParser","defaultHttpClient");
			HttpPost httpPost = new HttpPost(url);
			Log.w("JSONParser","After create HttpPost");
			HttpResponse httpResponse = httpClient.execute(httpPost);
			Log.w("JSONParser","After ecute httpClient");
			HttpEntity httpEntity = httpResponse.getEntity();
			Log.w("JSONParser","After get getEntity");
			is = httpEntity.getContent();			

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				Log.i("DATA", line);
				sb.append(line + "\n");
			}
			is.close();
			Log.w("JSONParser","Before  sb");
			json = sb.toString();
			Log.w("JSONParser","After  sb");
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}

		// try parse the string to a JSON object
		try {
			jObj = new JSONObject(json);
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}

		// return JSON String
		return jObj;

	}
}
