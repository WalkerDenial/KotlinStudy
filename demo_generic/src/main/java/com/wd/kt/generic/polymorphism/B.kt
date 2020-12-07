package com.wd.polymorphism

open class B : A() {

    fun show(obj: B): String {
        return "B show B"
    }

    override fun show(obj: A): String {
        return "B show A"
    }

}