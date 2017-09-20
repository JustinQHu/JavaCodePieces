import java.lang.reflect.Method;

/**
 * this program shows how to invoke methods through reflection
 * Created by huqijun on 9/20/2017.
 */
public class MethodPointerTest {
    public static void main(String[] args)
    {
        try
        {
            Method square = MethodPointerTest.class.getMethod("square",double.class);
            Method sqrt = Math.class.getMethod("sqrt",double.class);

            printTable(1,10,10,square);
            printTable(1,10,10,sqrt);
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * returns the square of a number
     * @param x
     * @return
     */
    public static double square(double x)
    {
        return x*x;
    }


    /**
     * Prints a table with x- and y-valus for a method
     * @param from
     * @param to
     * @param n
     * @param f
     */
    public static void printTable(double from, double to, int n, Method f)
    {
        System.out.println(f);

        double dx  =  (to - from )/(n -1);

        for(double x  = from ; x <= to;  x+= dx)
        {
            try
            {
                double y = (Double)f.invoke(null,x);
                System.out.printf("%10.4f  |  %10.4f%n",x,y);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
