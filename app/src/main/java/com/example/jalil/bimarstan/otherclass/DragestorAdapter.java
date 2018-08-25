package com.example.jalil.bimarstan.otherclass;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jalil.bimarstan.Detail_darogeramActivity;
import com.example.jalil.bimarstan.G;
import com.example.jalil.bimarstan.R;

import java.util.List;


public class DragestorAdapter extends ArrayAdapter<dragestor> {

    private final Activity context;
    private final List<dragestor>  itemname;

    public DragestorAdapter(Activity context, List<dragestor> itemname) {
        super(context, R.layout.listdragestor, itemname);
        this.context=context;
        this.itemname=itemname;
    }




    public View getView(final int position, View view, ViewGroup parent) {




        View listItem = view;
        MyWrapper wrapper = null;


        try {

            if (listItem == null) {

                listItem = LayoutInflater.from(context).inflate(R.layout.listdragestor, parent, false);
                wrapper = new MyWrapper(listItem);
                listItem.setTag(wrapper);

            } else {
                wrapper = (MyWrapper) listItem.getTag();
            }


            wrapper.getText().setText(itemname.get(position).getText());
            wrapper.getAddress().setText(itemname.get(position).getAddress());
            wrapper.getMobile().setText(itemname.get(position).getMobile());





            wrapper.getMobile().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent jalil=new Intent(G.activity,Detail_darogeramActivity.class);
                    jalil.putExtra("id",itemname.get(position).getId());
                    jalil.putExtra("text",itemname.get(position).getText());
                    jalil.putExtra("address",itemname.get(position).getAddress());
                    jalil.putExtra("mobile",itemname.get(position).getMobile());
                    jalil.putExtra("mobile2",itemname.get(position).getMobile2());
                    jalil.putExtra("lat",itemname.get(position).getLat());
                    jalil.putExtra("lng",itemname.get(position).getLng());


                    G.activity.startActivity(jalil);

                }
            });




        }
        catch (Exception e){
            Log.i("eeeeee", "eeeeeeeeeeeeeeee"+e.toString());
        }



        return listItem;


    };







    class MyWrapper
    {
        private View base;
        private TextView text;
        private TextView mobile;
        private TextView address;

        public MyWrapper(View base)
        {
            this.base = base;
        }



        public TextView getText(){
            if(text == null){
                text = (TextView) base.findViewById(R.id.text);
                Typeface yekan_font = Typeface.createFromAsset(G.activity.getAssets(), "B Nazanin Bold_p30download.com.ttf");
                text .setTypeface(yekan_font);

            }
            return text;
        }






        public TextView getMobile(){
            if(mobile == null){
                mobile = (TextView) base.findViewById(R.id.mobile);
                Typeface yekan_font = Typeface.createFromAsset(G.activity.getAssets(), "B Nazanin Bold_p30download.com.ttf");
                mobile .setTypeface(yekan_font);

            }
            return mobile;
        }





        public TextView getAddress(){
            if(address == null){
                address = (TextView) base.findViewById(R.id.address);
                Typeface yekan_font = Typeface.createFromAsset(G.activity.getAssets(), "B Nazanin Bold_p30download.com.ttf");
                address .setTypeface(yekan_font);

            }
            return address;
        }




    }
}
