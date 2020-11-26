package com.wd.kt.generic

/**
 *
 * @Author Walker Denial
 * @Email WalkerDenial@gmail.com
 * @Time Created at 11/26/20 10:54 PM.
 */
class GenericMethod {

    /**
     * int 相加
     */
    fun add(vararg num: Int): Int {
        return num.sum()
    }

    /**
     * float 相加
     */
    fun add(vararg num: Float): Float {
        return num.sum()
    }

    /**
     * 如果在加 double、long、short 等等类型的时候，还要额外写 很多个方法，
     * 如果是混合组合，例如：a:Int, b: Double，以及参数个数不同，add 方法数量只会呈指数级增长，
     * 那么有没有方法实现动态类型转换呢？
     * 答案当然是有的，泛型就是为了解决这种问题而诞生的。
     * 接下来，请看泛型写法。
     */
    fun <N : Number> add(vararg num: N): Number {
        return num.sumOf { it.toDouble() }
    }

    /**
     * List 原始类型示例
     */
    fun testList() {
        val list = arrayListOf<Any>()
        list.add("Hello")
        list.add("Generic")
        list.add(99)
        for (i in list.indices) {
            /**
             * 在 i == 2 的时候，会出现转换出错 ClassCastException
             * Int can not cast to String
             */
            val item = list[i] as String
            println(item)
        }
    }

    /**
     * List 泛型类型示例
     */
    fun testListGeneric() {
        val list = arrayListOf<String>()
        list.add("Hello")
        list.add("Generic")
        // list.add(99) // 此处会直接报错，编译不通过，99 不是 String 类型
        for (i in list.indices) {
            /**
             * 在 i == 2 的时候，会出现转换出错 ClassCastException
             * Int can not cast to String
             */
            val item = list[i] // 此处也不需要强转了，已经指定了 String 类型，系统会自动推断出来对应的类型
            println(item)
        }
    }

    /**
     * 标准的单个类型参数的泛型方法
     */
    fun <T> genericMethod(vararg t: T) {
        println(t.javaClass)
    }

    /**
     * 错误示例
     * 原因：E 并没有被定义，因此找不到 E，编译不会通过
     */
//    fun setKey(key:E) :E {
//
//    }

    /**
     * 注意，这里不是泛型方法
     * 原因：这只是一个普通方法，只是用一个泛型类作为参数而已
     */
//    fun show(box: Box<String>) {
//
//    }

    /**
     * 错误示例
     * 原因：E 没有被定义
     */
//    fun <T> show(box: Box<E>): T {
//
//    }

    /**
     * 错误示例
     * 原因：在非泛型类里面，T 并没有被声明
     */
//    fun show(obj: T) {
//
//    }

}