// task 6:  реализовать абстрактный класс коллекция документов с
// абстрактным методом searchDoc(doc : <Doc>). В качестве <Doc> Ваш класс
abstract class Collection {
    abstract fun searchDoc(doc : DrivingLicense): DrivingLicense
}