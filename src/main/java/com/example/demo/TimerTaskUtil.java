package com.example.demo;

import java.time.LocalDateTime;
import  java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import com.example.demo.Services.MasterService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

public class TimerTaskUtil {
    public void callTimerTask(){
        ScheduledTask task = new ScheduledTask();


        Timer timer = new Timer(true);


        timer.schedule(task,90000,90000);




    }

}
