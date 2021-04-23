import java.util.zip.DataFormatException
import kotlin.math.abs
import java.io.File

fun main() {
    // task 9
    // launchMenu()

    // task 10.19
    // println("Number of Sundays: ${problem19()}")

    // task 10.39
    // println("P = ${problem39()}")

    // task 10.59
    problem59()
}

// task 9: Реализовать возможность пользователю выбирать,
// какие из методов для введенного числа он хочет исполнить,
// и продолжать работу программы, пока пользователь не укажет обратное,
// то есть пользователь может вводить числа и методы для них,
// пока не введет соответствующую команду
fun launchMenu() {
    println("***METHODS***\n")

    println("0: exit")
    println("1: sumDigits")
    println("2: maxDigit")
    println("3: minDigit")
    println("4: multDigits")
    println("5: maxPrimeDivisor")
    println("6: multDigitsNotDivBy")
    println("7: maxOddNotPrimeDiv\n")

    print("Enter the method number:> ")
    val methodNumber = readLine()

    val validSet = setOf("1","2","3","4","5","6","7")
    val number: Int

    if (validSet.contains(methodNumber)) {
        println()
        number = inputNumber()
    }
    else number = -1

    try {
        when (methodNumber) {
            "0" -> return
            "1" -> println("Sum of digits: ${sumDigits(number)}")
            "2" -> println("Max digit: ${maxDigit(number)}")
            "3" -> println("Min digit: ${minDigit(number)}")
            "4" -> println("Mult of digits: ${multDigits(number)}")
            "5" -> println("Max prime divisor of number: ${maxPrimeDivisor(number)}")
            "6" -> println("Mult of digits not divisible by 5: ${multDigitsNotDivBy(number,5)}")
            "7" -> {
                println("\nMax odd not prime divisor of number: ${maxOddNotPrimeDiv(number)}")
                println("Mult of digits: ${multDigits(number)}")
                println("\nTheir GCD: ${task8(number)}")
            }
            else -> println("Error: invalid method number!")
        }
    }
    catch(e: java.lang.ArithmeticException)
    {
        println("Error: ${e.message}!")
    }

    println()
    launchMenu()
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

// task 10.19: количество месяцев, начавшихся с воскресенья
// диапазон [1 января 1901; 31 декабря 2000]
fun problem19(): Int {
    fun numberOfDays(month: Int, year: Int): Int =
        when {
            (month == 1) -> 31
            (month == 2) && (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0)) -> 29
            (month == 2) -> 28
            (month == 3) -> 31
            (month == 4) -> 30
            (month == 5) -> 31
            (month == 6) -> 30
            (month == 7) -> 31
            (month == 8) -> 31
            (month == 9) -> 30
            (month == 10) -> 31
            (month == 11) -> 30
            (month == 12) -> 31
            else -> throw DataFormatException("month isn't in range from 1 to 12")
        }

    fun problem19(dayOfWeek: Int, month: Int, year: Int): Int {
        return if (!(month == 1 && year == 2001)) {
            val newDayOfWeek = (numberOfDays(month, year) % 7 + dayOfWeek) % 7
            val counter =
                if (newDayOfWeek == 0) 1
                else 0

            if (month == 12)
                problem19(newDayOfWeek,1,year + 1) + counter
            else
                problem19(newDayOfWeek,month + 1, year) + counter
        }
        else 0
    }

    return problem19(2,1,1901)
}

// task 10.39: Если p - периметр прямоугольного треугольника, {a, b, c},
// какое значение при p ≤ 1000 имеет наибольшее количество решений?
// *размера стека по умолчанию не хватает: Run/Debug Configuration VM Options = -Xss64M*
// *осторожно: может быть, перегруз по памяти...*
// *тот самый случай, когда циклы были бы лучше рекурсии - !!!*
fun problem39(): Int {
    fun problem39(p: Int, a: Int, result: Int, solutions: Int, i: Int): Int {
        return if (a < p / 3) {
            if (p * (p - 2 * a) % (2 * (p - a)) == 0)
                problem39(p, a + 1, result, solutions, i + 1)
            else
                problem39(p, a + 1, result, solutions, i)
        }
        else if (p < 1000) {
            if (i > solutions)
                problem39(p + 2, 2, p, i, 0)
            else
                problem39(p + 2, 2, result, solutions, 0)
        }
        else result
    }

    return problem39(2,2,0,0,0)
}

// task 10.59: расшифровать сообщение
// и вывести сумму ASCII кодов сообщения
fun problem59() {
    fun readFile(path: String, separator: Char): List<Int> {
        val listOfCodes: MutableList<Int> = mutableListOf()

        val inputFile = File(path)
        for (line in inputFile.readLines())
            for (code in line.split(separator))
                listOfCodes.add(code.toInt())

        return listOfCodes
    }

    fun decryptMessage(message: List<Int>, key: List<Int>): List<Int> {
        val encryptedMessage: MutableList<Int> = mutableListOf()
        for (i in message.indices)
            encryptedMessage.add((message[i] xor key[i % key.size]))

        return encryptedMessage
    }

    // частотный анализ
    fun analysis(message: List<Int>, keyLength: Int): List<Int> {
        var maxSize = 0
        for (i in message.indices) {
            if (message[i] > maxSize)
                maxSize = message[i];
        }

        val aMessage = MutableList(keyLength) { MutableList(maxSize + 1) {0} }
        val key = MutableList(keyLength) {0}

        for (i in message.indices) {
            val j = i % keyLength;
            aMessage[j][message[i]]++;
            if (aMessage[j][message[i]] > aMessage[j][key[j]])
                key[j] = message[i];
        }

        val spaceAscii = 32
        for (i in 0 until keyLength) {
            key[i] = key[i] xor spaceAscii
        }

        return key
    }

    val message = readFile("p059_cipher.txt", ',')

    var key = analysis(message, 3)
    val decryptedMessage = decryptMessage(message, key)

    print("Decrypted message: ")
    for (code in decryptedMessage)
        print(code.toChar())

    print("\nSum of ASCII values: ${decryptedMessage.sum()}")
}