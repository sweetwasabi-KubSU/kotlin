import kotlin.math.abs

fun main() {
    val number = inputNumber()

//    // task 6
//    println("Sum of digits: ${sumDigits(number)}")
//
//    // task 7.1
//    println("Max digit: ${maxDigit(number)}")
//
//    // task 7.2
//    println("Min digit: ${minDigit(number)}")
//
//    // task 7.3
//    println("Mult of digits: ${multDigits(number)}")
//
//    // task 8.1
//    try {
//        println("Max prime divisor of number: ${maxPrimeDivisor(number)}")
//    }
//    catch(e: java.lang.ArithmeticException)
//    {
//        println("Error: ${e.message}!")
//    }
//
//    // task 8.2
//    println("Mult of digits not divisible by 5: ${multDigitsNotDivBy(number,5)}")
//
//    // task 8.3
//    try {
//        println("\nMax odd not prime divisor of number: ${maxOddNotPrimeDiv(number)}")
//        println("Mult of digits: ${multDigits(number)}")
//        println("\nTheir GCD: ${task8(number)}")
//    }
//    catch(e: ArithmeticException) {
//        println("Error: ${e.message}!")
//    }
}

// ввод числа (рекурсия, пока число некорректно)
fun inputNumber(): Int {
    print("Enter the number:> ")
    val number = readLine()

    return if (number == null)
        inputNumber()
    else
        try {
            number.toInt()
        }
        catch (e: NumberFormatException) {
            inputNumber()
        }
}

// task 6: сумма цифр числа
fun sumDigits(number: Int): Int =
    if (number / 10 == 0)
        abs(number % 10)
    else
        sumDigits(number / 10) + abs(number % 10)

// task 7.1: максимальная цифра числа
fun maxDigit(number: Int): Int {
    fun maxDigit(number: Int, max: Int): Int {
        val newNumber = number / 10
        val digit = abs(number % 10)

        val newMax =
            if (digit > max)
                digit
            else
                max

        return if (newNumber != 0)
            maxDigit(newNumber, newMax)
        else
            newMax
    }
    return maxDigit(number, -1)
}

// task 7.2: минимальная цифра числа
fun minDigit(number: Int): Int {
    fun minDigit(number: Int, min: Int): Int {
        val newNumber = number / 10
        val digit = abs(number % 10)

        val newMin =
            if (digit < min)
                digit
            else
                min

        return if (newNumber != 0)
            minDigit(newNumber, newMin)
        else
            newMin
    }
    return minDigit(number, 10)
}

// task 7.2: проиведение цифр числа
fun multDigits(number: Int): Int =
    if (number / 10 == 0)
        abs(number % 10)
    else
        multDigits(number / 10) * abs(number % 10)

// task 8.1: максимальный простой делитель числа
// *допущение, что простой делитель 1 - 1*
fun maxPrimeDivisor(number: Int): Int {
    fun maxPrimeDivisor(number: Int, divisor: Int): Int =
        if (isNumberPrime(divisor) && (number % divisor == 0))
            divisor
        else
            maxPrimeDivisor(number,divisor - 1)

    try {
        return if ((number == 1) || isNumberPrime(number))
            number
        else
            maxPrimeDivisor(number, number / 2)
    }
    catch(e: ArithmeticException)
    {
        throw e
    }
}

// проверка, простое ли число
fun isNumberPrime(number: Int): Boolean {
    fun isNumberPrime(number: Int, divisor: Int): Boolean =
        when {
            (divisor == 1) -> true
            (number % divisor == 0) -> false
            else -> isNumberPrime(number, divisor - 1)
        }

    return when (number) {
        0 -> throw ArithmeticException("0 has no prime divisors")
        1 -> false
        2 -> true
        else -> isNumberPrime(number, number / 2)
    }
}

// task 8.2: произведение цифр числа, не делящихся на 5
// *если число полностью состоит из 5, то результат - 1*
fun multDigitsNotDivBy(number: Int, notDivisor: Int): Int =
    when {
        (number / 10 == 0) && (number % 10 != notDivisor) -> abs(number % 10)
        (number / 10 == 0) -> 1
        (number % 10 != notDivisor) -> multDigitsNotDivBy(number / 10, notDivisor) * abs(number % 10)
        else -> multDigitsNotDivBy(number / 10, notDivisor)
    }

// task 8.3: НОД максимального нечетного непростого
// делителя числа и прозведения цифр данного числа
// *если само число - 0, то НОД - 1*
// *если произведение цифр числа - 0, то НОД - 1*
// *пример: 45*
fun task8 (number: Int): Int {
    val maxPrimeDiv: Int
    try {
        maxPrimeDiv = maxOddNotPrimeDiv(number)
    }
    catch(e: ArithmeticException)
    {
        throw e
    }

    val multDigits = multDigits(number)

    if (multDigits == 0)
        throw ArithmeticException("Unable to find a GCD with the argument 0")

    return numbersGCD(maxPrimeDiv, multDigits)
}

// максимальный нечётный непростой делитель
fun maxOddNotPrimeDiv(number: Int): Int {
    fun maxOddNotPrimeDiv(number: Int, divisor: Int): Int =
        if (!isNumberPrime(divisor) && (number % divisor == 0) && (divisor % 2 != 0))
            divisor
        else
            maxOddNotPrimeDiv(number, divisor - 1)

    return if ((number % 2 != 0) && !isNumberPrime(number))
        number
    else
        maxOddNotPrimeDiv(number, number / 2)
}

// наиобольший общий делитель
fun numbersGCD(a: Int, b: Int): Int =
    when {
        (a == b) -> a
        (a > b) -> numbersGCD(a - b, b)
        else -> numbersGCD(a, b - a)
    }