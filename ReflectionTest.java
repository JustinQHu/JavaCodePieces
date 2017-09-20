import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by huqijun on 9/20/2017.
 */
public class ReflectionTest {


    public static void main(String[] args) {
        Employee e = new Employee("huqijun",14400,2014,7,15);

        System.out.println("please input the name of Class :");
        Scanner in = new Scanner(System.in);
        String name = in.next();

        printClass(name);
    }

    /**
     * prints all information of a class
     * @param name
     */
    public static void printClass(String name)
    {
        try
        {
            Class cl = Class.forName(name);
            Class supercl = cl.getSuperclass();
            String modifiers = Modifier.toString(cl.getModifiers());
            if(modifiers.length() >0 ) System.out.print(modifiers + " ");
            System.out.print("class " + name);
            if(supercl != null && supercl != Object.class)
                System.out.print(" entends " + supercl.getName());

            System.out.print("\n{\n");
            printConstructors(cl);
            System.out.println();
            printMethods(cl);
            System.out.println();
            printFields(cl);
            System.out.println("}");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }



    /**
     * Prints all constructors of a class
     * @param cl
     */
    public static void printConstructors(Class cl) {
        Constructor[] constructors = cl.getConstructors();
        for(Constructor c : constructors) {
            String name = c.getName();
            System.out.print("  ");
            String modifiers = Modifier.toString(c.getModifiers());

            if(modifiers.length() > 0 )  System.out.print(modifiers + " ");
            System.out.print(name + "(");

            Class[] paramTypes = c.getParameterTypes();
            for(int j = 0; j < paramTypes.length ; j ++)
            {
                if(j > 0 ) System.out.print(", ");
                System.out.print(paramTypes[j].getName());
            }

            System.out.println(");");
        }

    }

    /**
     * Prints all methos of a class
     * @param cl
     */
    public static void printMethods(Class cl) {
        Method[] methods = cl.getDeclaredMethods();
        for(Method m : methods)
        {
            Class retType = m.getReturnType();
            String name = m.getName();

            System.out.print("  ");
            String modifiers = Modifier.toString(m.getModifiers());
            if(modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print(retType.getName() + " " + name + "(");

            Class[] paramTypes = m.getParameterTypes();
            for(int j = 0; j <paramTypes.length ; j++)
            {
                if(j >0 ) System.out.print(", ");
                System.out.print(paramTypes[j].getName());
            }

            System.out.println(");");
        }
    }

    /**
     * Prints all fields of a class
     * @param cl
     */
    public static void printFields(Class cl) {
        Field[] fields = cl.getDeclaredFields();

        for(Field f:fields)
        {
            Class type = f.getType();
            String name = f.getName();
            System.out.print("  ");
            String modifiers = Modifier.toString(f.getModifiers());
            if(modifiers.length() >0 ) System.out.print(modifiers + " ");
            System.out.println(type.getName() + name + ";");
        }
    }

}
