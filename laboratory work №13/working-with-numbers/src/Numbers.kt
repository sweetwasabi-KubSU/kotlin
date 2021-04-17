import kotlin.math.abs

fun main() {
    val number = inputNumber()

    // task 6
    //println("Sum of digits: ${sumDigits(number)}")

    // task 7.1
    // println("Max digit: ${maxDigit(number)}")

    // task 7.2
    // println("Min digit: ${minDigit(number)}")

    // task 7.3
    println("Mult of digits: ${multDigits(number)}")
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

// *** task 6 ***
// сумма цифр числа
fun sumDigits(number: Int): Int =
    if (number / 10 == 0)
        abs(number % 10)
    else
        sumDigits(number / 10) + abs(number % 10)

// *** task 7.1 ***
// максимальная цифра числа
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

// *** task 7.2 ***
// минимальная цифра числа
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

// *** task 7.2 ***
// проиведение цифр числа
fun multDigits(number: Int): Int =
    if (number / 10 == 0)
        abs(number % 10)
    else
        multDigits(number / 10) * abs(number % 10)