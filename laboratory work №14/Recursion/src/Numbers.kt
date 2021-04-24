import kotlin.math.abs

fun main() {
    val number = inputNumber()

    // task 1
    // println("Sum of digits (recursion up): ${sumDigitsUp(number)}")

    // task 2
    // println("Sum of digits (tail recursion): ${sumDigitsTail(number)}")

    // task 3.1
    // println("Mult of digits (recursion up): ${multDigitsUp(number)}")

    // task 3.1
    // println("Mult of digits (tail recursion): ${multDigitsTail(number)}")

    // task 3.2
    // println("Min digit (recursion up): ${minDigitUp(number)}")

    // task 3.2
    // println("Min digit (tail recursion): ${minDigitTail(number)}")

    // task 3.3
    println("Max digit (recursion up): ${maxDigitUp(number)}")

    // task 3.3
    println("Max digit (tail recursion): ${maxDigitTail(number)}")
}

// ввод числа
fun inputNumber(): Int {
    print("Enter the number:> ")
    val number = readLine()!!.toString()

    return try {
        number.toInt()
    }
    catch (e: NumberFormatException) {
        inputNumber()
    }
}

// task 1: сумма цифр числа (рекурсия вверх)
fun sumDigitsUp(number: Int): Int =
    if (number / 10 == 0)
        abs(number % 10)
    else
        sumDigitsUp(number / 10) + abs(number % 10)

// task 2: сумма цифр числа (хвостовая рекурсия)
fun sumDigitsTail(number: Int, sum: Int): Int =
    if (number / 10 == 0)
        sum + abs(number % 10)
    else
        sumDigitsTail(number / 10, sum + abs(number % 10))

fun sumDigitsTail(number: Int): Int =
    sumDigitsTail(number, 0)

// task 3.1: проиведение цифр числа (рекурсия вверх)
fun multDigitsUp(number: Int): Int =
    if (number / 10 == 0)
        abs(number % 10)
    else
        multDigitsUp(number / 10) * abs(number % 10)

// task 3.1: произведение цифр числа (хвостовая рекурсия)
fun multDigitsTail(number: Int, mult: Int): Int =
    if (number / 10 == 0)
        mult * abs(number % 10)
    else
        multDigitsTail(number / 10, mult * abs(number % 10))

fun multDigitsTail(number: Int): Int =
    multDigitsTail(number, 1)

// task 3.2: минимальная цифра числа (рекурсия вверх)
fun minDigitUp(number: Int): Int {
    val newNumber = number / 10
    val digit = abs(number % 10)

    return if (newNumber != 0) {
        val min = minDigitUp(newNumber)
        if (digit < min)
            digit
        else min
    }
    else digit
}

// task 3.2: минимальная цифра числа (хвостовая рекурсия)
fun minDigitTail(number: Int): Int {
    fun minDigitTail(number: Int, min: Int): Int {
        val newNumber = number / 10
        val digit = abs(number % 10)

        val newMin =
            if (digit < min)
                digit
            else min

        return if (newNumber != 0)
            minDigitTail(newNumber, newMin)
        else newMin
    }
    return minDigitTail(number, 10)
}

// task 3.3: максимальная цифра числа (рекурсия вверх)
fun maxDigitUp(number: Int): Int {
    val newNumber = number / 10
    val digit = abs(number % 10)

    return if (newNumber != 0) {
        val max = maxDigitUp(newNumber)
        if (digit > max)
            digit
        else max
    }
    else digit
}

// task 3.3: максимальная цифра числа (хвостовая рекурсия)
fun maxDigitTail(number: Int): Int {
    fun maxDigitTail(number: Int, max: Int): Int {
        val newNumber = number / 10
        val digit = abs(number % 10)

        val newMax =
            if (digit > max)
                digit
            else max

        return if (newNumber != 0)
            maxDigitTail(newNumber, newMax)
        else newMax
    }
    return maxDigitTail(number, -1)
}