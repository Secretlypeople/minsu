package com.jk.test;

public class TestThread {

    public static void main(String[] args) {


        /*//通过new关键字创建新的线程 1.新建状态
        ThreadDemo a = new ThreadDemo("A");
        ThreadDemo b = new ThreadDemo("B");
        ThreadDemo c = new ThreadDemo("C");*/
        //start启动线程的方法 2.就绪状态

        Thread a = new Thread(new RunnableTest("A"));
        Thread b= new Thread(new RunnableTest("B"));
        Thread c = new Thread(new RunnableTest("C"));

        //调用完run方法后 线程对象进入垃圾回收机制 线程被回收 4.线程进入死亡状态
        a.start();
        b.start();
        c.start();
    }
}
