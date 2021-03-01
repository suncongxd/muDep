package test;

/**
 * Created by myw on 20-3-27.
 */

public class A {
    String str;
    B b;
    private A(String str, B b){
        this.b = b;
        this.str = str;
    }

    public static A genA(String str, B b){
        return new A(str,b);
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }
}
