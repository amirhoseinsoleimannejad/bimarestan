package com.example.jalil.bimarstan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AzmoonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safeazmon);


        SharedPreferences sharedpreferences = getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
        String langauge = sharedpreferences.getString(G.lang ,"-1");

        if (langauge.equals("-1")){
            Intent myIntent = new Intent(G.activity,LangaugeActivity.class);
            startActivity(myIntent);
            finish();
        }

        else if (langauge.equals("ps")){
            Button mmpi_button=(Button) findViewById(R.id.mmpi);
            mmpi_button.setText("د Mmpi ازموینه");

            mmpi_button=(Button) findViewById(R.id.anogram);
            mmpi_button.setText("د enogram ازموینه");

            mmpi_button=(Button) findViewById(R.id.dpress);
            mmpi_button.setText("د dpress ازموینه");


        }


        String checklogin = sharedpreferences.getString(G.id_sick ,"-1");
        if(checklogin.equals("-1") ) {
            Intent myIntent = new Intent(G.activity,LoginActivity.class);
            startActivity(myIntent);
        }



    }

    public void jalil(View view) {
        Intent jalil=new Intent(this,MmpiActivity.class);
        startActivity(jalil);
    }

    public void jalil1(View view) {
        Intent jalil1=new Intent(this,VisitAnswerAzmoonActivity.class);
        startActivity(jalil1);
    }




    public void anogram(View view){
        Intent jalil1=new Intent(this,AnogramActivity.class);
        startActivity(jalil1);
    }

    public void dpress(View view){
        Intent jalil1=new Intent(this,DepressActivity.class);
        startActivity(jalil1);
    }
}
