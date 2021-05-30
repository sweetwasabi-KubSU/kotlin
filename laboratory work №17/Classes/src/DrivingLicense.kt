import java.util.*

class DrivingLicense (val surname: String, val name: String, val patronymic: String,
                      val dateOfBirth: Date, val cityOfBirth: String,
                      val dateOfIssue: Date, val expiryDate: Date, val policeDepartment: String,
                      private val licenseSeries: String, private val licenseNumber: String,
                      val cityOfIssue: String, val categories: List<String>, val yearOfDrivingExperience: Int )