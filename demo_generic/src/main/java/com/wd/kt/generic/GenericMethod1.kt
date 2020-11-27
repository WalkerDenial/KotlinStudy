package com.wd.kt.generic

/**
 *
 * @Author Walker Denial
 * @Email WalkerDenial@gmail.com
 * @Time Created at 11/26/20 10:54 PM.
 */
class GenericMethod1 {

    open class Fruit {
        override fun toString(): String = "Fruit"
    }

    open class Apple : Fruit() {
        override fun toString(): String = "Apple"
    }

    class Person {
        override fun toString(): String = "Person"
    }

    class GenericTest<T> {

        fun show1(t: T) {
            println(t.toString())
        }

        fun <T> show2(t: T) {
            println(t.toString())
        }

        /**
         * 在泛型类中声明泛型方法，这个 E 可以与 T 相同，也可以与 T 不相同
         */
        fun <E> show3(t: E) {
            println(t.toString())
        }

        fun test() {
            val apple = Apple()
            val person = Person()

            val genericTest = GenericTest<Fruit>()
            genericTest.show1(apple)
            // genericTest.show1(person) // 此处报错 Person 并不是 Fruit 的子类

            genericTest.show2(apple)
            genericTest.show2(person)

            genericTest.show3(apple)
            genericTest.show3(person)
        }

    }

    /**
     * 如果想要比较大小，那么就需要限定泛型类型为 Comparable 子集
     */
    fun <T : Comparable<T>> min(a: T, b: T): T {
        return if (a > b) b else a
    }

}