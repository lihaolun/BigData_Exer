package javatest;

public class AbstractImp {
    public static void main(String[] args) {
        A2 a2 = new A2() {
            @Override
            public void cry() {

                System.out.println("cry");
            }
        };
        a2.cry();
    }
}
abstract class A2{
    abstract public void cry();
}
