import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadWaitNotifyDemo {
    public static void main(String[] args) {
        AirConditioner airConditioner=new AirConditioner();
        new Thread(()->{
          for (int i = 1; i <10 ; i++) {
              try {
                  Thread.sleep(200);
                    airConditioner.increment();
             }catch (InterruptedException e){
                 e.printStackTrace();
               }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 1; i <10 ; i++) {
                try {
                    Thread.sleep(300);
                    airConditioner.decrement();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 1; i <10 ; i++) {
                try {
                    Thread.sleep(400);
                    airConditioner.increment();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(()->{
            for (int i = 1; i <10 ; i++) {
                try {
                    Thread.sleep(500);
                    airConditioner.decrement();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}

class AirConditioner{
    private int number =0;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();

    public synchronized void increment()throws InterruptedException{
        lock.lock();
                try
                {
                    //1 判断
                    while(number != 0)
                    {
                        condition.await();//A系统停止
                    }
                    //2 干活
                    ++number;
                    System.out.println(
                            Thread.currentThread()
                    );
                    //3 通知
                    condition.signal();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
    }

    public void decrement() throws InterruptedException
    {
        lock.lock();
        try {
             //1 判断
             while (number == 0) {
                condition.await();//this.wait();
            }
            //2 干活
            --number;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //3 通知
            condition.signalAll();//this.notifyAll();
         } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
