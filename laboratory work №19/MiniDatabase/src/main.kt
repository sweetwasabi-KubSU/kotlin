import com.google.gson.Gson
import java.io.FileReader
import java.io.FileWriter
import java.io.File
import java.io.FileNotFoundException
import com.google.gson.JsonParser
import com.google.gson.JsonArray


fun main() {
    // проверка
    // val dealership = Dealership(
    //    "ЮГ-АВТО", "г. Краснодар ул. Дзержинского, д. 102", "+7 (861) 203-28-82")

    // val dealershipToJson = serializeObject(dealership)
    // val dealershipFromJson = deserializeObject<Dealership>(dealershipToJson)

    // println("***ORIGIN***\n\n$dealership")
    // println("***TO JSON***\n\n$dealershipToJson\n")
    // println("***FROM JSON***:\n\n$dealershipFromJson")

    val dealerships = listOf(
        Dealership("ЮГ-АВТО", "г. Краснодар ул. Дзержинского, д. 102", "+7 (861) 203-28-82"),
        Dealership("ДМ-АВТО","г. Краснодар ул. Крайняя, д. 84", "+7 (909) 445-90-90"),
        Dealership("КЛЮЧАВТО", "г. Краснодар ул. Крылатая, д. 12/3", "+7 (861) 204-37-29"),
        Dealership("ТЕМП АВТО", "г. Краснодар ул. Ростовское ш., д. 26/2", "+7 (202) 202-00-22"),
        Dealership("МОДУС ПЛЮС", "г. Краснодар ул. Ростовское ш., д. 26/2", "+7 (861) 212-71-00"))

    val cars = listOf(
        Car("BMW", "M6 GT3", "Германия", 6500000),
        Car("Audi", "R8", "США", 12550000),
        Car("Lexus", "LC 500", "Италия", 4200000),
        Car("КамАЗ", "54901", "Россия", 10000000),
        Car("Тонар", "7502", "Россия", 7800000))

    val sportcars = listOf(
        Sportcar("Ferrari", "458", 6750000, 2.5, 4, "ДВС"),
        Sportcar("Nissan", "GT-R", 13550000,1.7, 2, "гибридный двигатель"),
        Sportcar("Ford", "GT", 5700000, 1.9, 4, "двигатель Ванкеля"))

    val trucks = listOf(
        Truck("ГАЗ", "54901", 11000000, "специализированное", 8, 3700000),
        Truck("MAN", "7502", 9800000, "специальное", 12, 4000000))

    // task 3
    writeToJson("json/dealerships.json", dealerships)
    writeToJson("json/cars.json", cars)
    writeToJson("json/sportcars.json", sportcars)
    writeToJson("json/trucks.json", trucks)

    // task 4
    println("ИНФОРМАЦИЯ ОБ ОБЪЕКТАХ\n")
    readFromJsonFile<Dealership>("json/dealerships.json").map { println(it) }
    readFromJsonFile<Car>("json/cars.json").map { println(it) }
    readFromJsonFile<Sportcar>("json/sportcars.json").map { println(it) }
    readFromJsonFile<Truck>("json/trucks.json").map { println(it) }

}

// task 2: реализовать процедуры сериализации

// сериализует объект
fun<T> serializeObject(someObject: T): String = Gson().toJson(someObject)

// сериализует список объектов и записывает в json файл
fun<T> writeToJson(fileName: String, jsonObjects: T) {
    if (!fileName.endsWith(".json"))
        throw IllegalArgumentException("$fileName isn't .json file")

    FileWriter(fileName).use { writer ->
        Gson().toJson(jsonObjects, writer) }
}

// task 2: реализовать процедуры десериализации

// десериализует объект
inline fun<reified T> deserializeObject(jsonObject: String): T =
    try {
        Gson().fromJson(jsonObject, T::class.java)
    }
    catch(e: com.google.gson.JsonSyntaxException) {
        throw ClassCastException("Invalid type for deserialization")
    }

// читает список объектов из json файла и десериализует
inline fun<reified T> readFromJsonFile(fileName: String): List<T> {
    if (!fileName.endsWith(".json"))
        throw IllegalArgumentException("$fileName isn't .json file")

    if (!File(fileName).exists())
        throw FileNotFoundException("$fileName doesn't exist")

    val list: MutableList<T> = mutableListOf()
    FileReader(fileName).use { reader ->
        val gson = Gson()
        val array: JsonArray = JsonParser().parse(reader).asJsonArray
        for (jsonElement in array) {
            list.add(gson.fromJson(jsonElement, T::class.java))
        }
    }

    //    return FileReader(fileName).use { reader ->
    //        Gson().fromJson(reader, Array<T>::class.java).toList() }

    return list
}

