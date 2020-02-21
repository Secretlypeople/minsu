package com.jk.test;

public class ThreadSaveTest {

    private static int i = 0;
    public static void main(String[] args) {

        for (int index = 0; index<50000 ; index++) {
            //匿名内部类
            new Thread(new Runnable() {
                @Override
                public void run() {
                    i++;
                }
            }).start();

            //匿名内部类
            new Thread(new Runnable() {
                @Override
                public void run() {
                    i--;
                }
            }).start();
        }

        try {
            //等待线程执行完毕 最终输出结果
            Thread.sleep(10000);
            System.out.println("args=["+i+"]");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
