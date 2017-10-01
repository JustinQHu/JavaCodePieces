package multithread.ThreadPool;

/**
 * Created by huqijun on 10/1/2017.
 */
public class ThreadPoolTest {

    public static void main(String[] args)
    {
        Pool mypool = new Pool(100);

        for(int i =0 ;  i< 10000;  i++)
        {
            mypool.addTask(new TestTask(i));
        }
    }

}



class  TestTask implements Runnable
{
    public int taskIndex;

    public TestTask(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread()+ " is executing the " + taskIndex +" task!");
    }
}
