package pers.sunrise.util.test;

import pers.sunrise.util.function.RunnableEx;

import java.io.IOException;

public class Test {
    public static void main(String... args) throws Exception {
        RunnableEx.without(Test::f).run();
    }

    public static void f() throws Exception {
        throw new IOException();
    }
}
