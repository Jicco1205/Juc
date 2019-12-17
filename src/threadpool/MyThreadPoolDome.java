package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThreadPoolDome {
    public static void main(String[] args) {
        ExecutorService threadpool= Executors.newFixedThreadPool(3);
        try
        {
           for (int i = 0; i <30 ; i++)
           {
                threadpool.execute(()->{ System.out.println(Thread.currentThread().getName()+"/t卖票"); });
            }
        }finally {

        }
    }
}
