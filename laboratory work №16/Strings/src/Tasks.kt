import java.io.File
import java.util.zip.DataFormatException
import kotlin.random.Random

fun main() {
    // print("Input string: ")
    // val s = readLine()!!.toString()

    // task1()

    // task 2: задачи 3, 8, 16
    // task2_launchMenu()

    // task 3
    // val text = File("task3_example.txt").readText()
    // println("Dates (check file 'task3_example.txt'):\n")
    // outputList<String>(findDates(text),"\n")

    // task 4: задачи 3, 8, 16
    // task4_launchMenu()

    // task 5
    // val listOfStrings = File("task5_example.txt").readText().split("\n")
    // println("Source list of strings:\n")
    // outputList<String>(listOfStrings, "\n")
    // println("\nSorted list of strings:\n")
    // outputList<String>(sortStringsByLength(listOfStrings), "\n")

    // task 6
    // val listOfStrings = File("task6_example.txt").readText().split("\n")
    // println("Source list of strings:\n")
    // outputList<String>(listOfStrings, "\n")
    // val sortedListOfStrings = sortStringsByNumberOfWords(listOfStrings)
    // println("\nSorted list of strings by number of words:\n")
    // outputList<String>(sortedListOfStrings, "\n")
}

// не очень уверена, насколько эффективно работает joinToString, поэтому:
fun<T> outputList(list: List<T>, separator: String) {
    tailrec fun<T> outputList(it: Iterator<T>, separator: String) {
        if (it.hasNext()) {
            print("${it.next()}${separator}")
            outputList(it, separator)
        }
    }
    outputList(list.iterator(), separator)
}

// task 1: дана строка в которой числа перечислены
// через пробел, найти максимальное из этих чисел
fun task1() {
    print("Input string: ")
    val s = readLine()

    try {
        val listNumbers = s!!.split(" ").map { it.toInt() }
        println("Max number: ${listNumbers.maxOrNull()}")

    }
    catch(e: NumberFormatException) {
        println("Error: ${e.message}. Try again!\n")
        task1()
    }
}

// task 2.3 (1/3): дана строка в которой слова записаны через пробел,
// перемешать все слова этой строке в случайном порядке
fun task2_3(s: String): String {
    fun replace(list: MutableList<String>, i: Int, j: Int) {
        val temp = list[i]
        list[i] = list[j]
        list[j] = temp
    }

    val listOfWords = s.split(" ").toMutableList()
    listOfWords.mapIndexed { i, _ ->  replace(listOfWords, i, Random.nextInt(0, listOfWords.size)) }

    return listOfWords.joinToString(" ")
}

// task 2.8 (2/3): дана строка в которой записаны слова через пробел,
// посчитать количество слов с четным количеством символов
fun task2_8(s: String): Int {
    val listOfWords = s.split(" ")
    return if (s == "") 0
        else listOfWords.count { it -> it.count() % 2 == 0 }
}

// task 2.16 (3/3): дан массив в котором находятся строки "белый", "синий"
// и "красный" в случайном порядке, упорядочить массив так,
// чтобы получился российский флаг
fun task2_16(arrayOfColors: Array<String>) {
    val indexOfWhite = arrayOfColors.indexOf("белый")
    if (indexOfWhite != 0) {
        val temp = arrayOfColors[0]
        arrayOfColors[0] = arrayOfColors[indexOfWhite]
        arrayOfColors[indexOfWhite] = temp
    }

    val indexOfBlue = arrayOfColors.indexOf("синий")
    if (indexOfBlue != 1) {
        val temp = arrayOfColors[1]
        arrayOfColors[1] = arrayOfColors[indexOfBlue]
        arrayOfColors[indexOfBlue] = temp
    }
}

// task 2: предложить пользователю решить, какую задачу решать
fun task2_launchMenu() {
    println("What task do you want to complete?\n")
    println("0. exit")
    println("1. shuffle words of string")
    println("2. number of words with even number of characters")
    println("3. sort colors as in russian flag\n")
    print(">: ")

    when(readLine()) {
        "0" -> return
        "1" -> {
            print("\nInput string: ")
            val s = readLine()!!.toString()

            println("Shuffled string: ${task2_3(s)}")
        }
        "2" -> {
            print("\nInput string: ")
            val s = readLine()!!.toString()

            println("Number of words with even number of characters: ${task2_8(s)}")
        }
        "3" -> {
            val arrayOfColors = arrayOf("красный", "белый", "синий")
            println("\nShuffled flag colors: ${arrayOfColors.joinToString(" ")}")

            task2_16(arrayOfColors)
            println("Sorted flag colors: ${arrayOfColors.joinToString(" ")}")
        }
        else -> println("Error: there's no such task. Try again!")
    }

    println()
    task2_launchMenu()
}

