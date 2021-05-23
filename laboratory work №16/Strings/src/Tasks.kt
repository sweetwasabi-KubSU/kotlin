import kotlin.random.Random

fun main() {
    // print("Input string: ")
    // val s = readLine()!!.toString()

    // task1()

    // task 2: задачи 3, 8, 16
    task2_launchMenu()
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