package com.wd.kt.generic

open class Box<T> {
    open var t: T? = null
}


class IntegerBox : Box<Int>() {

    /**
     *
     */
    override var t: Int?
        get() = super.t
        set(value) {}

}