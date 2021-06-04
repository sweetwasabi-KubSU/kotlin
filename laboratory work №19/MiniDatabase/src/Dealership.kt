open class Dealership(val name: String, val address: String, val phoneNumber: String) {

    override fun toString(): String {
        return "Название:\t$name\nАдрес:\t\t$address\nТелефон:\t$phoneNumber\n"
    }

}