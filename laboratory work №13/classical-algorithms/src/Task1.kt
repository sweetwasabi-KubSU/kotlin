fun main(args: Array<String>) {
   task5()
}

/* Установить Intellij Idea, JDK и плагин для Kotlin
   Реализовать Hello World */
fun task1() = println("Hello, World!")

/* Принять имя пользователя как аргумент программы
   Поздороваться с пользователем. Применить форматирование строки */
fun task3() {
   val name = inputName()
   println("Hello, $name!")
}

// ввод имени пользователя
fun inputName(): String? {
   print("Enter the user name: ")
   return readLine()
}

/* Продолжение задания 3: Спросить у пользователя, какой язык
   у него любимый, если это Kotlin или Prolog, ответь пользователю,
   что он — подлиза, для других языков придумать комментарий,
   воспользоваться для решения задачи условным оператором и операторм when */
fun task5() {
   val name = inputName()

   println("\n$name, which is your favorite programming language?")
   print(":> ")

   val language: String? = readLine()
   print("\n${chooseAnswerByLanguage(language)}")
}

// выбор ответа в зависимости от языка
fun chooseAnswerByLanguage(language: String?) =
   when(language) {
      "Prolog","Kotlin" -> "You're licksplittle!"
      "C#" -> "Buddy, it's time to start smth new!"
      "C++" -> "I'm sorry, but working with classes on it isn't very convenient!"
      "F#" -> "No, you have Kotlin, so third course won't be able to help"
      "Pascal" -> "You're doing good, kid!"
      else -> "Don't show off like you're the boss"
   }