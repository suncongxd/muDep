package test;

import android.util.Log;

import java.util.*;

/**
 * Created by myw on 20-3-27.
 */

public class B implements Interface1{
    List<Integer> list ;
    public B(List<Integer> list){
        this.list = list;
    }

    public void set(int index, int newVal){
        list.set(index, newVal);
    }

    @Override
    public void print() {
        if(list ==null){
            return;
        }
        StringBuilder sb = new StringBuilder();
        for(Integer i: list){
            sb.append(i+" ");
        }
        Log.v("list content",sb.toString());
    }
}
