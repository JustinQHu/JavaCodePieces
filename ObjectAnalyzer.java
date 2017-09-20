import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huqijun on 9/20/2017.
 */
public class ObjectAnalyzer {


    /**
     * Convert an object to a string representation that lists all fields
     * @param obj an object
     * @return a string with the object's class name and all fields names and values
     */
    public String toString(Object obj)
    {
        if(obj == null ) return null;

        Class cl = obj.getClass();

        //obj is String
        if(cl == String.class) return (String)obj;

        //obj is Array
        if(cl.isArray())
        {
            String ret = cl.getComponentType() + "[]{";
            for(int i = 0; i < Array.getLength(obj) ; i++)
            {
                if(i > 0) ret +=",";
                Object val = Array.get(obj, i);
                if(cl.getComponentType().isPrimitive()) ret += val;
                else ret += toString(val);
            }

            return ret + "}";
        }

        //normal object
        String ret = cl.getName();
        //inspect the field of this class and all super classes
        do
        {
            ret += "{";
            Field[] fields = cl.getDeclaredFields();
            AccessibleObject.setAccessible(fields,true);

            //get the name and value of all fileds
            for(Field f : fields)
            {
                //not static filed
                if(! Modifier.isStatic(f.getModifiers()))
                {
                    if(!ret.endsWith("{"))  ret += ",";
                    ret += f.getName() + "=";

                    try
                    {
                        Class t = f.getType();
                        Object val = f.get(obj);
                        if(t.isPrimitive()) ret += val;
                        else ret += toString(val);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            ret += "}";
            cl = cl.getSuperclass();
        }
        while(cl != null);
        return ret;
    }

    //test
    public static void main(String[] args)
    {
        List<Integer> squares = new ArrayList<>();
        for(int i = 1; i<= 5 ;  ++i)
        {
            squares.add(i*i);
        }

        System.out.println(new ObjectAnalyzer().toString(squares));

        Employee e = new Employee("huqijun",14400,2014,7,15);
        System.out.println(new ObjectAnalyzer().toString(e));

    }

}
