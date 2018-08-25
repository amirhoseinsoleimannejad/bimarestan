package com.example.jalil.bimarstan;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jalil.bimarstan.otherclass.CustomAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class VisitAnswerAzmoonActivity extends AppCompatActivity {


    public String[] TurnList ;
    public int index;
    public StringAndIntegers [] ListStringInteger;
    private CustomAdapter customadapter;
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitanswer);


        index=0;



        lv = (ListView)findViewById(R.id.listanswer);


        SharedPreferences sharedpreferences = getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);

        String id_sick = sharedpreferences.getString(G.id_sick, "-1");
        new sendhttp(G.FetchListVisitAnswer+"?id_sick="+id_sick,"aaa",id_sick).execute();



    }


















    public class sendhttp extends AsyncTask<String, String, String> {
        public String text;
        public String  data;
        public String urlstring;

        public String pass;



        public ProgressDialog progressDialog;






        public sendhttp(String urlstring,String data,String pass){


            this.data=data;
            this.urlstring=urlstring;

            this.pass=pass;

        }




        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);
            this.text=result;





            if(progressDialog.isShowing())
                progressDialog.dismiss();



            Log.i("rrrrrrrrrrr","rrrrrrrrrrrrr"+this.text);







                try {


                    JSONArray mmpi;
                    JSONObject jsonObj = new JSONObject(this.text);
                    mmpi = jsonObj.getJSONArray("mmpi");


                    JSONArray anogram;
                    anogram = jsonObj.getJSONArray("anogram");


                    JSONArray depress;
                    depress = jsonObj.getJSONArray("depress");


                    TurnList=new String[mmpi.length()+depress.length()+anogram.length()];

                    ListStringInteger = new StringAndIntegers[mmpi.length()+depress.length()+anogram.length()];


                    for (int i = 0; i < mmpi.length(); i++) {

                        JSONObject c = mmpi.getJSONObject(i);




                        SharedPreferences sharedpreferences = G.activity.getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
                        String lang = sharedpreferences.getString(G.lang ,null);
                        if(lang.equals("fa")){

                            String id = c.getString("id");
                            String date = c.getString("date");
                            String score=c.getString("score");
                            String result2=c.getString("result").replace("<br>","-");


                            ListStringInteger[index] = new StringAndIntegers(Integer.parseInt(id),date,result2,score,"آزمون mmpi");

                        }
                        else if(lang.equals("ps")){

                            String id = c.getString("id");
                            String date = c.getString("date");
                            String score=c.getString("score");
                            String result2=c.getString("result_p").replace("<br>","-");

                            ListStringInteger[index] = new StringAndIntegers(Integer.parseInt(id),date,result2,score,"د Mmpi ازموینه");

                        }



                        index++;
                    }






                    for (int i = 0; i < anogram.length(); i++) {

                        JSONObject c = anogram.getJSONObject(i);




                        SharedPreferences sharedpreferences = G.activity.getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
                        String lang = sharedpreferences.getString(G.lang ,null);
                        if(lang.equals("fa")){

                            String id = c.getString("id");
                            String date = c.getString("date");
                            String score=c.getString("score");
                            String result2=c.getString("result").replace("<br>","-");


                            ListStringInteger[index] = new StringAndIntegers(Integer.parseInt(id),date,result2,score,"آزمون آنوگرام");

                        }
                        else if(lang.equals("ps")){

                            String id = c.getString("id");
                            String date = c.getString("date");
                            String score=c.getString("score");
                            String result2=c.getString("result_p").replace("<br>","-");

                            ListStringInteger[index] = new StringAndIntegers(Integer.parseInt(id),date,result2,score,"انوگرام آزموینه");

                        }



                        index++;
                    }









                    for (int i = 0; i < depress.length(); i++) {

                        JSONObject c = depress.getJSONObject(i);




                        SharedPreferences sharedpreferences = G.activity.getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
                        String lang = sharedpreferences.getString(G.lang ,null);
                        if(lang.equals("fa")){

                            String id = c.getString("id");
                            String date = c.getString("date");
                            String score=c.getString("score");
                            String result2=c.getString("result").replace("<br>","-");


                            ListStringInteger[index] = new StringAndIntegers(Integer.parseInt(id),date,result2,score,"آزمون افسردگی");

                        }
                        else if(lang.equals("ps")){

                            String id = c.getString("id");
                            String date = c.getString("date");
                            String score=c.getString("score");
                            String result2=c.getString("result_p").replace("<br>","-");

                            ListStringInteger[index] = new StringAndIntegers(Integer.parseInt(id),date,result2,score,"د ډیپلومات ازموینه");

                        }



                        index++;
                    }



//                    ArrayAdapter adapter = new ArrayAdapter<String>(VisitAnswerAzmoonActivity.this,
//                            R.layout.activity_listview, TurnList);
//                    ListView listView = (ListView) findViewById(R.id.mobile_list);
//                    listView.setAdapter(adapter);

                    customadapter = new CustomAdapter(G.activity,ListStringInteger);
                    lv.setAdapter(customadapter);


                }
                catch (Exception e){

                }





        }

        @Override
        protected void onPreExecute() {

            progressDialog = ProgressDialog.show(VisitAnswerAzmoonActivity.this,
                    "لطفاً منتظر بمانید",
                    "با تشکر");
        }

        @Override
        protected String doInBackground(String... params) {



            try {

                URL url = new URL(this.urlstring);





                // Send POST data request

                HttpURLConnection conn = null;

                conn = (HttpURLConnection) url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = null;

                conn.setRequestProperty(
                        "Authorization",
                        "Basic " + Base64.encodeToString((""+":"+this.pass).getBytes(), Base64.NO_WRAP));
                wr = new OutputStreamWriter(conn.getOutputStream());


                wr.write(this.data);


                wr.flush();


                // Get the server response

                BufferedReader reader = null;

                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response

                while ((line = reader.readLine()) != null) {
                    // Append server response in string
                    sb.append(line);

                }


                this.text = sb.toString();

                conn.disconnect();





            }
            catch (Exception e){

                this.text=e.toString();

            }



            return this.text;
        }





    }
}