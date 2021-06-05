import java.util.*

fun main() {
    // task 1
    val diana = DrivingLicense("ПИВОВАРОВА", "ДИАНА", "ШАМИЛЬЕВНА",
        MyDate(28, 3, 2061), "КРАСНОДАРСКИЙ КР.",
        MyDate(27, 6, 2019), MyDate(27, 6, 2029),
        "ГИБДД 2303", "0123", "456789",
        "КРАСНОДАРСКИЙ КР.", listOf("B", "B1", "M"), 2019)

    // task 2
    diana.printDrivingLicense()
}