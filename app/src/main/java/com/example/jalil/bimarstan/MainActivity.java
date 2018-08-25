package com.example.jalil.bimarstan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        G.activity=this;


        SharedPreferences sharedpreferences = getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
        String langauge = sharedpreferences.getString(G.lang ,"-1");

        if (langauge.equals("-1")){
            Intent myIntent = new Intent(G.activity,LangaugeActivity.class);
            startActivity(myIntent);
            finish();
        }
        else if (langauge.equals("fa")){
            setContentView(R.layout.activity_main);

        }
        else if (langauge.equals("ps")){
            setContentView(R.layout.activity_mainpasto);
        }


        String checklogin = sharedpreferences.getString(G.id_sick ,"-1");
        if(checklogin.equals("-1") ) {
            Intent myIntent = new Intent(G.activity,LoginActivity.class);
            startActivity(myIntent);
        }





        TextView txtyekan2 = (TextView) findViewById(R.id.textView4);
        TextView txtyekan3 = (TextView) findViewById(R.id.textView111);
        TextView txtyekan4 = (TextView) findViewById(R.id.textView112);
        TextView txtyekan5 = (TextView) findViewById(R.id.textView113);
        TextView txtyekan6 = (TextView) findViewById(R.id.textView117);
        TextView txtyekan7 = (TextView) findViewById(R.id.textView117);
        TextView txtyekan8 = (TextView) findViewById(R.id.textView119);
        TextView txtyekan9 = (TextView) findViewById(R.id.textView123);
        TextView txtyekan10 = (TextView) findViewById(R.id.textView124);
        TextView txtyekan11 = (TextView) findViewById(R.id.textView125);
        TextView txtyekan12 = (TextView) findViewById(R.id.textView455);
        Typeface yekan_font2 = Typeface.createFromAsset(getAssets(), "BTrafcBd_0.ttf");
        txtyekan2.setTypeface(yekan_font2);
        txtyekan3.setTypeface(yekan_font2);
        txtyekan4.setTypeface(yekan_font2);
        txtyekan5.setTypeface(yekan_font2);
        txtyekan6.setTypeface(yekan_font2);
        txtyekan7.setTypeface(yekan_font2);
        txtyekan8.setTypeface(yekan_font2);
        txtyekan9.setTypeface(yekan_font2);
        txtyekan10.setTypeface(yekan_font2);
        txtyekan11.setTypeface(yekan_font2);
        txtyekan12.setTypeface(yekan_font2);

//        Typeface yekan_font4 = Typeface.createFromAsset(getAssets(), "tt0586m_.ttf");
//        Button a=(Button) findViewById(R.id.buttonsin);
//        a.setTypeface(yekan_font4);








        Button backbutton=(Button) findViewById(R.id.button2);
        backbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                G.activity.finish();
            }
        });




        Calendar c = Calendar.getInstance();
        System.out.println("Current time =&gt; "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());
// Now formattedDate have current date/time
//        Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();
        TextView d=( TextView )findViewById(R.id.textView44);
        String d_t[]=formattedDate.split(" ");
        d.setText(d_t[1]);
        TextView d1=( TextView )findViewById(R.id.textView45);

        d1.setText(d_t[0]);
    }




    @Override
    protected void onStop()
    {
        super.onStop();
        Log.i("000000000000000", "onStop: ");
    }


    @Override
    public void onResume(){
        super.onResume();
        Log.i("1111111111111", "onResume: ");
        SharedPreferences sharedpreferences = getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
        String langauge = sharedpreferences.getString(G.lang ,"-1");

        if (langauge.equals("-1")){
            Intent myIntent = new Intent(G.activity,LangaugeActivity.class);
            startActivity(myIntent);
            finish();
        }



        String checklogin = sharedpreferences.getString(G.id_sick ,"-1");
        if(checklogin.equals("-1") ) {
            Intent myIntent = new Intent(G.activity,LoginActivity.class);
            startActivity(myIntent);
            finish();
        }

    }




    public void azmon(View view) {

        YoYo.with(Techniques.Shake)
                .duration(1000)
                .repeat(1)
                .playOn(findViewById(R.id.imagel5));


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent jalil=new Intent(G.activity,AzmoonActivity.class);
                startActivity(jalil);

            }
        },1000);


    }

    public void mostanad(View view) {
        YoYo.with(Techniques.Shake)
                .duration(1000)
                .repeat(1)
                .playOn(findViewById(R.id.imagel4));


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent jalil=new Intent(G.activity,VisitActivity.class);
                startActivity(jalil);

            }
        },1000);
    }

    public void zaman(View view) {
        YoYo.with(Techniques.Shake)
                .duration(1000)
                .repeat(1)
                .playOn(findViewById(R.id.imagel3));


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent jalil=new Intent(G.activity,TurnsearchActivity.class);
                startActivity(jalil);

            }
        },1000);
    }

    public void doktor(View view) {
        YoYo.with(Techniques.Shake)
                .duration(1000)
                .repeat(1)
                .playOn(findViewById(R.id.imagel6));


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent jalil=new Intent(G.activity,ChatallActivity.class);
                startActivity(jalil);

            }
        },1000);
    }

    public void bazar(View view) {
        YoYo.with(Techniques.Shake)
                .duration(1000)
                .repeat(1)
                .playOn(findViewById(R.id.imagel7));


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent jalil=new Intent(G.activity,VisitorActivity.class);
                startActivity(jalil);

            }
        },1000);
    }

    public void darokha(View view) {
        YoYo.with(Techniques.Shake)
                .duration(1000)
                .repeat(1)
                .playOn(findViewById(R.id.imagel8));


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent jalil=new Intent(G.activity,Dragestor_listActivity.class);
                startActivity(jalil);

            }
        },1000);
    }

    public void azmonja(View view) {
        YoYo.with(Techniques.Shake)
                .duration(1000)
                .repeat(1)
                .playOn(findViewById(R.id.imagel10));


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent jalil=new Intent(G.activity,AzmoonActivity.class);
                startActivity(jalil);

            }
        },1000);
    }

    public void listdoktor(View view) {
        YoYo.with(Techniques.Shake)
                .duration(1000)
                .repeat(1)
                .playOn(findViewById(R.id.imagel11));


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent jalil=new Intent(G.activity,AzmoonActivity.class);
                startActivity(jalil);

            }
        },1000);
    }

    public void khadamat(View view) {
        YoYo.with(Techniques.Shake)
                .duration(1000)
                .repeat(1)
                .playOn(findViewById(R.id.imagel12));


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent jalil=new Intent(G.activity,AzmoonActivity.class);
                startActivity(jalil);

            }
        },1000);
    }

    public void motafar(View view) {
        YoYo.with(Techniques.Shake)
                .duration(1000)
                .repeat(1)
                .playOn(findViewById(R.id.imagel20));


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent jalil=new Intent(G.activity,AzmoonActivity.class);
                startActivity(jalil);

            }
        },1000);
    }





    public void exit(View view){
        YoYo.with(Techniques.Shake)
                .duration(1000)
                .repeat(1)
                .playOn(findViewById(R.id.imagel21));


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                SharedPreferences settings = G.activity.getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
                settings.edit().clear().commit();


                Intent jalil=new Intent(G.activity,LangaugeActivity.class);
                startActivity(jalil);
                finish();

            }
        },1000);
    }


}
