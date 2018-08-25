package com.example.jalil.bimarstan;

import java.util.ArrayList;
import java.util.List;
public class DataProvider {
    private static List<Course> data=new ArrayList<>();
    public  static List<Course> getData(){return  data;}
    static {
        data.add(new Course(10101, "php اموزش","bazi",12 ));
        data.add(new Course(10102, "java اموزش","bazi",12));
        data.add(new Course(10103, "javascript اموزش","bazi",12));
        data.add(new Course(10104, "html اموزش","bazi",12));
        data.add(new Course(10105, "css اموزش","bazi",12));
    }
}
