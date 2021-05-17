fun main() {
    //val arrayNumbers: Array<Int> = inputArray()
    val listNumbers: List<Int> = inputList()
    println()

    // task 1, 2
    println("Min list element: ${min(listNumbers)}")
    println("Max list element: ${max(listNumbers)}")
    println("Sum of list elements: ${sum(listNumbers)}")
    println("Mult of list elements: ${mult(listNumbers)}")
}

// ввод списка с клавиатуры
fun inputList(): List<Int> {
    print("Input array (in one line, separated by space)\n>: ")

    return try {
        readLine()!!.split(" ").map { it.toInt() }
    }
    catch(e: NumberFormatException) {
        println()
        inputList()
    }
}

// ввод массива с клавиатуры
fun inputArray(): Array<Int> {
    print("Input array (in one line, separated by space)\n>: ")

    return try {
        readLine()!!.split(" ").map { it.toInt() }.toTypedArray()
    }
    catch(e: NumberFormatException) {
        println()
        inputArray()
    }
}
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