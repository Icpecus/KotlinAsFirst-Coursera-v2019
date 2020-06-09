@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import lesson2.task2.daysInMonth
import java.lang.Integer.min
import kotlin.IllegalArgumentException

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun monthStrToDigit(month: String): Int {
    return when (month) {
        "января" -> 1
        "февраля" -> 2
        "марта" -> 3
        "апреля" -> 4
        "мая" -> 5
        "июня" -> 6
        "июля" -> 7
        "августа" -> 8
        "сентября" -> 9
        "октября" -> 10
        "ноября" -> 11
        "декабря" -> 12
        else -> -1
    }
}

fun dateStrToDigit(str: String): String {
    val parts = str.split(" ")
    if (parts.size == 3)
        if (parts[0].toInt() <= daysInMonth(monthStrToDigit(parts[1]), parts[2].toInt()))
            return String.format("%02d.%02d.%04d", parts[0].toInt(), monthStrToDigit(parts[1]), parts[2].toInt())
    return ""
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun monthDigitToStr(month: Int): String {
    return when (month) {
        1 -> "января"
        2 -> "февраля"
        3 -> "марта"
        4 -> "апреля"
        5 -> "мая"
        6 -> "июня"
        7 -> "июля"
        8 -> "августа"
        9 -> "сентября"
        10 -> "октября"
        11 -> "ноября"
        12 -> "декабря"
        else -> ""
    }
}

fun dateDigitToStr(digital: String): String {
    val parts = digital.split(".")
    val check = digital.replace(".", "").toCharArray()
    for (item in check)
        if (item.toInt() < 47 || item.toInt() > 58)
            return ""

    if (parts.size == 3)
        if (parts[0].toInt() <= daysInMonth(parts[1].toInt(), parts[2].toInt()))
            return String.format(
                "%d ${monthDigitToStr(parts[1].toInt())} %04d",
                parts[0].toInt(),
                parts[2].toInt()
            )
    return ""
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phone: String): String {
    val numbers = phone.filter { it != ' ' && it != '-' }
    val charsOfPhone = numbers.toCharArray()
    var count = 0
    for (item in charsOfPhone) {
        if (charsOfPhone[count] == '(' && !charsOfPhone[count + 1].isDigit())
            return ""
        count++
        if (item == '+' && count > 1)
            return ""
        if (item.toInt() < 47 || item.toInt() > 58)
            if (item == '(' || item == ')' || item == '+')
                continue
            else
                return ""
    }

    return numbers.filter { it != '(' && it != ')' }
}


/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    val numbers = jumps.replace("% ", "").replace("- ", "")
    var z = -1
    if (numbers.replace(" ", "").toCharArray().all { it.isDigit() }) {
        val res = numbers.split(" ")
        for (i in 0 until res.size)
            if (!res[i].equals("") && !res[i].equals("-") && !res[i].equals("%"))
                if (res[i].toInt() > z)
                    z = res[i].toInt()
    }
    return z
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    val res = jumps.filter { it != ' ' && it != '%' }.toCharArray()
    var new = ""
    var max = 0
    for (i in 0 until res.size) {
        if (!res[i].isDigit() && !res[i].equals('-') && !res[i].equals('+'))
            return -1
        else {
            if (res[i].isDigit() || res[i].equals('-'))
                new = ""
            else if (res[i].equals('+')) {
                for (k in i - 3 until i)
                    new += res[k]
                if (new.toInt() > max)
                    max = new.toInt()
            }
        }
    }
    return max
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    val str = expression.split(" ")
    var res = 0
    try {
        if (str[0].toCharArray().all { it.isDigit() })
            res += str[0].toInt()
        else
            for (i in str[0].toCharArray())
                res = i.toString().toInt()

        for (i in 1 until str.size)
            if (str[i] == "+")
                res += str[i + 1].toInt()
            else if (str[i] == "-")
                res -= str[i + 1].toInt()
            else if (!str[i].toCharArray().all { it.isDigit() })
                for (k in str[i].toCharArray())
                    res = k.toString().toInt()
        return res

    } catch (e: IllegalArgumentException) {
        throw e
    }
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val res = str.toLowerCase().split(" ")
    var index = 0
    for (i in 0 until res.size - 1) {
        index += res[i].toCharArray().size + 1
        if (res[i] == res[i + 1])
            return index - res[i].toCharArray().size - 1
    }
    return -1
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String {
    val res = description.filter { it != ';' }.split(" ")
    val map = mutableMapOf<String, Double>()
    var max = 0.0
    var str = ""
    if (res.size != 1) {
        for (i in 0 until res.size step 2)
            map[res[i]] = res[i + 1].toDouble()
        for ((key, value) in map)
            if (max < value) {
                max = value
                str = key
            }
    }
    return str
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int {
    val romanToArabic =
        mapOf('M' to 1000, 'D' to 500, 'C' to 100, 'L' to 50, 'X' to 10, 'V' to 5, 'I' to 1, 'A' to 0)
    val romanChars = roman.toCharArray()
    var res = 0
    var count = 0
    var previousValue = 100000
    for (i in 0 until romanChars.size) {
        for ((rom, arabic) in romanToArabic)
            if (romanChars[i] == rom) {
                count++
                if (romanChars.size > i) {
                    if (arabic <= previousValue)
                        res += arabic
                    else
                        res += arabic - 2 * previousValue
                    previousValue = arabic
                } else
                    res += arabic
                break
            }
    }
    if (count != romanChars.size)
        res = -1

    return res
}

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {
    val possibleCommands = charArrayOf('<', '>', '+', '-', ' ', '[', ']')
    val realcommands = commands.toCharArray()
    val resCells = mutableListOf<Int>()
    for (i in 0 until cells)
        resCells.add(0)

    var sensor = cells / 2
    var numberOfOpenBrace = 0
    var numberOfCloseBrace = 0
    var countOfSymbols = 0

    for (i in 0 until realcommands.size)
        for (j in possibleCommands)
            if (realcommands[i] == j) {
                countOfSymbols++
                if (j == '[')
                    numberOfOpenBrace++
                else if (j == ']')
                    numberOfCloseBrace++

            }
    if (countOfSymbols != realcommands.size || numberOfCloseBrace != numberOfOpenBrace)
        throw IllegalArgumentException()

    for (i in 0 until min(limit, realcommands.size)) {
        if (sensor >= cells || sensor < 0)
            throw IllegalStateException()

        when (realcommands[i]) {
            possibleCommands[0] -> sensor--
            possibleCommands[1] -> sensor++
            possibleCommands[2] -> resCells[sensor]++
            possibleCommands[3] -> resCells[sensor]--
            possibleCommands[4] -> 0
            possibleCommands[5] -> 0
            possibleCommands[6] -> 0
        }
    }

    return resCells
}
