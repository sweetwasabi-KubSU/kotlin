import kotlin.math.abs

fun main() {
    val number = inputNumber()

    // task 1
    // println("Sum of digits (recursion up): ${sumDigitsUp(number)}")

    // task 2
    println("Sum of digits (tail recursion): ${sumDigitsTail(number)}")
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