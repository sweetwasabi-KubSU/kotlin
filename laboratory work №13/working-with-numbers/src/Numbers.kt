fun main() {
    try {
        val number = inputNumber()

        if (number != null)
            println("Sum of digits of number: ${sumDigits(number)}")
        else
            main()
    }
    catch(e: Exception)
    {
        main()
    }
}

// ввод числа: может вернуть null, Int
// или выбросить исключение (при приведение типов)
fun inputNumber(): Int? {
    print("Enter the number:> ")
    val number = readLine()

    if (number == null)
        return number

    return number.toInt()
}

// сумма цифр числа
fun sumDigits(number: Int): Int =
    if (number / 10 == 0)
        number % 10
    else
        sumDigits(number / 10) + (number % 10)