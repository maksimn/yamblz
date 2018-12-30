package io.github.maksimn.rxjava2examples.rx2course.ch02observables;

import io.reactivex.Single;

public class Ch02Code {

    public static void observableCreation() {
        // Задача 1: создать "Observable", тип события - целое число, присвоить ему значение 42
        // "подписаться" на это значение и вывести его в лог в обработчике
        Single.just(42).subscribe(val -> System.out.println("value = " + val.toString()));
    }
}
