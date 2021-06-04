import java.lang.IllegalArgumentException
import java.util.*

// вспомогательный класс для хранения даты,
// т.к. Date устарел и работает не так, как надо
class MyDate(_day: Int, _month: Int, _year: Int) {
    val day: Int
    val month: Int
    val year: Int

    init {
        if (isDayCorrect(_day, _month, _year))
            day = _day
        else throw IllegalArgumentException("Incorrect day")

        if (isMonthCorrect(_month))
            month = _month
        else throw IllegalArgumentException("Incorrect month")

        if (isYearCorrect(_year))
            year = _year
        else throw IllegalArgumentException("Incorrect year")
    }

    private fun isDayCorrect(day: Int, month: Int, year: Int): Boolean {
        fun getNumberOfDays(month: Int, year: Int): Int =
            when {
                (month == 1) -> 31
                (month == 2) && (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0)) -> 29
                (month == 2) -> 28
                (month == 3) -> 31
                (month == 4) -> 30
                (month == 5) -> 31
                (month == 6) -> 30
                (month == 7) -> 31
                (month == 8) -> 31
                (month == 9) -> 30
                (month == 10) -> 31
                (month == 11) -> 30
                (month == 12) -> 31
                else -> 0
            }

        return (day >= 1) && (day <= getNumberOfDays(month, year))
    }

    private fun isMonthCorrect(month: Int): Boolean = ((month >= 1) && (month <= 12))
    private fun isYearCorrect(year: Int): Boolean = ((year >= 1950) && (year <= 2050))

    // возвращает дату в формате: "22.03.2021"
    fun getDateToPrint(): String {
        val check = {date: Int -> if (date < 10) "0$date" else "$date"}
        return "${check(day)}.${check(month)}.${year}"
    }
}