package com.wd.polymorphism

fun main() {
    val a1: A = A()
    val a2: A = B()
    val b: B = B()
    val c: C = C()
    val d: D = D()
    println(a1.show(b)) // A show A
    println(a1.show(c)) // A show A
    println(a1.show(d)) // A show D
    println(a2.show(b)) // B show A
    println(a2.show(c)) // B show A
    println(a2.show(d)) // A show D
    println(b.show(b)) // B show B
    println(b.show(c)) // B show B
    println(b.show(d)) // A show D
}
