package dome.thread;

import sun.misc.Cache;

import java.nio.channels.InterruptibleChannel;
import java.nio.channels.Selector;
import java.util.concurrent.TimeUnit;

/**
 * 第三章内容
 * api讲解
 * @author styzf
 * @date 2021/10/13 21:52
 */
public class Three {
    
    public static void main(String[] args) throws Exception {
//        interruptedDemoPro();// 这个有点复杂
//        interruptedDemo();//该程序输出结果，有一个为true的标识
//        isInterrupted2();
//        isInterrupted1();
//        interruptDemo();// 打断线程阻塞演示
//        currentThread();// 获取当前线程演示
    }
    
    /**
     * 思考
     * interrupted
     * interrupt
     * 这两个都是中断，可是一个会把标识设置为true，一个会为false，当后面有线程要调用可中断方法的时候
     * 如果标识为true的话，那么会被捕获到被中断的异常抛出
     */
    
    private static void interruptedDemoPro() {
//        抹去中断信号，后面在进入可中断方法，不会被捕获到中断信号
        System.out.println("1" + Thread.interrupted());
//        2、中断，设置为true
        Thread.currentThread().interrupt();
        System.out.println("3" + Thread.currentThread().isInterrupted());
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (Exception e) {
//            全部执行会捕获到中断信号，这里捕获到的是2的中断设置
            System.out.println("捕获到中断信号");
        }
    }
    
    /**
     * InterruptedException
     * 在一个死循环里面被中断，该循环不会被打断
     * 还会继续运行下去
     */
    
    /**
     * interrupted和isInterrupted都是调用的该方法
     * 区别其实只在于传参设置的值不一致
     * ClearInterrupted用于控制是否擦除标志
     * @param ClearInterrupted
     * @return
     */
    private native boolean isInterrupted(boolean ClearInterrupted);
    
    /**
     * 静态方法interrupted
     * 调用后会复位interrupt标识
     * @throws InterruptedException
     */
    private static void interruptedDemo() throws InterruptedException {
        Thread thread = new Thread(() -> {
           while (true) {
               System.out.println(Thread.interrupted());
           }
        });
        thread.setDaemon(true);
        thread.start();
        
        TimeUnit.MILLISECONDS.sleep(1);
        thread.interrupt();
    }
    
    /**
     * 判断当前线程是否被中断，对interrupt的判断
     * demo2
     * 可中断方法会擦除Interrupt标识
     * @throws InterruptedException
     */
    private static void isInterrupted2() throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.MINUTES.sleep(1);
                    } catch (InterruptedException e) {
                        System.out.printf("被打断的线程：%s\n", isInterrupted());
                    }
                }
            }
        };

        thread.start();
        System.out.printf("sleep前阻塞线程状态%s\n", thread.isInterrupted());
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.printf("阻塞线程状态%s\n", thread.isInterrupted());
        thread.interrupt();
        System.out.printf("打断后线程状态%s\n", thread.isInterrupted());
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.printf("打断后阻塞线程状态%s\n", thread.isInterrupted());
        
    }
    
    /**
     * 判断当前线程是否被中断，对interrupt的判断
     */
    private static void isInterrupted1() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
//               一直循环
            }
        });
//        打断线程，线程不结束？
//        设置为守护线程后，主线程结束，该线程结束
        thread.setDaemon(true);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.printf("阻塞线程状态%s\n", thread.isInterrupted());
        thread.interrupt();
        System.out.printf("打断线程状态%s", thread.isInterrupted());
    }
    
    /**
     * interrupt演示demo
     */
    private static void interruptDemo() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.MINUTES.sleep(1);
//                TimeUnit.MILLISECONDS.sleep(1);// 比另外一个线程跑的快，就不会被打断阻塞
//                先结束，该线程会进入死亡状态，则尝试对其interrupt会被直接忽略
            } catch(InterruptedException exception) {
                System.out.println("我被打断了");
            }
        });
        
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
//        TimeUnit.SECONDS.sleep(2);
        thread.interrupt();
    }
    
    /**
     * interrupt方法说明
     * @throws InterruptedException
     */
    private static void interrupt() throws InterruptedException {
//      以下方法会导致线程进入阻塞，而要打断阻塞，则需要调用所在线程的interrupt方法
//      打断线程的话会抛出InterruptedException
//      阻塞并不会导致线程的生命周期结束
        Object object = new Object();
        object.wait();
        Thread thread = new Thread();
        Thread.sleep(100L);
        Thread.sleep(100L,0);
        thread.join();
        thread.join(100L);
//        该类的io操作
        InterruptibleChannel interruptibleChannel = null;
//        nio相关类
        Selector selector = null;
        selector.wakeup();
        
//        打断线程阻塞的方法
    
    
    }
    
    /**
     * 获取当前线程
     */
    private static void currentThread() {
        Thread thread = new Thread() {
            @Override
            public void run() {
//                线程内获取当前线程并且进行判断
                Boolean test = Thread.currentThread() == this;
                System.out.println("线程中测试结果：" + test);
                System.out.println("线程名：" + Thread.currentThread().getName());
            }
        };
        thread.start();
    
//        获取主线程
        System.out.println("当前线程名：" + Thread.currentThread().getName());
        String name = Thread.currentThread().getName();
        System.out.println("main".equals(name));
    }
}
