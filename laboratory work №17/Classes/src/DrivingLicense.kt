import java.util.*

class DrivingLicense(private val surname: String,
    private val name: String, private val patronymic: String,
    private val dateOfBirth: MyDate, private val placeOfBirth: String,
    private val dateOfIssue: MyDate, private val expiryDate: MyDate,
    private val policeDepartment: String,
    private val licenseSeries: String, private val licenseNumber: String,
    private val placeOfIssue: String,
    private val categories: List<String>,
    private val drivingExperience: Int): Comparable<DrivingLicense> {

    fun printDrivingLicense() {
        println("ВОДИТЕЛЬСКОЕ УДОСТОВЕРЕНИЕ\n")
        println("1\t$surname")
        println("2\t$name $patronymic")
        println("3\t${dateOfBirth.getDateToPrint()}")
        println("\t$placeOfBirth")
        println("4a)\t${dateOfIssue.getDateToPrint()}\t4b)\t${expiryDate.getDateToPrint()}")
        println("4c)\t$policeDepartment")
        println("5\t$licenseSeries $licenseNumber")
        // 8, потому что 6 и 7 - фото и подпись
        println("8\t$placeOfIssue")
        println("9\t${categories.joinToString(" ")}")
        // 14, потому что до этого - специальные поля
        println("14\tСТАЖ С $drivingExperience")
    }

    // task 3
    // Если этот метод возвращает отрицательное число,
    // то текущий объект будет располагаться перед тем, который передается через параметр;
    // Если метод вернет положительное число, то, наоборот, после второго объекта;
    // Если метод возвратит ноль, значит, оба объекта равны;
    override fun compareTo(other: DrivingLicense): Int {
        return when {
            this.licenseSeries > other.licenseSeries -> -1
            this.licenseSeries < other.licenseSeries -> 1
            else -> {
                when {
                    this.licenseNumber > other.licenseNumber -> -1
                    this.licenseNumber < other.licenseNumber -> 1
                    else -> 0
                }
            }
        }

    }

    // task 4
    private fun isSurnameCorrect(): Boolean =
        """^[А-Я][а-я]+$""".toRegex().find(surname) != null

    private fun isNameCorrect(): Boolean =
        """^[А-Я][а-я]+$""".toRegex().find(name) != null

    private fun isPatronymicCorrect(): Boolean =
        """^[А-Я][а-я]+$""".toRegex().find(patronymic) != null

}