package com.wang;

import java.util.concurrent.TimeUnit;

/**
 * @author feige
 * @version v1.0
 * @date 2023-03-10-22:10
 * @description
 */

public class CallBackDemo {

    public static void main(String[] args) {
        CallBackDemo demo = new CallBackDemo();
        demo.print(new CallBack() {
            @Override
            public void context() {
                // 回调通知
                System.out.println("你好啊");
            }
        });
        System.out.println("接下来");
    }


    public void print(CallBack callBack) {
        System.out.println("操作完成，回调");
        new Thread(() -> {
            // 耗时操作
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            callBack.context();
        }).start();
    }


}

interface CallBack {
    void context();
}


