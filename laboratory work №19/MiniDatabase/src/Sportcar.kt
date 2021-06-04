class Sportcar(
    dealershipName: String,
    dealershipAddress: String,
    dealershipPhoneNumber: String,
    brand: String, model: String,
    countryOfOrigin: String,
    cost: Double,
    val acceleration: Int,
    val numberOfDoors: Int,
    val engineType: String): Car(
    dealershipName, dealershipAddress, dealershipPhoneNumber,
    brand, model, countryOfOrigin, cost)