package com.example.threadstudy.day02

import i
import java.lang.Exception
import kotlin.jvm.Throws

/**
 * Interrupt 发生在 Thread.sleep 阻塞时：
 * 会有 interruptException 被抛出，但是程序没有停止，原因：下次 Thread.interrupted()判断时,发现中断状态被清除。
 */
class StopThreadClearInterrupt {
    companion object {
        @JvmStatic
        fun main(args: Array<String>){

            var runnable = Runnable {

                var i = 0

                while (!Thread.interrupted() && i <= 500) {
                    if (i % 100 == 0)
                        i("$i")
                    i+=100
                    try {
                        Thread.sleep(1000)
                    } catch (e: Exception) {
                        i("appear error: " + e.localizedMessage)
                    }
                }
            }

            var t1 = Thread(runnable)
            t1.start()
            Thread.sleep(500)
            t1.interrupt()

        }
    }
}