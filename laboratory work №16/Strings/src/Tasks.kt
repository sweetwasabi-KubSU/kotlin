import kotlin.random.Random

fun main() {
    print("Input string: ")
    val s = readLine()!!.toString()

    // task1()

    // task 2: задачи 3, 8, 16
    // println("Shuffled string: ${task2_3(s)}")
    println("Number of words with even number of characters: ${task2_8(s)}")

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