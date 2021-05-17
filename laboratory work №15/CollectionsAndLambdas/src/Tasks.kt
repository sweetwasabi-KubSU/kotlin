import java.io.File

fun main() {
//    //val arrayNumbers: Array<Int> = inputArrayByConsole()
//    val listNumbers: List<Int> = inputListByConsole()
//    println()
//
//    // task 1, 2
//    println("Min list element: ${min(listNumbers)}")
//    println("Max list element: ${max(listNumbers)}")
//    println("Sum of list elements: ${sum(listNumbers)}")
//    println("Mult of list elements: ${mult(listNumbers)}")

    val arrayNumbers = inputArray()
    print("\nInput result: ")
    outputArray<Int>(arrayNumbers)
}

// вывод массива
fun<T> outputArray(array: Array<T>) {
    print("${array.joinToString(" ")}")
}

// ввод списка с клавиатуры
fun inputListByConsole(): List<Int> {
    print("Input list (in one line, separated by space)\n>: ")

    return try {
        readLine()!!.split(" ").map { it.toInt() }
    }
    catch(e: NumberFormatException) {
        println()
        inputListByConsole()
    }
}

// ввод массива с клавиатуры
fun inputArrayByConsole(): Array<Int> {
    print("Input array (in one line, separated by space)\n>: ")

    return try {
        readLine()!!.split(" ").map { it.toInt() }.toTypedArray()
    }
    catch(e: NumberFormatException) {
        println()
        inputArrayByConsole()
    }
}

// конечно, можно было бы сделать круто:
// убрать лишние и повторяющиеся пробелы
// (хоп-хей, замена с регулярочками, уу!),
// допустить как разделитель - новую строку
// (ну, это клиника - не знаю, что пошло не так),
// но я усталь, и у меня что-то ломается,
// поэтому выбираю быть тупой
// *грустные мысли дианы в час ночи*

// старательно обрабатываю все возможные исключения...
// я как герой стихотворения Маяковского "Послушайте!",
// в роли звёзд - исключения
// "ведь теперь тебе ничего не страшно?"

// на самом деле я молодец, наверное

// ввод массива из файла (разделитель: пробел)
fun inputArrayByFile(path: String = "ExampleOfArray.txt"): Array<Int> =
    try {
        File(path).readText().split(" ").map { it.toInt() }.toTypedArray()
    }
    catch(e: NullPointerException) {
        throw NullPointerException("This file doesn't exist")
    }
    catch(e: java.io.FileNotFoundException) {
        throw NullPointerException("This file doesn't exist")
    }
    catch(e: NumberFormatException) {
        throw NumberFormatException("Can't be cast to Int")
    }

// ввод массива из файла (разделитель: пробел)
// специально для задания 3: нужно, чтобы функция была без параметров
fun inputArrayByFileV2(): Array<Int> =
    try {
        File("ExampleOfArray.txt").readText().split(" ").map { it.toInt() }.toTypedArray()
    }
    catch(e: NullPointerException) {
        throw NullPointerException("This file doesn't exist")
    }
    catch(e: java.io.FileNotFoundException) {
        throw NullPointerException("This file doesn't exist")
    }
    catch(e: NumberFormatException) {
        throw NumberFormatException("Can't be cast to Int")
    }

// task 3: спросить пользователя, откуда читать данные,
// в зависимости от ответа читать или с клавиатуры, или из файла,
// для выполнения данной задачи построить функцию,
// возвращающую функцию, возвращающую массив
fun selectInputMethod(): () -> Array<Int> {
    println("How do you want to input array?")
    println("1. Console")
    println("2. File\n")
    print(">: ")

    return when(readLine()) {
        "1" -> {
            println()
            ::inputArrayByConsole
        }
        "2" -> ::inputArrayByFileV2
        else -> {
            println("Invalid method. Try again!\n")
            selectInputMethod()
        }
    }
}

// task 3: и для реализации чтения результат этой функции
fun inputArray(): Array<Int> {
    return try {
        selectInputMethod()()
    }
    catch (e: NumberFormatException) {
        println("\nError: ${e.message}! Check the file.\n")
        inputArray()
    }
    catch(e: NullPointerException) {
        println("\nError: ${e.message}! I'm sorry, select console :(\n")
        inputArray()
    }
    catch(e: java.io.FileNotFoundException) {
        println("\nError: ${e.message}! I'm sorry, select console :(\n")
        inputArray()
    }
}

// RIP ленивым вычислениям

// task 1, 2: написать одну функцию arrayOp() с применением
// хвостовой рекурсии, перебирающую элементы массива, принимающую
// как аргументы массив, лямбда выражение и инициализирующее значение
// *переименовала arrayOp в listOp для логичности*
fun listOp(arrayNumbers: List<Int>, calculate: (Int, Int) -> Int, init: Int = 0): Int =
    if (arrayNumbers.isNotEmpty()) {
        listOp(arrayNumbers.drop(1), calculate, calculate(arrayNumbers.first(), init))
    }
    else init

// task 1, 2: написать 4 функции для суммы, произведения,
// мин и макс, использующих функцию arrayOp()

fun min(arrayNumbers: List<Int>): Int =
    listOp(arrayNumbers.drop(1), {a: Int, b: Int -> if (a < b) a else b}, arrayNumbers.first())

fun max(arrayNumbers: List<Int>): Int =
    listOp(arrayNumbers.drop(1), {a: Int, b: Int -> if (a > b) a else b}, arrayNumbers.first())

fun sum(arrayNumbers: List<Int>): Int =
    listOp(arrayNumbers, {a: Int, b: Int -> a + b})

fun mult(arrayNumbers: List<Int>): Int =
    listOp(arrayNumbers, {a: Int, b: Int -> a * b}, 1)