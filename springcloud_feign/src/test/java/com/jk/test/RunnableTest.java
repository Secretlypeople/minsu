package com.jk.test;

public class RunnableTest implements  Runnable {

    private String threadName;

    public RunnableTest(String threadName) {

        this.threadName = threadName;
    }

    //run方法规定当前线程需要执行的任务内容
    //run方法没有参数 需要通过构造函数传递参数
    //3.运行状态
    @Override
    public void run() {

        for (int i = 0; i < 50; i++) {
            System.out.println("线程"+threadName+":"+i);
            try {
                //线程休眠方法 线程进入阻塞状态
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
