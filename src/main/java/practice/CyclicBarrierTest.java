package practice;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hklv on 2016/8/3.
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        final CyclicBarrier cb = new CyclicBarrier(3);
        for (int i = 0; i < 3; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((long) (Math.random() * 1000));
                        System.out.println("线程" + Thread.currentThread().getName() + "即将到达集合地点1，当前已有" + (cb.getNumberWaiting() + 1) + "个已到达" + (cb.getNumberWaiting() == 2 ? "都到齐了，继续走啊" : "正在等候"));
                        cb.await();
                        Thread.sleep((long) (Math.random() * 1000));
                        System.out.println("线程" + Thread.currentThread().getName() + "即将到达集合地点2，当前已有" + (cb.getNumberWaiting() + 1) + "个已到达" + (cb.getNumberWaiting() == 2 ? "都到齐了，继续走啊" : "正在等候"));
                        cb.await();
                        Thread.sleep((long) (Math.random() * 1000));
                        System.out.println("线程" + Thread.currentThread().getName() + "即将到达集合地点3，当前已有" + (cb.getNumberWaiting() + 1) + "个已到达" + (cb.getNumberWaiting() == 2 ? "都到齐了，继续走啊" : "正在等候"));
                        cb.await();
                    } catch (InterruptedException e) {

                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                }
            };
            exec.execute(runnable);
        }
        exec.shutdown();
    }
}
