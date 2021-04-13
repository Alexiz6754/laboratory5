package data;

/**
 * Класс данных
 */
public class Coordinates {
    private Long x; //Значение поля должно быть больше -601, Поле не может быть null
    private Long y; //Поле не может быть null

    /**
     * Конструктор
     * Генерирует новую сущность класса и заполняет поля случайными значениями (Возможно не соответствующими области определения)
     * Используется для тестирования или создания новых объектов с дальнейшим изменением значений
     */
    public Coordinates(){
        x = 1L;
        y = 1L;
    }

    /**
     * Устанавливает значение поля x
     * @param x - x
     * @return Возвращает true - если значение установлено, иначе false
     */
    public boolean setX(Long x){
        if (x == null || x <= -601){
            return false;
        } else {
            this.x = x;
            return true;
        }
    }

    /**
     * Устанавливает значение поля y
     * @param y - y
     * @return Возвращает true - если значение установлено, иначе false
     */
    public boolean setY(Long y){
        if (y == null){
            return false;
        } else {
            this.y = y;
            return true;
        }
    }

    /**
     * Метод преобразования объекта в строку, почти JSON
     * @return Возвращает строков представление объекта
     */
    @Override
    public String toString(){
        return "x: " + x.toString() + " y: " + y.toString();
    }
}