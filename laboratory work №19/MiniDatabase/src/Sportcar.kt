class Sportcar(
    brand: String, model: String, cost: Int,
    val acceleration: Double, val numberOfDoors: Int, val engineType: String,
    countryOfOrigin: String = "НЕИЗВЕСТНО", dealershipName: String = "НЕИЗВЕСТНО",
    dealershipAddress: String = "НЕИЗВЕСТНО", dealershipPhoneNumber: String = "НЕИЗВЕСТНО"):
    Car(brand, model, countryOfOrigin, cost) {

    override fun toString(): String {
        return super.toString() + "Разгон:\t\t$acceleration сек.\nДвери:\t\t$numberOfDoors\nДвигатель:\t$engineType\n"
    }

    }