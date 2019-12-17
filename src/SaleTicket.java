import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicket {
    public static void main(String[] args) {
         Ticket ticket = new Ticket();
        new Thread(()->{ for (int i = 0; i <35; i++) ticket.sale();},"A").start();
    }
}
class Ticket{
    private int number=30;
    private Lock lock=new ReentrantLock();
    public void sale() {
        lock.lock();
        try {
            //1 判断
            while (number>0) {
                System.out.println(Thread.currentThread().getName()+"\t卖出第"+number--+"\t剩余第"+number);
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    /*public synchronized void sale(){
        if(number>0){
            System.out.println(Thread.currentThread().getName()+"\t卖出第"+number--+"\t剩余第"+number);
        }
    }*/

}


