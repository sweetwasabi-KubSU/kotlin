fun main(args: Array<String>) {
   task3()
}

/* Установить Intellij Idea, JDK и плагин для Kotlin
   Реализовать Hello World */
fun task1() = println("Hello, World!")

/* Принять имя пользователя как аргумент программы
   Поздороваться с пользователем. Применить форматирование строки */
fun task3() {
   print("Enter the user name: ")
   val name = readLine()

   println("Hello, $name!")
}