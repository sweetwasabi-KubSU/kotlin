class Truck(
    dealershipName: String,
    dealershipAddress: String,
    dealershipPhoneNumber: String,
    brand: String, model: String,
    countryOfOrigin: String,
    cost: Double,
    val function: String,
    val numberOfWheels: Int,
    val loadCapacity: Double): Car(
    dealershipName, dealershipAddress, dealershipPhoneNumber,
    brand, model, countryOfOrigin, cost)