package Thread._0509;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 05 09
 * Time:10:00
 */
public class Demo1 {
    public static void main(String[] args) {
        Object lockA = new Object();
        Object lockB = new Object();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lockA){
                    System.out.println("线程1得到锁A");
                    try {
                        //休眠1s，让线程2先得到锁B
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程1尝试获取锁B");
                    synchronized (lockB){
                        System.out.println("线程1得到锁B");
                    }
                }
            }
        },"t1");
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lockB){
                    System.out.println("线程2得到锁B");
                    try {
                        //休眠1s，让线程1先得到锁A
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程2尝试获取锁A");
                    synchronized (lockA){
                        System.out.println("线程2得到锁A");
                    }
                }
            }
        },"t2");
        t2.start();
    }
}