// task 3: строка, найти все даты, которые описаны в виде "31 февраля 2007"
// *условие, что перед и после даты - пробел*
fun findDates(s: String): List<String> {
    // переделанная функция из task 13.10.19
    fun numberOfDays(month: String, year: Int): Int =
        when {
            (month == "января") -> 31
            (month == "февраля") && (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0)) -> 29
            (month == "февраля") -> 28
            (month == "марта") -> 31
            (month == "апреля") -> 30
            (month == "мая") -> 31
            (month == "июня") -> 30
            (month == "июля") -> 31
            (month == "августа") -> 31
            (month == "сентября") -> 30
            (month == "октября") -> 31
            (month == "ноября") -> 30
            (month == "декабря") -> 31
            else -> throw DataFormatException("Month isn't in range from 1 to 12")
        }

    // чтобы убрать 2 лишнии проверки из regex,
    // которые увеличивают количество проверок в 2 (!) раза
    val text = " $s "

    // диапазон дней: 1 - 31
    val checkingDay = "(([1-9])|([1-2][0-9])|(3[0-1]))"

    val months = listOf(
        "декабря", "января", "февраля",
        "марта", "апреля", "мая",
        "июня", "июля", "августа",
        "сентября", "октября", "ноября"
    )
    val checkingMonth = "(${months.joinToString("|")})"

    // диапазон лет: 1000 - 2021
    val checkingYear = "((1[0-9]{3})|(20[0-1][0-9])|(202[0-1]))"

    // """ - не нужно экранирование
    val regexDates = """\s${checkingDay}\s${checkingMonth}\s${checkingYear}\s""".toRegex()

    // список дат, распарсенных на число, месяц и год
    val listOfDates1 = regexDates.findAll(text).map { it.value.drop(1).dropLast(1).split(" ") }.toList()

    // оставляет только корректные даты
    // например, не может быть 31 сентября (в этом месяце только 30 дней)
    val listOfDates2 = listOfDates1.filter { it[0].toInt() <= numberOfDays(it[1], it[2].toInt())}

    // поскольку дальше ничего с этими датами делать не нужно
    // возвращаем в удобном виде List<String>, а не List<List<String>>
    return listOfDates2.map { it.joinToString(" ") }
}

// task 4.3 (1/3): найти общее количество русских символов
fun findRussianCharacters(s: String): Int {
    val charactersCode = s.toCharArray().map { it.toInt() }
    return charactersCode.count { ((it >= 1040) && (it <= 1103)) || (it == 1025) || (it == 1105) }
}

// task 4.8 (2/3): найти все используемые строчные символы латиницы
fun findLowercaseEnglishCharacters(s: String): Set<Char> {
    val lowercaseCharacters = s.toCharArray().filter { (it.toInt() >= 97) && (it.toInt() <= 122) }
    return lowercaseCharacters.toSet()
}

// task 4.16 (3/3): найти минимальное из имеющихся целых чисел
fun findMinNumber(s: String): Int {
    val regexNumbers = "\\d+".toRegex()
    val listOfNumbers = regexNumbers.findAll(s).map { it.value.toInt() }.toList()

    return listOfNumbers.minOrNull() ?: throw IllegalArgumentException("String doesn't contain numbers")
}

// task 4: предложить пользователю решить, какую задачу решать
fun task4_launchMenu() {
    println("What task do you want to complete?\n")
    println("0. exit")
    println("1. find number of russian characters")
    println("2. find lowercase english characters")
    println("3. find min number of string\n")
    print(">: ")

    val listTaskNumbers = listOf("1", "2", "3")
    val choice = readLine()

    val s = if (listTaskNumbers.contains(choice)) {
        print("\nInput string: ")
        readLine()!!.toString()
    }
    else ""

    when(choice) {
        "0" -> return
        "1" -> println("Number of russian characters: ${findRussianCharacters(s)}")
        "2" -> println("Lowercase english characters: ${findLowercaseEnglishCharacters(s)}")
        "3" -> {
            try {
                println("Min number of string: ${findMinNumber(s)}")
            }
            catch(e: IllegalArgumentException) {
                println("Error: ${e.message}. Try again!")
            }
        }
        else -> println("Error: there's no such task. Try again!")
    }

    println()
    task4_launchMenu()
}

// task 5: прочитать список строк из файла, упорядочить по длине строки
fun sortStringsByLength(listOfStrings: List<String>): List<String> {
    return listOfStrings.sortedBy { it -> it.length }
}

// task 6: дан список строк из файла, упорядочить по количеству слов в строке
// *упрощение: за слово принимаем любой набор, разделённый слева и справа пробелом*
fun sortStringsByNumberOfWords(listOfStrings: List<String>): List<String> {
    // заменяет n-ое количество пробелов на один
    val regexSpaces = "\\s{2,}".toRegex()

    // dropWhile и dropLastWhile для того, чтобы убрать
    // 1 (!) пробел в начале или конце, если они есть
    // (c регуляркой не очень красиво получается)
    val listOfStrings2 = listOfStrings.map { it ->
        regexSpaces.replace(it, " ").dropWhile { it == ' ' }.dropLastWhile { it == ' ' } }

    // println("\nList without extra spaces:\n")
    // outputList<String>(listOfStrings2, "\n")

    val result = listOfStrings.withIndex().sortedBy { it -> listOfStrings2[it.index].split(" ").size }
    return result.map { it -> it.value }
}