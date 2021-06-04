open class Car(
    dealershipName: String,
    dealershipAddress: String,
    dealershipPhoneNumber: String,
    val brand: String, val model: String,
    val countryOfOrigin: String,
    val cost: Double):
    Dealership(dealershipName, dealershipAddress, dealershipPhoneNumber)