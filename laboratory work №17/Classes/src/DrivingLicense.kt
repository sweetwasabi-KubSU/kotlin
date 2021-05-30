import java.util.*

class DrivingLicense(private val surname: String,
    private val name: String, private val patronymic: String,
    private val dateOfBirth: Date, private val placeOfBirth: String,
    private val dateOfIssue: Date, private val expiryDate: Date,
    private val policeDepartment: String,
    private val licenseSeries: String, private val licenseNumber: String,
    private val placeOfIssue: String,
    private val categories: List<String>,
    private val startingYearOfDriving: Int) {

    fun printDrivingLicense() {
        println("ВОДИТЕЛЬСКОЕ УДОСТОВЕРЕНИЕ\n")
        println("1\t$surname")
        println("2\t$name $patronymic")
        println("3\t${getDate(dateOfBirth)}")
        println("\t$placeOfBirth")
        println("4a)\t${getDate(dateOfIssue)}\t4b)\t${getDate(expiryDate)}")
        println("4c)\t$policeDepartment")
        println("5\t$licenseSeries $licenseNumber")
        // 8, потому что 6 и 7 - фото и подпись
        println("8\t$placeOfIssue")
        println("9\t${categories.joinToString(" ")}")
        // 14, потому что до этого - специальные поля
        println("14\tСТАЖ С $startingYearOfDriving")
    }

    private fun getDate(date: Date): String {
        val check = {date: Int -> if (date < 10) "0$date" else "$date"}
        return "${check(date.date)}.${check(date.month)}.${date.year}"
    }
}