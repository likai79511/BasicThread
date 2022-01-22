package com.example.threadstudy.day05;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

/**
 * 重排序的演示
 * <p>
 * 初始化a,b,x,y 均为0，两个线程t1,t2。
 * t1:  a = 1; x = b;
 * t2:  b = 1; y = a;
 * <p>
 * 可能出现的结果如下：
 * --t1 先执行，t2后执行。  结果为： x = 0, y = 1;
 * --t2 先执行，t1后执行。  结果为： x = 1, y =0;
 * --交错执行：
 * --t1先执行一行，t2执行一行。 结果为：x = 1, y = 1
 * --t1先执行一行，t2执行两行。 结果为：x = 1, y = 1
 * --t2先执行一行，t1执行一行。 结果为：x = 1, y = 1
 * --t2先执行一行，t1执行两行。 结果为：x = 1, y = 1
 * <p>
 * 所以结果是[x,y]： [0,1], [1,0], [1,1]
 *
 * 大量运行，会有小概率出现： [0,0]结果， 说明代码的执行顺序发生的调整。
 *
 */
public class OutOfOrderExecution {

    static int a = 0, b = 0;
    static int x = 0, y = 0;
    static int count = 0;
    static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        for (; ; ) {
            x=0;
            y=0;
            a=0;
            b=0;

            count++;
            latch = new CountDownLatch(1);
            T1 t1 = new T1();
            T2 t2 = new T2();
            t1.start();
            t2.start();
            latch.countDown();
            t1.join();
            t2.join();

            System.out.println("第" + count + "次结果：(" + x + "," + y + ")");
            if (x == 0 && y ==0){
                break;
            }
        }



    }


    static class T1 extends Thread {
        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a = 1;
            x = b;
        }
    }

    static class T2 extends Thread {
        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            b = 1;
            y = a;
        }
    }

}
