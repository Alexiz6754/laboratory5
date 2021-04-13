package data;

/**
 * Класс данных
 */
public class Address {
    private String zipCode; //Поле не может быть null

    /**
     * Конструктор
     * Генерирует новую сущность класса и заполняет поля случайными значениями (Возможно не соответствующими области определения)
     * Используется для тестирования или создания новых объектов с дальнейшим изменением значений
     */
    public Address(){
        zipCode = "Z.I.P";
    }

    /**
     * Устанавливает значение поля zipCode
     * @param zipCode - zipCode
     * @return Возвращает true - если значение установлено, иначе false
     */
    public boolean setZipCode(String zipCode){
        if (zipCode == null) {
            return false;
        } else {
            this.zipCode = zipCode;
            return true;
        }
    }

    /**
     * Метод преобразования объекта в строку, почти JSON
     * @return Строковое представление объекта
     */
    @Override
    public String toString(){
        return "zipCode: " + zipCode;
    }
}
