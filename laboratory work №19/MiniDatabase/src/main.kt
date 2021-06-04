import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.FileReader
import java.io.FileWriter

fun main() {
    val dealership = Dealership(
        "ЮГ-АВТО", "г. Краснодар ул. Дзержинского, д. 102", "+7 (861) 203-28-82")

    val dealershipToJson = serializeObject<Dealership>(dealership)
    val dealershipFromJson = deserializeObject<Dealership>(dealershipToJson)

    println("***ORIGIN***\n\n$dealership")
    println("***TO JSON***\n\n$dealershipToJson\n")
    println("***FROM JSON***:\n\n$dealershipFromJson")


//    FileWriter("info.json").use { writer ->
//        Gson().toJson(dealership, writer)
//    }
//
//    FileReader("info.json").use { reader ->
//        Gson().fromJson(reader, Dealership::class.java)
//    }
}

// task 2: реализовать процедуры сериализации
fun<T> serializeObject(someObject: T): String {
    return Gson().toJson(someObject)
}

// task 2: реализовать процедуры десериализации
inline fun<reified T> deserializeObject(jsonObject: String): T {
    return Gson().fromJson<T>(jsonObject, T::class.java)
}