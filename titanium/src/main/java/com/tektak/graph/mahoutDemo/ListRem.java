package com.tektak.graph.mahoutDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tektak on 7/7/15.
 */
public class ListRem {
    public static void main(String[] args) {
        List<Long> list1 = new ArrayList();
        list1.add(1l);
        list1.add(2l);
        list1.add(3l);

        List<Long> list2 = new ArrayList();
        list2.add(4l);
        list2.add(2l);
        list2.add(3l);
        list1.removeAll(list2);
        System.out.println(list1);
    }
}
