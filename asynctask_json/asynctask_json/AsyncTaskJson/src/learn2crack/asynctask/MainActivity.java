package learn2crack.asynctask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import learn2crack.asynctask.library.JSONParser;
//import learn2crack.jsonparsing.R;



public class MainActivity extends Activity {
	TextView uid;
	TextView name1;
	TextView email1;
	Button Btngetdata;
	
	//URL to get JSON Array
	private static String url = "http://www.thephpcode.com/demo/test.php";
	
	private static String url1="http://namara.io/api/v0/resources/7cb31a66-2f19-4c2a-9bdc-7176a342981c/data?api_key=097496a85611786bd3f1b6670ede38f814bdc56eaf569bda316f101aa5f172dc&order=%7B%22column%22:5,%22direction%22:0%7D&where=[%7B%22column%22:0,%22selector%22:%22eq%22,%22value%22:%222012%22%7D]&limit=10";
	
	//JSON Node Names 
	private static final String TAG_USER = "user";
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";
	private static final String TAG_EMAIL = "email";
	
	JSONArray user = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
        setContentView(R.layout.activity_main);
        Btngetdata = (Button)findViewById(R.id.getdata);
        Btngetdata.setOnClickListener(new View.OnClickListener() {
		
        	
			@Override
			public void onClick(View view) {
				//Log.i("Inside the onCreate  ", null );
				Log.d("Siva", "onCreated called");
			
		         new JSONParse().execute();

				
			}
		});
        
        
    }


    
    private class JSONParse extends AsyncTask<String, String, JSONObject> {
    	 private ProgressDialog pDialog;
    	@Override
        protected void onPreExecute() {
            super.onPreExecute();
             uid = (TextView)findViewById(R.id.uid);
			 name1 = (TextView)findViewById(R.id.name);
			 email1 = (TextView)findViewById(R.id.email);
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
            
    	}
    	
    	@Override
        protected JSONObject doInBackground(String... args) {
    		JSONParser jParser = new JSONParser();

    		// Getting JSON from URL
    		JSONObject json = jParser.getJSONFromUrl(url1);
    		return json;
    	}
    	 @Override
         protected void onPostExecute(JSONObject json) {
    		 pDialog.dismiss();
    		int len=0;
    		 try {
    			 Log.w("getJSONArray","Inside the loop");
    			// Getting JSON Array
    				//user = json.getJSONArray();
    			 user=json.names();
    				len=user.length();
    				JSONObject c =null;
    						//user.getJSONObject(0);
    				
    				// Storing  JSON item in a Variable
    				/*String id = c.getString(TAG_ID);
    				String name = c.getString(TAG_NAME);
    				String email = c.getString(TAG_EMAIL); */
    				
    				//Importing TextView
    				/*
    				final TextView uid = (TextView)findViewById(R.id.uid);
    				final TextView name1 = (TextView)findViewById(R.id.name);
    				final TextView email1 = (TextView)findViewById(R.id.email);
    				
    				//Set JSON Data in TextView
    				uid.setText(id);
    				name1.setText(name);
    				email1.setText(email);   

    			for(int i=0;i<len;i++)
    			{
    				Log.w("getJSONArray","Inside the loop");
    				 c = user.getJSONObject(i);
     				Log.w("getJSONArray",c.getString( "Ref_Date"));
     				Log.w("getJSONArray",c.getString("GEO"));
     				Log.w("getJSONArray",c.getString(" Geographical classification"));
    				
    			}
    			*/
    		} catch (Exception e) {
    			e.printStackTrace();
    		}

    		 
    	 }
    }
    
}
