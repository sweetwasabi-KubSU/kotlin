class Truck(
    brand: String, model: String, cost: Int,
    val function: String, val numberOfWheels: Int, val loadCapacity: Int,
    countryOfOrigin: String = "НЕИЗВЕСТНО", dealershipName: String = "НЕИЗВЕСТНО",
    dealershipAddress: String = "НЕИЗВЕСТНО", dealershipPhoneNumber: String = "НЕИЗВЕСТНО"):
    Car(brand, model, countryOfOrigin, cost) {

    override fun toString(): String {
        return super.toString() + "Назначение:\t$function\nКолёса:\t\t$numberOfWheels\nГруз:\t\t$loadCapacity т.\n"
    }

    }