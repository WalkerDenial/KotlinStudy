package com.wd.kt.generic.polymorphism

open class A {

    open fun show(obj: D): String {
        return "A show D"
    }

    open fun show(obj: A): String {
        return "A show A"
    }

}