fun main(){
    var t1 = T1()
    t1.start()

    var t2 = T2()
    Thread(t2).start()
}

class T1: Thread() {
    override fun run() {
        i("--使用 extent Thread 创建线程--")
    }
}

class T2:Runnable{
    override fun run() {
        i("--使用 implement Runnable 创建线程--")
    }
}