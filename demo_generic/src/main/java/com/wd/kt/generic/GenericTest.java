package com.wd.kt.generic;

public class GenericTest {

    public <T> void test() {
        // 原始类型
        TestBox box1 = new TestBox();
        // signature Lcom/wd/generic/TestBox<Ljava/lang/Object;>;
        // declaration: box2 extends com.wd.generic.TestBox<java.lang.Object>
        TestBox<Object> box2 = new TestBox<>();
        // signature Lcom/wd/generic/TestBox<*>;
        // declaration: box3 extends com.wd.generic.TestBox<?>
        TestBox<?> box3 = new TestBox<>();
        // signature Lcom/wd/generic/TestBox<TT;>;
        // declaration: box4 extends com.wd.generic.TestBox<T>
        TestBox<T> box4 = new TestBox<>();
        // signature Lcom/wd/generic/TestBox<+TT;>;
        // declaration: box5 extends com.wd.generic.TestBox<? extends T>
        TestBox<? extends T> box5 = new TestBox<>();
        // signature Lcom/wd/generic/TestBox<-TT;>;
        // declaration: box6 extends com.wd.generic.TestBox<? super T>
        TestBox<? super T> box6 = new TestBox<>();
    }

}
