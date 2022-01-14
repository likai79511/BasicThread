package com.example.threadstudy.day03

import i

/**
 * 打印出线程的三种基本状态： New、Runnable、Terminated
 *
 * 我们可以看出：
 *          在线程创建后，没有调用start之前： New
 *          调用start后：Runnable
 *          线程运行过程中：Runnable
 *          线程运行完毕后：Terminated
 */
class SimpleThreadStatus {

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {

            var t1 = Thread(Rb())
            i("status-0: ${t1.state}")
            t1.start()
            i("status-1: ${t1.state}")
            Thread.sleep(30)
            i("status-2: ${t1.state}")
            Thread.sleep(1000)
            i("status-3: ${t1.state}")
        }
    }



    class Rb:Runnable{
        override fun run() {
            for (i in 0..10){
                i("$i")
                Thread.sleep(10)
            }
        }
    }
}