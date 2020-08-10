package javatest;

import java.util.ArrayList;

public class JavaCollection {
    public static void main(String[] args) {
        //scala的不可变集合类似java的数组
        int [] nums = new int[3];
        nums[0] = 1;
        nums[1] = 2;
        nums[2] = 3;
       // nums[3] = 4;

        //可变集合举例
        ArrayList list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        System.out.println(list+"地址"+list.hashCode());
        list.add("c");
        System.out.println(list+"地址"+list.hashCode());

        System.out.println("abc\ru");


   /*     try {
            // 可疑代码
            int i = 0;
            int b = 10;
            int c = b / i; // 执行代码时，会抛出ArithmeticException异常
        } catch (ArithmeticException ex) {
            ex.printStackTrace();
        } catch (Exception e) { //java中不可以把返回大的异常写在前，否则报错!!
            e.printStackTrace();
        } finally {
            // 最终要执行的代码
            System.out.println("java finally");
        }

        System.out.println("ok~~~继续执行...");*/

    }
}
