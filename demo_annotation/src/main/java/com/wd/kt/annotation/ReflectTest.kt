package com.wd.kt.annotation

/**
 *
 * @Author Walker Denial
 * @Email WalkerDenial@gmail.com
 * @Time Created at 12/4/20 11:53 PM.
 * @Desc 测试反射信息
 */
class ReflectTest {

    inner class Fruit {

        private var name = "Fruit"

        fun showName() {
            println("name is $name")
        }

        fun updateName(name: String) {
            this.name = name
        }

    }

    // 反射测试
    fun test() {
        val clazz = Class.forName(Fruit::class.java.canonicalName)
        val methods = clazz.declaredMethods.orEmpty()
        for (item in methods) {
            println(
                "Method: ${item.name}, " +
                        "parameterCount: ${item.parameterCount}, " +
                        "returnType: ${item.returnType}"
            ) // 打印 Fruit 中所有的方法名称
        }

        val fields = clazz.declaredFields.orEmpty()
        for (item in fields) {
            println("Field: ${item.name}, ${item.type}") // 打印 Fruit 中所有的属性
        }
    }

}