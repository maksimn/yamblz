package io.github.maksimn.rxjava2examples.java;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

class MyListTransformer {

    static <T extends List<Number>> void transform(T list) {
        list.add(123);
        list.add(12.345f);
    }

    static void producerGet(List<? extends Number> producer) {
        AtomicInteger ai = (AtomicInteger) producer.get(0);
        int i = (Integer) producer.get(1);
        long l = (Long) producer.get(2);
        float f = (Float) producer.get(3);
    }

    static void consumerAdd(List<? super Number> consumer) {
        consumer.add(new AtomicInteger(1));
        consumer.add(123);
        consumer.add(123456L);
        consumer.add(12.345f);
    }
}

public class GenericsExamples {
    public static void example1() {
        List<Number> list1 = new ArrayList<>(); // Ok
        List<Integer> list2 = new ArrayList<>(); // Not ok
        List<Float> list3 = new ArrayList<>(); // Not ok

        MyListTransformer.transform(list1); // ok
        // MyListTransformer.transform(list2); // Not ok
        // MyListTransformer.transform(list3); // Not ok
    }
}
