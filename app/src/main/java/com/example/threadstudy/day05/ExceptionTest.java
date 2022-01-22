package com.example.threadstudy.day05;

import androidx.annotation.NonNull;

import java.nio.channels.IllegalChannelGroupException;

public class ExceptionTest implements Runnable{
    @Override
    public void run() {
        throw new RuntimeException("inner exception");
    }


    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((t, e) ->
                System.out.println("--catch exception: "+e.getLocalizedMessage()));

        Thread t1 = new Thread(new ExceptionTest());
        t1.start();
    }
}
