package com.example.threadstudy.day02

import i
import java.util.*

/**
 * 使用 interrupt 去中断线程的 简单方式（没有阻塞的情况）
 */
class StopThreadWithoutBlocking {

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {

            var runnable = Runnable {
                var i = 0
                while (!Thread.interrupted() && i <= Integer.MAX_VALUE) {
                    if (i % 10000 == 0){
                        i("$i")
                    }
                    i++
                }
            }

            var t1 = Thread(runnable)
            t1.start()
            Thread.sleep(500)
            t1.interrupt()
        }
    }
}