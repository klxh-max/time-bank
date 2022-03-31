package com.tb.util;

import org.omg.PortableServer.ServantActivator;

import javax.servlet.ServletContextListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

//监听器
public class DayInterval implements ServletContextListener {

    public static void showDayTime() {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.set(year, month, day, 23, 59, 59);//设置要执行的日期时间

        Date defaultdate = calendar.getTime();

        Timer dTimer = new Timer();
        dTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                System.out.println("每日任务已经执行");

            }

        }, defaultdate , 24* 60* 60 * 1000);//24* 60* 60 * 1000
    }

    public static void main(String[] args) {
        showDayTime();
    }
}
