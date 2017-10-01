package multithread.ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by huqijun on 10/1/2017.
 */
public class Pool {

    private int poolSize;

    BlockingQueue<Runnable> tasks;

    List<Thread> threads;

    /**
     * Construct a Thread pool
     * @param poolSize
     */
    public Pool(int poolSize) {
        this.poolSize = poolSize;
        tasks = new LinkedBlockingQueue<>();
        threads = new ArrayList<>();
        startPool();
    }

    /**
     * 向线程池中添加任务
     * @param r
     */
    public void addTask(Runnable r)
    {
        tasks.add(r);
    }

    /**
     * 启动线程池(中的线程)
     */
    public void startPool()
    {
        for(int i =0 ; i< poolSize; i++)
        {
            Thread t = new Thread(new PoolThread(tasks));
            threads.add(t);
            t.start();
        }
    }

}


/**
 * 线程池使用线程，  自己的run 要不能结束，要一直存活
 * 获取到Runnable 任务后，  显示的调用任务的Run 方法来执行任务
 */
class PoolThread implements Runnable
{
    private  BlockingQueue<Runnable> tasks;

    public PoolThread(BlockingQueue<Runnable> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread() + "Starts!");
        while (true)
        {
            try
            {
                Runnable r = tasks.take();
                r.run();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
