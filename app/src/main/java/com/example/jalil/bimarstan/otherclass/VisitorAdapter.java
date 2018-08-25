package com.example.jalil.bimarstan.otherclass;


import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.media.Image;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jalil.bimarstan.G;
import com.example.jalil.bimarstan.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class VisitorAdapter extends ArrayAdapter<visitor> {

    private final Activity context;
    private final List<visitor>  itemname;

    public VisitorAdapter(Activity context, List<visitor> itemname) {
        super(context, R.layout.listvisitor, itemname);
        this.context=context;
        this.itemname=itemname;
    }




    public View getView(int position,View view,ViewGroup parent) {




        View listItem = view;
        MyWrapper wrapper = null;


        try {

            if (listItem == null) {

                listItem = LayoutInflater.from(context).inflate(R.layout.listvisitor, parent, false);
                wrapper = new MyWrapper(listItem);
                listItem.setTag(wrapper);

            } else {
                wrapper = (MyWrapper) listItem.getTag();
            }


            wrapper.getText().setText(itemname.get(position).getText());



            Picasso.with(G.activity)
                    .load(G.ServerImg+itemname.get(position).getImage())
                    .resize(Resources.getSystem().getDisplayMetrics().widthPixels, Resources.getSystem().getDisplayMetrics().heightPixels / 3 )
                    .into(wrapper.getImagevisitor());


            Button b_request=(Button)listItem.findViewById(R.id.request_tamdid);

            b_request.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Toast.makeText(G.activity, "درخواست تمدید زمان برای شما به درستی انجام شد",Toast.LENGTH_LONG).show();

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
        private ImageView imagevisitor;

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



        public ImageView getImagevisitor(){
            if(imagevisitor == null){
                imagevisitor = (ImageView) base.findViewById(R.id.imagevisitor);
//                Typeface yekan_font = Typeface.createFromAsset(G.activity.getAssets(), "B Nazanin Bold_p30download.com.ttf");
//                text .setTypeface(yekan_font);

            }
            return imagevisitor;
        }

    }
}
