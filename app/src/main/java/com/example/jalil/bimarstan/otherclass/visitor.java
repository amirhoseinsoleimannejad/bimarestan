package com.example.jalil.bimarstan.otherclass;

/**
 * Created by amhso on 21/07/2018.
 */

public class visitor {

    public String id;
    public String text;
    public String image;

    public visitor(String id,String text,String image){
        this.id=id;
        this.text=text;
        this.image=image;
    }

    public String getId(){
        return this.id;
    }

    public String getText(){
        return this.text;
    }


    public String getImage(){
        return this.image;
    }
}
