package com.wd.kt.generic;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class GenericTest {

    public Map<String, Number> map = new HashMap<>();

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

    public void parameterizedTypeTest() throws NoSuchFieldException {
        Field field = GenericTest.class.getDeclaredField("map"); // 获取 field
        ParameterizedType paramType = (ParameterizedType) field.getGenericType(); // 获取 ParameterizedType，ParameterizedType 中存储的有泛型信息
        for (Type item : paramType.getActualTypeArguments()) { // 获取实际泛型信息，并打印
            System.out.println(item.getTypeName());
            // 打印结果为：
            // java.lang.String
            // java.lang.Number
        }
    }

}
