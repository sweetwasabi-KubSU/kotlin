import java.io.File

fun main() {
//    // val array: Array<Int> = inputArrayByConsole()
//    val list = inputListByConsole()
//    println()
//
//    // task 1, 2
//    println("Min array element: ${min(array)}")
//    println("Max array element: ${max(array)}")
//    println("Sum of array elements: ${sum(array)}")
//    println("Mult of array elements: ${mult(array)}")
//
//    // task 3
//    val array = inputArray()
//    print("\nInput result: ")
//    outputArray<Int>(array)
//
//    // task 4: задачи 9, 10, 21, 23, 33, 36, 39, 45, 57
//
//    // task 4.9 (1/9)
//    println("Elements before the last min:")
//    outputArray<Int>(task4_9(array))
//
//    // task 4.10 (2/9)
//    val array1: Array<Int> = inputArrayByConsole()
//    val array2: Array<Int> = inputArrayByConsole()
//    println("\nNumber of matching pairs: ${task4_10(array1, array2)}")
//
//    // task 4.21 (3/9)
//    println("Elements after the first max:")
//    outputArray<Int>(task4_21(array))
//
//    // task 4.23 (4/9)
//    try {
//        val mins = task4_23(array)
//        println("Two smallest elements of array: ${mins.first}, ${mins.second}")
//    }
//    catch(e: IllegalArgumentException) {
//        println("Error: ${e.message}. Try again!")
//    }
//
//    // task 4.33 (5/9)
//    if (task4_33(array))
//        println("All elements of array are interleaved (+/-).")
//    else
//        println("Elements of array aren't interleaved (+/-).")
//
//    // task 4.36 (6/9)
//    try {
//        println("Max odd element of array: ${task4_36(array)}")
//    }
//    catch (e: IllegalArgumentException) {
//        println("Error: ${e.message}. Try again!")
//    }
//
//    // task 4.39 (7/9)
//    val resultArrays = task4_39(array)
//    print("Elements with even index: ")
//    outputArray<Int>(resultArrays.first)
//    print("\nElements with odd index: ")
//    outputArray<Int>(resultArrays.second)
//    println()
//
//    // task 4.45 (8/9)
//    try {
//        println("*Numbering starts from 0*")
//        print("Input a (start of interval): ")
//        val a = readLine()!!.toInt()
//        print("Input b (end of interval): ")
//        val b = readLine()!!.toInt()
//        println("\nSum of interval: ${task4_45(array, a, b)}")
//    }
//    catch(e: IllegalArgumentException) {
//        println("\nError: ${e.message}. Try again!")
//    }
//
//    // task 4.57 (9/9)
//    println("Number of matching elements: ${task4_57(array)}")
//
//    // *** СПИСКИ ***
//
//    // task 5: переписать task 1 через список
//    println("Min list element: ${min(list)}")
//    println("Max list element: ${max(list)}")
//    println("Sum of list elements: ${sum(list)}")
//    println("Mult of list elements: ${mult(list)}")
//
//    // task 6: использовать в task 5 встроенные методы
//    println("Min list element: ${list.minOrNull()}")
//    println("Max list element: ${list.maxOrNull()}")
//    println("Sum of list elements: ${list.sum()}")
//    println("Mult of list elements: ${list.reduce { acc, i -> acc * i }}")
//
//    // task 7: переписать task 3 через список
//    val list = inputList()
//    print("\nInput result: ")
//    outputList<Int>(list)
//
//    // task 8: переписать task 4 через список
}

// вывод массива
fun<T> outputArray(array: Array<T>) {
    print("${array.joinToString(" ")}")
}

