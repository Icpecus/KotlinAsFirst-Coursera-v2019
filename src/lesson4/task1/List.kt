@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson3.task1.isPrime
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double =
    sqrt(v.fold(0.0) { prevRes, element -> (prevRes + sqr(element)) })

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double =
    if (list.isEmpty()) 0.0 else
        list.fold(0.0) { prevRes, element -> prevRes + element } / list.size

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    //var result = mutableListOf<Double>()
    if (list.isNotEmpty()) {
        var avg = 0.0
        for (i in 0 until list.size)
            avg += list[i]
        avg /= list.size
        //result = list.map(fun(i: Double): Double = i - avg).toMutableList()
        for (i in 0 until list.size)
            list[i] = list[i] - avg
    }
    return list //result
}

/* Средняя
    *
    * Найти скалярное произведение двух векторов равной размерности,
    * представленные в виде списков a и b.Скалярное произведение считать по формуле:
    * C = a1b1 + a2b2 + ... + aNbN.Произведение пустых векторов считать равным 0.
    */
fun times(a: List<Int>, b: List<Int>): Int {
    var sum = 0
    if (a.isEmpty() || b.isEmpty())
        return 0
    else {
        for (i in 0 until a.size)
            sum += a[i] * b[i]
    }
    return sum
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var res = 0
    for (i in 0 until p.size)
        res += p[i] * (x.toDouble().pow(i.toDouble())).toInt()
    return res
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    var sum = 1
    for (i in 1 until list.size) {
        sum += list[i]
        list[i] = sum
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val list = mutableListOf<Int>()
    var newN = n

    for (i in 2..Int.MAX_VALUE) {
        if (isPrime(newN)) {
            list.add(newN)
            break
        }
        while (newN % i == 0) {
            list.add(i)
            newN /= i
        }
        if (newN == 1)
            break
    }
    return list.sorted()
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String {
    val list = factorize(n)
    return list.joinToString(separator = "*", prefix = "$n -> ")
}

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val list = mutableListOf<Int>()
    var newN = n
    var remainder: Int
    if (base < n)
        while (newN > base - 1) {
            remainder = newN % base
            newN /= base
            list.add(remainder)
        }
    list.add(newN)
    val invertList = mutableListOf<Int>()
    for (i in 0 until list.size)
        if (list.size > 1)
            invertList.add(list[list.size - i - 1])
        else
            invertList.add(n)
    return invertList
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    val result = convert(n, base)
    val strList = mutableListOf<Char>()
    for (i in 0 until result.size)
        if (result[i] >= 10)
            strList.add(result[i].toChar() + 87)
        else
            strList.add(result[i].toChar() + 48)
    return strList.joinToString(separator = "")
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var result = 0
    for (i in 0 until digits.size)
        result += digits[i] * base.toDouble().pow(digits.size - 1 - i.toDouble()).toInt()
    return result
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    val list = mutableListOf<Char>()
    val res = mutableListOf<Int>()
    for (i in 0 until str.length)
        list.add(str[i])
    for (i in 0 until list.size)
        if (list[i] > 96.toChar())
            res.add(list[i].toInt() - 87)
        else
            res.add(list[i].toInt() - 48)
    return decimal(res, base)
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var number = n
    var str = ""
    val romans = arrayOf(1000, 500, 100, 50, 10, 5, 1, 0, 0)
    val arabics = arrayOf('M', 'D', 'C', 'L', 'X', 'V', 'I')
    var count: Int
    val list = n.toString()
    for (i in 0..6) {
        if (number == 0) break
        count = 0

        while (number - romans[i] >= 0) {
            number -= romans[i]
            count++
        }
        if (count == 4) {
            str += arabics[i]
            str += arabics[i - 1]
        } else {
            for (k in 1..count)
                str += arabics[i]
        }
        if (romans[i] - number <= romans[i + 2] && (number != n || list[0] == '9')) {
            str += arabics[i + 2]
            number += romans[i + 2]
            str += arabics[i]
            number -= romans[i]
        }
    }
    return str
}

/*fun main(){
    var str: String
    for (i in 900..999 step 2 ) {
        println("$i -> ${roman(i)}")
    }
}*/

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun convertToRus(a: Int): String {
    return when (a) {
        0 -> ""
        1 -> "один"
        2 -> "два"
        3 -> "три"
        4 -> "четыре"
        5 -> "пять"
        6 -> "шесть"
        7 -> "семь"
        8 -> "восемь"
        9 -> "девять"
        10 -> "десять"
        11 -> "одиннадцать"
        12 -> "двенадцать"
        13 -> "тринадцать"
        14 -> "четырнадцать"
        15 -> "пятнадцать"
        16 -> "шестнадцать"
        17 -> "семнадцать"
        18 -> "восемнадцать"
        19 -> "девятнадцать"
        20 -> "двадцать"
        30 -> "тридцать"
        40 -> "сорок"
        50 -> "пятьдесят"
        60 -> "шестьдесят"
        70 -> "семьдесят"
        80 -> "восемьдесят"
        90 -> "девяносто"
        100 -> "сто"
        200 -> "двести"
        300 -> "триста"
        400 -> "четыреста"
        500 -> "пятьсот"
        600 -> "шестьсот"
        700 -> "семьсот"
        800 -> "восемьсот"
        900 -> "девятьсот"
        else -> "ошибка"
    }
}

fun convert3(n: Int, isThousand: Boolean): String {
    val k: Int
    val l: Int
    val m: Int
    var str = ""
    if (n > 0) {
        k = n / 100
        l = (n % 100) / 10
        m = n % 10

        str += convertToRus(k * 100)
        str += if (k == 0) "" else " "
        if (l * 10 + m < 20 && l != 0) {
            str += convertToRus(l * 10 + m)
            str += if (m == 0) "" else " "
        } else {
            str += convertToRus(l * 10)
            str += if (l == 0) "" else " "
            str += if (m == 1 && isThousand) "одна "
            else if (m == 2 && isThousand) "две "
            else
                convertToRus(m)
        }
        if (isThousand) {
            str += when (m) {
                1 -> "тысяча"
                2, 3, 4 -> "тысячи"
                else -> "тысяч"
            }
            str += " "
        }
    }
    return str
}

fun russian(n: Int): String {
    var str = ""
    val second3 = n % 1000
    val first3 = n / 1000
    str += convert3(first3, true)
    str += convert3(second3, false)
    return str.removePrefix(" ").removeSuffix(" ")
}