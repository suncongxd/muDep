package nativemethod.fuzzing;

/**
 * Created by myw on 18-6-8.
 */

public class Pair<T>
{
    private T imutable;
    private T mutable;

    public Pair() { imutable = null; mutable = null; }
    public Pair(T first, T second) { this.imutable = first;  this.mutable = second; }

    public T getEqualNum() { return imutable; }
    public T getAllNum() { return mutable; }

    //public void setEqualNum(T newValue) { imutable = newValue; }
    //public void setAllNum(T newValue) { mutable = newValue; }
}
