open class Car(
    val brand: String, val model: String, val countryOfOrigin: String, val cost: Int,
    dealershipName: String = "НЕИЗВЕСТНО", dealershipAddress: String = "НЕИЗВЕСТНО", dealershipPhoneNumber: String = "НЕИЗВЕСТНО"):
    Dealership(dealershipName, dealershipAddress, dealershipPhoneNumber) {

    override fun toString(): String {
        return super.toString() + "Марка:\t\t$brand\nМодель:\t\t$model\nCтрана:\t\t$countryOfOrigin\nЦена:\t\t$cost руб.\n"
    }

    }