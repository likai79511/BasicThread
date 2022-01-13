package com.example.threadstudy.day02

import i
import java.lang.Exception
import java.util.*

/**
 * 在有sleep阻塞的情况下，使用 interrupt 去中断线程
 *
 * sleep中会判断如果收到interrupt通知 会 抛出 interruptException。
 */
class StopThreadWithSleep {

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {

            var runnable = Runnable {
                var i = 0
                while (!Thread.interrupted() && i <= 500) {
                    if (i % 100 == 0){
                        i("$i")
                    }
                    i++
                }
                try{
                    Thread.sleep(2000)
                }catch (e:Exception){
                    i("appear error: "+e.localizedMessage)
                }
            }

            var t1 = Thread(runnable)
            t1.start()
            Thread.sleep(1000)
            t1.interrupt()
        }
    }
}