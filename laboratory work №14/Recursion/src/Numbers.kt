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
    // println("Max digit (recursion up): ${maxDigitUp(number)}")

    // task 3.3
    // println("Max digit (tail recursion): ${maxDigitTail(number)}")

    // task 4
    // println("\nSum of digits: ${calculate(number, ::sumDigitsUp)}")
    // println("Mult of digits: ${calculate(number, ::multDigitsUp)}")
    // println("Min digit: ${calculate(number, ::minDigitUp)}")
    // println("Max digit: ${calculate(number, ::maxDigitUp)}")

    // task 6: проверить функцию task 5

    // 1-ый и 2-ой пример: считает сумму цифр, если каждая из них больше 5
    // calculateWithCondition(number, ::sumDigitsTail, ::checkDigits)
    // calculateWithCondition(number, ::sumDigitsTail, ::checkDigits, 0)

    // 3-ий пример: считает произведение цифр, если каждая из них больше 5
    // calculateWithCondition(number, ::multDigitsTail, ::checkDigits, 1)
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

// task 4: функция обход числа, которая принимает число,
// функцию и инициализирующее значение по умолчанию (!)
fun calculate(number: Int, func: (number: Int) -> Int, initValue: Int = 0): Int =
    func(number)

// task 5: функция обход числа, которая принимает число,
// функцию с двумя аргументами Int, инициализирующее заполнение
// и функцию c одним аргументом Int, возврщающую true-false (условие для цифр)
fun calculateWithCondition(number: Int, funCalculate: (number: Int, value: Int) -> Int, funCondition: (number: Int) -> Boolean, initValue: Int = 0) {
    if (funCondition(number))
        println("Function result: ${funCalculate(number, initValue)}")
    else
        println("Sorry: digits don't satisfy the condition!")
}

// проверка, все ли цифры в числе больше 5
fun checkDigits(number: Int): Boolean =
    if (number % 10 > 5) {
        if (number / 10 == 0)
            true
        else
            checkDigits(number / 10)
    }
    else false
