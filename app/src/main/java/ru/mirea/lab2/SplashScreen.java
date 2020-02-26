package ru.mirea.lab2;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class SplashScreen extends AppCompatActivity {

        private ArrayList<Item> list;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.splashscreen);
            list = new ArrayList<>();
            if(savedInstanceState == null)
                new PrefetchData().execute();

        }

        private class PrefetchData extends AsyncTask<Void, Void, Void> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... arg0) {
                HttpHandler sh = new HttpHandler();
                String url = "https://raw.githubusercontent.com/wesleywerner/ancient-tech" +
                        "/02decf875616dd9692b31658d92e64a20d99f816/src/data/techs.ruleset.json";
                String json = sh.makeServiceCall(url);

                if (json != null) {
                    try {
                        JSONArray jArr = new JSONArray(json);
                        for (int i = 1; i < jArr.length(); i++) {
                            JSONObject o = jArr.getJSONObject(i);
                            if (o.has("helptext")) {
                            list.add(new Item(o.getString("graphic"),
                                    o.getString("name"),
                                    o.getString("helptext")
                                    )
                            ); } else {
                                list.add(new Item(o.getString("graphic"), o.getString("name")));
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                i.putParcelableArrayListExtra("listOfItems", list);
                startActivity(i);
                finish();
            }
        }


}
