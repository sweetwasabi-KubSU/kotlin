import java.util.*

fun main() {
    // task 1
    val diana = DrivingLicense("ПИВОВАРОВА", "ДИАНА", "ШАМИЛЬЕВНА",
        Date(2000, 3, 28), "КРАСНОДАРСКИЙ КР.",
        Date(2019, 6, 27), Date(2029, 6, 27),
        "ГИБДД 2303", "0123", "456789",
        "КРАСНОДАРСКИЙ КР.", listOf("B", "B1", "M"), 2019)

    // task 2
    diana.printDrivingLicense()
}