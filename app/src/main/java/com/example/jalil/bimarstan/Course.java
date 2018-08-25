package com.example.jalil.bimarstan;

public class Course {
    private  int courseNumber;
    private  String title;
    private  String descripttion;
    private  double credits;
    public  Course(int courseNumber,String title,String descripttion,double credits){
        this.courseNumber = courseNumber;
        this.title = title;
        this.descripttion =descripttion;
        this.credits =credits;
    }
    public  int getcourseNumber(){return  courseNumber;}
    public String getTitle(){ return  title;}
    public String getDescripttion(){return descripttion;}
    public  double getCredits(){return  credits;}}
