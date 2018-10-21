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

public class VisitActivity extends AppCompatActivity {


    public String[] TurnList ;
    public int index;
    public StringAndIntegers [] ListStringInteger;

    public CustomAdapter customadapter;

    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit);


        index=0;

        G.activity=this;

         listView = (ListView) findViewById(R.id.list_doctor);

        SharedPreferences sharedpreferences = getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
        String id_sick = sharedpreferences.getString(G.id_sick, "");
        new sendhttp(G.FetchListVisit+"?id_sick="+id_sick,"aaa",id_sick).execute();

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


            Log.i("result:", "resssssssss: "+result);


            if(progressDialog.isShowing())
                progressDialog.dismiss();
            Log.i("",""+this.text);

            String message[]=this.text.split("~");


            Log.i("",""+this.text);
            if(message[0].equals("visit")){

                try {


                    JSONArray contacts;
                    JSONObject jsonObj = new JSONObject(message[1]);
                    contacts = jsonObj.getJSONArray("contacts");

                    ListStringInteger = new StringAndIntegers[contacts.length()+1];
                    TurnList=new String[contacts.length()+1];

                    for (int i = 0; i < contacts.length(); i++) {

                        JSONObject c = contacts.getJSONObject(i);
                        String id = c.getString("id");
                        String date = c.getString("date");
                        String doctor=c.getString("doctore");
                        String description=c.getString("description");
                        String result2=c.getString("result");
                        String commission=c.getString("commission");



                        String name="تاریخ: ";
                        name +=date +"\n";
                        name +="ډاکټر: ";
                        name += doctor +"\n";
                        name +="د کمیسون حالت: ";
                        name +=commission +"\n";
                        name +="د ویزیت پایله";
                        name +=result2 +"\n";
                        name +="شرحه: ";
                        name +=description +"\n";

                        SharedPreferences sharedpreferences = G.activity.getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
                        String lang = sharedpreferences.getString(G.lang ,null);
                        if(lang.equals("fa")){

                            if(i==0){
                                TurnList[index]=name;

                                ListStringInteger[index]=new StringAndIntegers(-1,"","نام پزشک","توضیحات","نتیجه ویزیت");
                                index++;
                            }
                        }
                        else if(lang.equals("ps")){

                            if(i==0){
                                TurnList[index]=name;
                                ListStringInteger[index]=new StringAndIntegers(-1,"","د ډاکټر نوم","تفصیل","د لیدنې پایله");
                                index++;
                            }
                        }




                        TurnList[index]=name;
                        ListStringInteger[index]=new StringAndIntegers(1,date,doctor,description,result2);
                        index++;
                    }

//                    ArrayAdapter adapter = new ArrayAdapter<String>(VisitActivity.this,
//                            R.layout.activity_listview, TurnList);
//                    listView.setAdapter(adapter);

                    customadapter = new CustomAdapter(G.activity,ListStringInteger);
                    listView.setAdapter(customadapter);
                }
                catch (Exception e){

                    Log.i("eeeeeeee", "eeeeeeeeeeeeeeee"+e.toString());
                }

            }





        }

        @Override
        protected void onPreExecute() {

            progressDialog = ProgressDialog.show(VisitActivity.this,
                    "لطفاً منتظر بمانید",
                    "با تشکر");
        }

        @Override
        protected String doInBackground(String... params) {



            try {

                URL url = new URL(this.urlstring);


                Log.i("111111111111", "doInBackground: "+url.toString());


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