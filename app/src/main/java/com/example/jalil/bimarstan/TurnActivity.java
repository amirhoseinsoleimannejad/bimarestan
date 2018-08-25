package com.example.jalil.bimarstan;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jalil.bimarstan.otherclass.CustomAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class TurnActivity extends AppCompatActivity {
    public String[] DoctorsList ;
    public int index;
    public int expertise;
    public int part;

    public ListView lv;
    public StringAndIntegers [] ListStringInteger;
    private CustomAdapter customadapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn);



        G.activity=this;


        Bundle bundle = getIntent().getExtras();
        String s=bundle.getString("expertise");


        expertise=Integer.parseInt(s);
        s=bundle.getString("part");
        part=Integer.parseInt(s);

        index=0;



        lv = (ListView)findViewById(R.id.list_doctor);




        new sendhttp(G.FetchlistDoctor+"?expertise="+expertise+"&part="+part+"&code="+"00000","aaa","000").execute();







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


            Log.i("result", "result: "+result);


            if(progressDialog.isShowing())
                progressDialog.dismiss();







                try {


                    JSONArray contacts;
                    JSONObject jsonObj = new JSONObject(result);
                    contacts = jsonObj.getJSONArray("contacts");


                    DoctorsList = new String[contacts.length()];
                    ListStringInteger = new StringAndIntegers[contacts.length()];

                    for (int i = 0; i < contacts.length(); i++) {

                        JSONObject c = contacts.getJSONObject(i);


                        SharedPreferences sharedpreferences = G.activity.getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
                        String lang = sharedpreferences.getString(G.lang ,null);
                        if(lang.equals("fa")){

                            String id = c.getString("id");
                            String name = c.getString("qty");
                            String mobile = c.getString("text");
                            String address = c.getString("date");



                            ListStringInteger[index] = new StringAndIntegers(Integer.parseInt(id),name,"500000",address,mobile);

                        }
                        else if(lang.equals("ps")){

                            String id = c.getString("id");
                            String name = c.getString("qty");
                            String mobile = c.getString("text_p");
                            String address = c.getString("date");




                            ListStringInteger[index] = new StringAndIntegers(Integer.parseInt(id),name,"50000",address,mobile);

                        }
                        index++;
                    }




                    customadapter = new CustomAdapter(G.activity,ListStringInteger);
                    lv.setAdapter(customadapter);




                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                            int id_turn = ListStringInteger[(int) id].getInteger();
                            SharedPreferences sharedpreferences = getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);

                            String id_sick = sharedpreferences.getString(G.id_sick, "");
                            new sendhttp(G.Turn + "?id_turn=" + id_turn+"&&id_sick="+id_sick, "aaa", id_sick).execute();



                            Intent myIntent = new Intent(G.activity,MainActivity.class);
                            myIntent.putExtra("id_doctor",ListStringInteger[(int) id].getInteger());
                            G.activity.startActivity(myIntent);


                        }
                    });


                } catch (Exception e) {


                    Log.i("eeeeeee", "eeeeeee: " + e.toString());
//
                    String sr[]=result.split("~");

                    try {
                        if (sr[0].equals("turnclick") && sr[1].equals("1")) {
                            SharedPreferences sharedpreferences = G.activity.getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
                            String lang = sharedpreferences.getString(G.lang, null);
                            if (lang.equals("fa")) {

                                Toast.makeText(G.activity, "نوبت به درستی گرفته شد با تشکر", Toast.LENGTH_LONG).show();

                            } else if (lang.equals("ps")) {

                                Toast.makeText(G.activity, "بدله په سمه توګه اخیستل شوی مننه", Toast.LENGTH_LONG).show();

                            }
                        } else if (sr[0].equals("turnclick") && sr[1].equals("0")) {
                            SharedPreferences sharedpreferences = G.activity.getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
                            String lang = sharedpreferences.getString(G.lang, null);
                            if (lang.equals("fa")) {

                                Toast.makeText(G.activity, "نوبت به درستی ثبت نشد بار دیگر تکرار کنید", Toast.LENGTH_LONG).show();

                            } else if (lang.equals("ps")) {

                                Toast.makeText(G.activity, "په سمه توګه راجستر مه کوئ. بیا تکرار کړئ", Toast.LENGTH_LONG).show();

                            }
                        } else {
                            SharedPreferences sharedpreferences = G.activity.getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
                            String lang = sharedpreferences.getString(G.lang, null);
                            if (lang.equals("fa")) {

                                Toast.makeText(G.activity, "برای این منطقه و تخصص هیچ نوبتی تعریف نشده است.", Toast.LENGTH_LONG).show();

                            } else if (lang.equals("ps")) {

                                Toast.makeText(G.activity, "د دې سیمې او تخصص لپاره هیڅ بدلون نه شته.", Toast.LENGTH_LONG).show();

                            }
                        }

                    }
                    catch (Exception e2){
                        SharedPreferences sharedpreferences = G.activity.getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
                        String lang = sharedpreferences.getString(G.lang, null);
                        if (lang.equals("fa")) {

                            Toast.makeText(G.activity, "برای این منطقه و تخصص هیچ نوبتی تعریف نشده است.", Toast.LENGTH_LONG).show();

                        } else if (lang.equals("ps")) {

                            Toast.makeText(G.activity, "د دې سیمې او تخصص لپاره هیڅ بدلون نه شته.", Toast.LENGTH_LONG).show();

                        }
                    }
                    Intent myIntent = new Intent(G.activity, MainActivity.class);
                    G.activity.startActivity(myIntent);


                }





        }

        @Override
        protected void onPreExecute() {

            SharedPreferences sharedpreferences = G.activity.getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
            String lang = sharedpreferences.getString(G.lang, null);
            if (lang.equals("fa")) {

                progressDialog = ProgressDialog.show(TurnActivity.this,
                        "لطفاً منتظر بمانید",
                        "با تشکر");
            } else if (lang.equals("ps")) {

                progressDialog = ProgressDialog.show(TurnActivity.this,
                        "مهرباني وکړئ انتظار وکړئ",
                        "مننه");
            }

        }

        @Override
        protected String doInBackground(String... params) {



            try {

                URL url = new URL(this.urlstring);


                Log.i("ffffffff", "uuuuuuuuuuuuuuuuuu"+url.toString());




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