// вывод списка
fun<T> outputList(list: List<T>) {
    print("${list.joinToString(" ")}")
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

// ввод массива из файла (разделитель: пробел)
fun inputArrayByFile(path: String = "ExampleOfArray.txt"): Array<Int> =
    try {
        File(path).readText().split(" ").map { it.toInt() }.toTypedArray()
    }
    catch(e: NullPointerException) { throw e }
    catch(e: java.io.FileNotFoundException) { throw e }
    catch(e: NumberFormatException) { throw e }

// ввод массива из файла (разделитель: пробел)
// специально для задания 3: нужно, чтобы функция была без параметров
fun inputArrayByFileV2(): Array<Int> =
    try {
        File("ExampleOfArray.txt").readText().split(" ").map { it.toInt() }.toTypedArray()
    }
    catch(e: NullPointerException) { throw e }
    catch(e: java.io.FileNotFoundException) { throw e }
    catch(e: NumberFormatException) { throw e }

// ввод списка из файла (разделитель: пробел)
fun inputListByFile(path: String = "ExampleOfList.txt"): List<Int> =
    try {
        File(path).readText().split(" ").map { it.toInt() }
    }
    catch(e: NullPointerException) { throw e }
    catch(e: java.io.FileNotFoundException) { throw e }
    catch(e: NumberFormatException) { throw e }

// ввод списка из файла (разделитель: пробел)
// специально для задания 3: нужно, чтобы функция была без параметров
fun inputListByFileV2(): List<Int> =
    try {
        File("ExampleOfList.txt").readText().split(" ").map { it.toInt() }
    }
    catch(e: NullPointerException) { throw e }
    catch(e: java.io.FileNotFoundException) { throw e }
    catch(e: NumberFormatException) { throw e }

// task 3: спросить пользователя, откуда читать данные,
// в зависимости от ответа читать или с клавиатуры, или из файла,
// для выполнения данной задачи построить функцию,
// возвращающую функцию, возвращающую массив
fun selectArrayInputMethod(): () -> Array<Int> {
    println("How do you want to input array?")
    println("1. Console")
    println("2. Standard file\n")
    print(">: ")

    return when(readLine()) {
        "1" -> {
            println()
            ::inputArrayByConsole
        }
        "2" -> ::inputArrayByFileV2
        else -> {
            println("Invalid method. Try again!\n")
            selectArrayInputMethod()
        }
    }
}

// task 3: и для реализации чтения результат этой функции
fun inputArray(): Array<Int> {
    return try {
        selectArrayInputMethod()()
    }
    catch(e: Exception) {
        when(e) {
            is NullPointerException, is java.io.FileNotFoundException -> {
                println("\nError: ${e.message}! I'm sorry, select console :(\n")
                inputArray()
            }
            is NumberFormatException -> {
                println("\nError: ${e.message}! Check the file.\n")
                inputArray()
            }
            else -> throw e
        }
    }
}

// task 7: переписать task 3 через список
fun selectListInputMethod(): () -> List<Int> {
    println("How do you want to input list?")
    println("1. Console")
    println("2. Standard file\n")
    print(">: ")

    return when(readLine()) {
        "1" -> {
            println()
            ::inputListByConsole
        }
        "2" -> ::inputListByFileV2
        else -> {
            println("Invalid method. Try again!\n")
            selectListInputMethod()
        }
    }
}

// task 7: и для реализации чтения результат этой функции
fun inputList(): List<Int> {
    return try {
        selectListInputMethod()()
    }
    catch(e: Exception) {
        when(e) {
            is NullPointerException, is java.io.FileNotFoundException -> {
                println("\nError: ${e.message}! I'm sorry, select console :(\n")
                inputList()
            }
            is NumberFormatException -> {
                println("\nError: ${e.message}! Check the file.\n")
                inputList()
            }
            else -> throw e
        }
    }
}

// task 1, 2: написать одну функцию arrayOp() с применением
// хвостовой рекурсии, перебирающую элементы массива, принимающую
// как аргументы массив, лямбда выражение и инициализирующее значение
// *переименовала arrayOp в listOp для логичности*
tailrec fun arrayOp(array: Iterator<Int>, f : (Int, Int) -> Int, accum: Int = 0): Int =
    if (array.hasNext())
        arrayOp(array, f, f(array.next(), accum))
    else accum

// task 1, 2: написать 4 функции для суммы, произведения,
// мин и макс, использующих функцию arrayOp()

fun min(array: Array<Int>): Int =
    arrayOp(array.iterator(), { a: Int, b: Int -> if (a < b) a else b}, array.first())

fun max(array: Array<Int>): Int =
    arrayOp(array.iterator(), { a: Int, b: Int -> if (a > b) a else b}, array.first())

fun sum(array: Array<Int>): Int =
    arrayOp(array.iterator(), { a: Int, b: Int -> a + b})

fun mult(array: Array<Int>): Int =
    arrayOp(array.iterator(), { a: Int, b: Int -> a * b}, 1)


// task 5: переписать тоже самое для списка

fun min(array: List<Int>): Int =
    arrayOp(array.iterator(), { a: Int, b: Int -> if (a < b) a else b}, array.first())

fun max(array: List<Int>): Int =
    arrayOp(array.iterator(), { a: Int, b: Int -> if (a > b) a else b}, array.first())

fun sum(array: List<Int>): Int =
    arrayOp(array.iterator(), { a: Int, b: Int -> a + b})

fun mult(array: List<Int>): Int =
    arrayOp(array.iterator(), { a: Int, b: Int -> a * b}, 1)

// task 4.9 (1/9): найти элементы, расположенные перед последним минимальным
fun task4_9(array: Array<Int>): Array<Int> {
    val min = min(array)
    val lastIndexMin = array.indexOfLast { it == min }

    return array.filterIndexed { index, _ -> index < lastIndexMin }.toTypedArray()
}

// task 4.10 (2/9): два массива, найти количество совпадающих по значению элементов
fun task4_10(array1: Array<Int>, array2: Array<Int>): Int {
    fun contains(value1: Int, value2: Int, index2: Int, indexAvailability2: Array<Boolean>): Boolean {
        return if (value1 == value2 && indexAvailability2[index2]) {
            indexAvailability2[index2] = false
            true
        }
        else false
    }

    val indexAvailability2 = Array(array2.size) { true }
    val arrayIndexed2 = array2.withIndex()

    return array1.count { value1 -> arrayIndexed2.any { (index2, value2) -> contains(value1, value2, index2, indexAvailability2) } }
}

// task 4.21 (3/9): найти элементы, расположенные после первого максимального
fun task4_21(array: Array<Int>): Array<Int> {
    val max = max(array)
    val firstIndexMax = array.indexOfFirst { it == max }

    return array.filterIndexed { index, _ -> index > firstIndexMax }.toTypedArray()
}

// task 4.23 (4/9): найти два наименьших элемента
fun task4_23(array: Array<Int>): Pair<Int, Int> {
    tailrec fun task4_23(it: Iterator<Int>, f: (Int, Int, Int) -> Pair<Int, Int>, mins: Pair<Int, Int>): Pair<Int, Int> =
        if (it.hasNext())
            task4_23(it, f, f(it.next(), mins.first, mins.second))
        else mins

    if (array.size < 2)
        throw IllegalArgumentException("Incorrect array size")

    val f = { a: Int, b: Int, c: Int ->
        when {
            (a < b) -> Pair(a, b)
            (a < c) -> Pair(b, a)
            else -> Pair(b, c)
        }
    }

    val currentMins =
        if (array[0] <= array[1])
            Pair(array[0], array[1])
        else
            Pair(array[1], array[0])

    val it = array.iterator()
    it.next()
    it.next()

    return task4_23(it, f, currentMins)
}

// task 4.33 (5/9): проверить, чередуются ли в массиве положительные и отрицательные числа
fun task4_33(array: Array<Int>): Boolean {
    fun task4_33(it: Iterator<Int>, f: (Int) -> Boolean, flag: Boolean): Boolean =
        if (it.hasNext()) {
            if (flag != f(it.next()))
                task4_33(it, f, !flag)
            else false
        }
        else true

    val f = { element: Int -> element < 0 }
    val flag = f(array[0])

    return task4_33(array.iterator(), f, !flag)
}

// task 4.36 (6/9): найти максимальный нечетный элемент
fun task4_36(array: Array<Int>): Int {
    fun task4_36(it: Iterator<Int>, f: (Int, Int) -> Int, max: Int): Int =
        if (it.hasNext())
            task4_36(it, f, f(it.next(), max))
        else max

    val f = { a: Int, b: Int -> if (a > b && a % 2 != 0) a else b }
    val result = task4_36(array.iterator(), f, array[0])

    if (result % 2 == 0)
        throw IllegalArgumentException("Array contains only even elements")

    return result
}

// task 4.39 (7/9): вывести вначале его элементы с четными индексами, а затем - с нечетными
fun task4_39(array: Array<Int>): Pair<Array<Int>, Array<Int>> =
    Pair(array.filterIndexed { i, _ -> i % 2 == 0 }.toTypedArray(),
        array.filterIndexed { i, _ -> i % 2 != 0 }.toTypedArray())

// task 4.45 (8/9): массив и интервал a..b, найти сумму
// элементов, значение которых попадает в этот интервал
// *считаем, что нумерация в интервале так же идёт с нуля*
fun task4_45(array: Array<Int>, a: Int, b: Int): Int {
    if ((a < 0) || (b < 0) || (a >= b) || (a >= array.size) || (b >= array.size))
        throw IllegalArgumentException("Incorrect interval")

    return sum(array.filterIndexed { i, _ -> (i > a) && (i < b) }.toTypedArray())
}

// task 4.57 (9/9): для введенного списка найти количество
// таких элементов, которые больше, чем сумма всех предыдущих
fun task4_57(array: Array<Int>): Int {
    fun sumBeforeIndex(array: Array<Int>, endIndex: Int): Int =
        sum(array.filterIndexed { i, _ -> i < endIndex }.toTypedArray())

    return array.withIndex().count { element -> element.value > sumBeforeIndex(array, element.index) } - 1
}

// task 8: переписать task 4 через список

// task 4.9 (1/9): найти элементы, расположенные перед последним минимальным
fun task4_9(list: List<Int>): List<Int> {
    val min = min(list)
    val lastIndexMin = list.indexOfLast { it == min }

    return list.filterIndexed { index, _ -> index < lastIndexMin }
}

// task 4.10 (2/9): два массива, найти количество совпадающих по значению элементов
fun task4_10(list1: List<Int>, list2: List<Int>): Int {
    fun contains(value1: Int, value2: Int, index2: Int, indexAvailability2: MutableList<Boolean>): Boolean {
        return if (value1 == value2 && indexAvailability2[index2]) {
            indexAvailability2[index2] = false
            true
        }
        else false
    }

    val indexAvailability2 = MutableList(list2.size) { true }
    val arrayIndexed2 = list2.withIndex()

    return list1.count { value1 -> arrayIndexed2.any { (index2, value2) -> contains(value1, value2, index2, indexAvailability2) } }
}

// task 4.21 (3/9): найти элементы, расположенные после первого максимального
fun task4_21(list: List<Int>): List<Int> {
    val max = max(list)
    val firstIndexMax = list.indexOfFirst { it == max }

    return list.filterIndexed { index, _ -> index > firstIndexMax }
}

// task 4.23 (4/9): найти два наименьших элемента
fun task4_23(list: List<Int>): Pair<Int, Int> {
    tailrec fun task4_23(it: Iterator<Int>, f: (Int, Int, Int) -> Pair<Int, Int>, mins: Pair<Int, Int>): Pair<Int, Int> =
        if (it.hasNext())
            task4_23(it, f, f(it.next(), mins.first, mins.second))
        else mins

    if (list.size < 2)
        throw IllegalArgumentException("Incorrect list size")

    val f = { a: Int, b: Int, c: Int ->
        when {
            (a < b) -> Pair(a, b)
            (a < c) -> Pair(b, a)
            else -> Pair(b, c)
        }
    }

    val currentMins =
        if (list[0] <= list[1])
            Pair(list[0], list[1])
        else
            Pair(list[1], list[0])

    val it = list.iterator()
    it.next()
    it.next()

    return task4_23(it, f, currentMins)
}

// task 4.33 (5/9): проверить, чередуются ли в массиве положительные и отрицательные числа
fun task4_33(list: List<Int>): Boolean {
    fun task4_33(it: Iterator<Int>, f: (Int) -> Boolean, flag: Boolean): Boolean =
        if (it.hasNext()) {
            if (flag != f(it.next()))
                task4_33(it, f, !flag)
            else false
        }
        else true

    val f = { element: Int -> element < 0 }
    val flag = f(list[0])

    return task4_33(list.iterator(), f, !flag)
}

// task 4.36 (6/9): найти максимальный нечетный элемент
fun task4_36(list: List<Int>): Int {
    fun task4_36(it: Iterator<Int>, f: (Int, Int) -> Int, max: Int): Int =
        if (it.hasNext())
            task4_36(it, f, f(it.next(), max))
        else max

    val f = { a: Int, b: Int -> if (a > b && a % 2 != 0) a else b }
    val result = task4_36(list.iterator(), f, list[0])

    if (result % 2 == 0)
        throw IllegalArgumentException("List contains only even elements")

    return result
}

// task 4.39 (7/9): вывести вначале его элементы с четными индексами, а затем - с нечетными
fun task4_39(list: List<Int>): Pair<List<Int>, List<Int>> =
    Pair(list.filterIndexed { i, _ -> i % 2 == 0 },
        list.filterIndexed { i, _ -> i % 2 != 0 })

// task 4.45 (8/9): массив и интервал a..b, найти сумму
// элементов, значение которых попадает в этот интервал
// *считаем, что нумерация в интервале так же идёт с нуля*
fun task4_45(list: List<Int>, a: Int, b: Int): Int {
    if ((a < 0) || (b < 0) || (a >= b) || (a >= list.size) || (b >= list.size))
        throw IllegalArgumentException("Incorrect interval")

    return sum(list.filterIndexed { i, _ -> (i > a) && (i < b) })
}

// task 4.57 (9/9): для введенного списка найти количество
// таких элементов, которые больше, чем сумма всех предыдущих
fun task4_57(list: List<Int>): Int {
    fun sumBeforeIndex(list: List<Int>, endIndex: Int): Int =
        sum(list.filterIndexed { i, _ -> i < endIndex })

    return list.withIndex().count { element -> element.value > sumBeforeIndex(list, element.index) } - 1
}