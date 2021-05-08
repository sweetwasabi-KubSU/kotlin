import kotlin.math.abs

fun main() {
    val number = inputNumber()

//    // task 1
//    println("Sum of digits (recursion up): ${sumDigitsUp(number)}")
//
//    // task 2
//    println("Sum of digits (tail recursion): ${sumDigitsTail(number)}")
//
//    // task 3.1
//    println("Mult of digits (recursion up): ${multDigitsUp(number)}")
//
//    // task 3.1
//    println("Mult of digits (tail recursion): ${multDigitsTail(number)}")
//
//    // task 3.2
//    println("Min digit (recursion up): ${minDigitUp(number)}")
//
//    // task 3.2
//    println("Min digit (tail recursion): ${minDigitTail(number)}")
//
//    // task 3.3
//    println("Max digit (recursion up): ${maxDigitUp(number)}")
//
//    // task 3.3
//    println("Max digit (tail recursion): ${maxDigitTail(number)}")
//
//    // task 4
//    println("\nSum of digits: ${calculate(number, ::sumDigitsUp)}")
//    println("Mult of digits: ${calculate(number, ::multDigitsUp)}")
//    println("Min digit: ${calculate(number, ::minDigitUp)}")
//    println("Max digit: ${calculate(number, ::maxDigitUp)}")
//
//    // task 6: проверить функцию task 5
//
//    // 1-ый и 2-ой пример: считает сумму цифр, если каждая из них больше 5
//    calculateWithCondition(number, ::sumDigitsTail, ::checkDigits)
//    calculateWithCondition(number, ::sumDigitsTail, ::checkDigits, 0)
//
//    // 3-ий пример: считает произведение цифр, если каждая из них больше 5
//    calculateWithCondition(number, ::multDigitsTail, ::checkDigits, 1)
//
//    // task 7: переписать task 13.8 с использованием
//    // только хвоствой рекурсии и тела-выражения
//    try {
//        // task 7.1
//        // println("Max prime divisor of number: ${maxPrimeDivisor(number)}")
//
//        // task 7.2
//        // println("Mult of digits not divisible by 5: ${multDigitsNotDivBy(number,5)}")
//
//        // task 7.3
//        // println("\nMax odd not prime divisor of number: ${maxOddNotPrimeDiv(number)}")
//        // println("Mult of digits: ${multDigitsUp(number)}")
//        // println("\nTheir GCD: ${task7_3(number)}")
//    }
//    catch(e: ArithmeticException)
//    {
//        println("\nError: ${e.message}!")
//    }
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

// task 7.1: максимальный простой делитель числа
// *допущение, что простой делитель 1 - 1*
fun maxPrimeDivisor(number: Int): Int =
    try {
        if ((number == 1) || isNumberPrime(number))
            number
        else
            maxPrimeDivisor(number, number / 2)
    }
    catch(e: ArithmeticException)
    {
        throw e
    }

fun maxPrimeDivisor(number: Int, divisor: Int): Int =
    try {
        if (isNumberPrime(divisor) && (number % divisor == 0))
            divisor
        else
            maxPrimeDivisor(number,divisor - 1)
    }
    catch(e: ArithmeticException)
    {
        throw e
    }

// проверка, простое ли число
fun isNumberPrime(number: Int): Boolean =
    when (number) {
        0 -> throw ArithmeticException("0 has no prime divisors")
        1 -> false
        2 -> true
        else -> isNumberPrime(number, number / 2)
    }

fun isNumberPrime(number: Int, divisor: Int): Boolean =
    try {
        when {
            (divisor == 1) -> true
            (number % divisor == 0) -> false
            else -> isNumberPrime(number, divisor - 1)
        }
    }
    catch(e: ArithmeticException)
    {
        throw e
    }

// task 7.2: произведение цифр числа, не делящихся на 5
// *если число полностью состоит из 5, то результат - 1*
fun multDigitsNotDivBy(number: Int, notDivisor: Int): Int =
    when {
        (number / 10 == 0) && (number % 10 != notDivisor) -> abs(number % 10)
        (number / 10 == 0) -> 1
        (number % 10 != notDivisor) -> multDigitsNotDivBy(number / 10, notDivisor) * abs(number % 10)
        else -> multDigitsNotDivBy(number / 10, notDivisor)
    }

// task 7.3: НОД максимального нечетного непростого
// делителя числа и прозведения цифр данного числа
// *если число или произведение цифр числа - 0, то НОД - 1*
// *пример: 45*
fun task7_3 (number: Int): Int =
    try {
        numbersGCD(maxOddNotPrimeDiv(number), multDigitsUp(number))
    }
    catch(e: ArithmeticException) {
        throw e
    }

// максимальный нечётный непростой делитель
fun maxOddNotPrimeDiv(number: Int): Int =
    try {
        if ((number % 2 != 0) && !isNumberPrime(number))
            number
        else
            maxOddNotPrimeDiv(number, number / 2)
    }
    catch(e: ArithmeticException) {
        throw e
    }

fun maxOddNotPrimeDiv(number: Int, divisor: Int): Int =
    try {
        if (!isNumberPrime(divisor) && (number % divisor == 0) && (divisor % 2 != 0))
            divisor
        else
            maxOddNotPrimeDiv(number, divisor - 1)
    }
    catch(e: ArithmeticException) {
        throw e
    }

// наиобольший общий делитель
fun numbersGCD(a: Int, b: Int): Int =
    when {
        ((a <= 0) || (b <= 0)) -> throw ArithmeticException("One of the numbers <= 0")
        (a == b) -> a
        (a > b) -> numbersGCD(a - b, b)
        else -> numbersGCD(a, b - a)
    }
