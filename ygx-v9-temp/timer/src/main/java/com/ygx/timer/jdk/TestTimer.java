package com.ygx.timer.jdk;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Timer;

public class TestTimer {
    public static void main(String[] args) {
        Timer timer = new Timer();
        MyTimeTask myTimeTask = new MyTimeTask();
        System.out.println(new Date());
//        timer.schedule(myTimeTask,3000);
        timer.schedule(myTimeTask,3000,1000);
    }
}
