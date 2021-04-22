package data;

import java.util.Date;

/**
 * Класс коллекцией экземпляров которого, управляет программа
 */
public class Product implements Comparable<Product>{
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private double price; //Значение поля должно быть больше 0
    private Integer manufactureCost; //Поле может быть null
    private UnitOfMeasure unitOfMeasure; //Поле не может быть null
    private Organization manufacturer; //Поле не может быть null
    //
    private static int number; // Для уникальности id

    static {
        number = 1;
    }

    {
        id = number++; //*Может увеличиваться во время парсинга, фиксится уменьшением во время работы стороннего метода
        creationDate = new Date();
    }

    /**
     * Конструктор
     * Генерирует новую сущность класса и заполняет поля случайными значениями (Возможно не соответствующими области определения)
     * Используется для тестирования или создания новых объектов с дальнейшим изменением значений
     */
    //Для тестирования
    public Product(){
        name = "Common_" + id;
        coordinates = new Coordinates();
        price = 5 * number;
        manufactureCost = 300 - 3 * number;
        unitOfMeasure = UnitOfMeasure.METERS;
        manufacturer = new Organization();
    }

    /**
     * Устанавливает значение поля id
     * @param id - id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return Возвращает значение поля id
     */
    public int getId(){
        return id;
    }

    /**
     * @return Возвращает значение поля price
     */
    public double getPrice(){
        return price;
    }

    /**
     * Устанавливает значение поля name
     * @param name - name
     * @return Возвращает true - если значение установлено, иначе false
     */
    public boolean setName(String name){
        if (name == null || name.isEmpty()){
            return false;
        } else {
            this.name = name;
            return true;
        }
    }

    /**
     * Устанавливает значение поля coordinates
     * @param coordinates - coordinates
     * @return Возвращает true - если значение установлено, иначе false
     */
    public boolean setCoordinates(Coordinates coordinates){
        if (coordinates == null){
            return false;
        } else {
            this.coordinates = coordinates;
            return true;
        }
    }

    /**
     * Устанавливает значение поля price
     * @param price - price
     * @return Возвращает true - если значение установлено, иначе false
     */
    public boolean setPrice(double price){
        if (price <= 0){
            return false;
        } else {
            this.price = price;
            return true;
        }
    }

    /**
     * Устанавливает значение поля manufactureCost
     * @param manufactureCost - manufactureCost
     * @return Возвращает true - если значение установлено, иначе false
     */
    public boolean setManufactureCost(Integer manufactureCost){
        this.manufactureCost = manufactureCost;
        return true;
    }

    /**
     * Устанавливает значение поля unitOfMeasure
     * @param unitOfMeasure - unitOfMeasure
     * @return Возвращает true - если значение установлено, иначе false
     */
    public boolean setUnitOfMeasure(UnitOfMeasure unitOfMeasure){
        if (unitOfMeasure == null){
            return false;
        } else {
            this.unitOfMeasure = unitOfMeasure;
            return true;
        }
    }

    /**
     * Устанавливает значение поля manufacturer
     * @param organization - manufacturer
     * @return Возвращает true - если значение установлено, иначе false
     */
    public boolean setManufacturer(Organization organization){
        if (organization == null){
            return false;
        } else {
            this.manufacturer = organization;
            return true;
        }
    }

    /**
     * Метод интерфейса Comparable, для сравнения элементов по полю price
     * @param o - Объект класса Product
     * @return Возвращает результат сравнения значения поля price данной сущности и поля price переданного параметра
     */
    @Override
    public int compareTo(Product o) {
        return (Double.compare(this.price, o.price));
    }

    /**
     * Метод преобразования объекта в строку, почти JSON
     * @return Строковое представление объекта
     */
    @Override
    public String toString(){
        //Переделать
        String manufactureCostString;
        if (manufactureCost == null){
            manufactureCostString = "null";
        } else{
            manufactureCostString = manufactureCost.toString();
        }

        return "id: " + id + " name: " + name + " coordinates: {" + coordinates.toString() + "} creationDate: {" + creationDate.toString() + "} price: " + price + " manufactureCost: " + manufactureCostString + " unitOfMeasure: {" + unitOfMeasure.toString() + "} manufacturer: {" + manufacturer.toString() + "}";
    }
}
