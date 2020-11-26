package com.wd.kt.generic

open class Box<T> {

    /**
     * 虽然这个是泛型类，此处类型也为 T，但是 getT() 不是一个泛型方法，这个 T 是创建对象时已经声明过的一个明确类型，所以不是泛型
     */
    open var t: T? = null
}

/**
 * 继承 Box，子类的 t:Any 与 父类的 t:Int 想要关联起来，然鹅两个类型却不相同，
 * 那么，两者是如何联系起来的呢？
 * 查看字节码，即可知道答案，有一个 bridge 将父类的 t 与子类的 t 进行关联，从而不会出现 ClassCastException
 */
class IntegerBox : Box<Int>() {

    override var t: Int?
        get() = super.t
        set(value) {}

}