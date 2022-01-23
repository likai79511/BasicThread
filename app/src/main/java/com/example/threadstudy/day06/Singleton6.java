package com.example.threadstudy.day06;

/**
 * 单例模式 -   枚举类
 *
 * 优点： 写法简单， 且不会被反序列化破坏单例。（比如反射可以将调用private的构造方法去创建多个实例）
 */
public enum Singleton6 {
    INSTANCE;

    public void test(){}
}
