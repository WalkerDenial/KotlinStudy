package com.wd.kt.generic

class IntegerBox : Box<Int>() {
    override var t: Int?
        get() = super.t
        set(value) {}
}