package com.wd.kt.lib.base.common

import kotlin.math.pow

/**
 * 采用密封类约束进制类型，避免异常值
 */
sealed class NumberSystemUnit(val offset: Int)
object Binary : NumberSystemUnit(0b000010) // 2 进制
object Octal : NumberSystemUnit(0b001000) // 8 进制
object Hexadecimal : NumberSystemUnit(0b010000) // 16 进制
object Decimal : NumberSystemUnit(0b001010) // 10 进制

/**
 * 缓存信息，低频使用，暂不加锁
 */
private val numUnitCache = mutableMapOf<String, String>()

/**
 * 16、32 进制扩容集合
 */
private val hexArrays = charArrayOf(
    '0',
    '1',
    '2',
    '3',
    '4',
    '5',
    '6',
    '7',
    '8',
    '9',
    'A',
    'B',
    'C',
    'D',
    'E',
    'F',
    'G',
    'H',
    'I',
    'J',
    'K',
    'L',
    'M',
    'N',
    'O',
    'P',
    'Q',
    'R',
    'S',
    'T',
    'U',
    'V',
    'W',
    'X',
    'Y',
    'Z'
)

/**
 * 缓存 key
 */
private inline fun <N : Number> N.getCacheKey(unit: NumberSystemUnit = Decimal): String =
    "${this.toLong()}_${unit.javaClass.name}"

/**
 * 计算不同进制下数据长度
 */
private inline fun <N : Number> N.calcNumLength(unit: NumberSystemUnit = Decimal): Int {
    var length = 0
    var temp = this.toLong()
    do {
        temp /= unit.offset
        length++
    } while (temp != 0L)
    return length
}

/**
 * 数据转换成对应的进制后，输出对应的字符串
 */
fun <N : Number> N.getNumString(unit: NumberSystemUnit = Decimal): String {
    // 判断单位数值是否合法
    if (unit.offset < 2) throw IllegalArgumentException("NumberSystemUnit unit value must greater than 1")
    // 先检查缓存中有没有数据
    val cacheKey = this.getCacheKey(unit)
    if (numUnitCache.containsKey(cacheKey)) return numUnitCache[cacheKey].orEmpty()
    // 当缓存中不存在的时候，计算对应的长度

    // 生成对应的字符串数据
    val length = this.calcNumLength(unit) - 1
    if (length < 0) return ""
    val tempSb = StringBuffer()
    for (i in 0..length) {
        val item = ((this.toLong() / unit.offset.toDouble()
            .pow(length - i)).toLong() % unit.offset).toInt()
        tempSb.append(
            when (item) {
                in hexArrays.indices -> hexArrays[item]
                else -> ""
            }
        )
    }
    // 将数据记录添加进缓存中
    val numString = tempSb.toString()
    numUnitCache[cacheKey] = numString
    return numString
}

/**
 * 根据数据进制获取对应的长度
 * 例如：
 *  数据：555
 *  2 进制长度：10
 *  8 进制长度：4
 *  10进制长度：3
 *  16进制长度：3
 *
 *  注意：如果使用浮点型，会丢失精度
 */
fun <N : Number> N.getNumLength(unit: NumberSystemUnit = Decimal): Int =
    this.getNumString(unit).length

fun <N : Number> N.getNum(index: Int, unit: NumberSystemUnit = Decimal): Int {
    // 先从缓存中获取缓存记录，如果存在，则不重复计算
    val temp = this.getNumString(unit)
    if (index in temp.indices) {
        var tempNum = temp[index].toString().toIntOrNull()
        if (tempNum == null)
            for (i in 10 until hexArrays.size) {
                if (hexArrays[i] != temp[index]) continue
                tempNum = i
                break
            }
        if (tempNum != null) return tempNum
    }
    return -1
}

//fun main() {
//    println(
//        """
//            ${"\t\t"}MIN${"\t\t"}MAX
//            Byte${"\t"}${Byte.MIN_VALUE.getNumLength(Binary)}${"\t\t"}${Byte.MAX_VALUE.getNumLength(Binary)}
//            Short${"\t"}${Short.MIN_VALUE.getNumLength(Binary)}${"\t\t"}${Short.MAX_VALUE.getNumLength(Binary)}
//            Int${"\t\t"}${Int.MIN_VALUE.getNumLength(Binary)}${"\t\t"}${Int.MAX_VALUE.getNumLength(Binary)}
//            Long${"\t"}${Long.MIN_VALUE.getNumLength(Binary)}${"\t\t"}${Long.MAX_VALUE.getNumLength(Binary)}
//        """.trimIndent()
//    )
//    println(
//        """
//            ${"\t\t"}Binary${"\t\t"}Octal${"\t\t"}Hexadecimal${"\t\t"}Decimal
//            99${"\t\t"}${99.getNumLength(Binary)}${"\t\t\t"}${99.getNumLength(Octal)}${"\t\t\t"}${
//            99.getNumLength(
//                Hexadecimal
//            )
//        }${"\t\t\t\t"}${
//            99.getNumLength(Decimal)
//        }
//            111${"\t\t"}${111.getNumLength(Binary)}${"\t\t\t"}${111.getNumLength(Octal)}${"\t\t\t"}${
//            111.getNumLength(
//                Hexadecimal
//            )
//        }${"\t\t\t\t"}${
//            111.getNumLength(Decimal)
//        }
//            222${"\t\t"}${222.getNumLength(Binary)}${"\t\t\t"}${222.getNumLength(Octal)}${"\t\t\t"}${
//            222.getNumLength(
//                Hexadecimal
//            )
//        }${"\t\t\t\t"}${
//            222.getNumLength(Decimal)
//        }
//            333${"\t\t"}${333.getNumLength(Binary)}${"\t\t\t"}${333.getNumLength(Octal)}${"\t\t\t"}${
//            333.getNumLength(
//                Hexadecimal
//            )
//        }${"\t\t\t\t"}${
//            333.getNumLength(Decimal)
//        }
//            444${"\t\t"}${444.getNumLength(Binary)}${"\t\t\t"}${444.getNumLength(Octal)}${"\t\t\t"}${
//            444.getNumLength(
//                Hexadecimal
//            )
//        }${"\t\t\t\t"}${
//            444.getNumLength(Decimal)
//        }
//            555${"\t\t"}${555.getNumLength(Binary)}${"\t\t\t"}${555.getNumLength(Octal)}${"\t\t\t"}${
//            555.getNumLength(
//                Hexadecimal
//            )
//        }${"\t\t\t\t"}${
//            555.getNumLength(Decimal)
//        }
//        """.trimIndent()
//    )
//
//    println()
//
//    var value = 0x123456789
//    for (i in 0 until value.getNumLength(Hexadecimal)) {
//        print("${value.getNum(i, Hexadecimal)}\t")
//    }
//    println()
//
//    value = 123456789
//    for (i in 0 until value.getNumLength(Decimal)) {
//        print("${value.getNum(i, Decimal)}\t")
//    }
//
//    println()
//
//    value = 0b1011011
//    for (i in 0 until value.getNumLength(Binary)) {
//        print("${value.getNum(i, Binary)}\t")
//    }
//
//    println()
//
//    value = 0x123456789ABC
//    for (i in 0 until value.getNumLength(Hexadecimal)) {
//        print("${value.getNum(i, Hexadecimal)}\t")
//    }
//    println()
//    println(value.getNumString(Hexadecimal))
//
//}