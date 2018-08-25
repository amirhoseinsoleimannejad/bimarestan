package com.example.jalil.bimarstan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LangaugeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_langauge);



        G.activity=this;



        SharedPreferences sharedpreferences = LangaugeActivity.this.getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
        String lang = sharedpreferences.getString(G.lang ,null);


        if(lang!=null){
            Intent myIntent = new Intent(G.activity,LoginActivity.class);
            LangaugeActivity.this.startActivity(myIntent);
            G.activity.finish();
        }


        TextView txtyekan = (TextView) findViewById(R.id.textView);
        Typeface yekan_font = Typeface.createFromAsset(getAssets(), "BTrafcBd_0.ttf");
        txtyekan.setTypeface(yekan_font);

//
//        TextView txtyekan1 = (TextView) findViewById(R.id.textView1);
//        Typeface yekan_font1 = Typeface.createFromAsset(getAssets(), "tt0586m_.ttf");
//        txtyekan1.setTypeface(yekan_font1);

        Button b1=(Button)findViewById(R.id.button);
        Typeface yekan_font2 = Typeface.createFromAsset(getAssets(), "BTrafcBd_0.ttf");
        b1.setTypeface(yekan_font2);
        Button b2=(Button)findViewById(R.id.button1);
        Typeface yekan_font3 = Typeface.createFromAsset(getAssets(), "BTrafcBd_0.ttf");
        b2.setTypeface(yekan_font3);







    }











    public void onclik(View view) {


        SharedPreferences sharedpreferences = LangaugeActivity.this.getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(G.lang,"fa");
        editor.apply();

        Intent jalil=new Intent(this,LoginActivity.class);
        startActivity(jalil);
        G.activity.finish();

    }







    public void pashti(View view) {

        SharedPreferences sharedpreferences = LangaugeActivity.this.getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(G.lang,"ps");
        editor.apply();

        Intent jalil1=new Intent(this,LoginActivity.class);
        startActivity(jalil1);
        G.activity.finish();

    }
}
