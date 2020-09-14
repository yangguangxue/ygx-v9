package com.ygx.timer.jdk;

import java.util.Date;
import java.util.TimerTask;

public class MyTimeTask extends TimerTask {
    @Override
    public void run() {
        System.out.println("now:"+new Date());
    }
}
