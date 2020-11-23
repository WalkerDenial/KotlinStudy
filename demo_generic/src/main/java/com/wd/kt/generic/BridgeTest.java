package com.wd.kt.generic;

/**
 * @Author Walker Denial
 * @Email WalkerDenial@gmail.com
 * @Time Created at 2020/11/24 00:12.
 */

class Box<T> {

    public T t;

    public void setT(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }
}

class IntegerBox extends Box<Integer> {

    @Override
    public Integer getT() {
        return super.getT();
    }

    @Override
    public void setT(Integer integer) {
        super.setT(integer);
    }

}

class BridgeTest {

    public static void main(String[] args) {

    }

}