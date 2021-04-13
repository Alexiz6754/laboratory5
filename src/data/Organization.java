package data;

/**
 * Класс данных
 */
public class Organization {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String fullName; //Поле может быть null
    private Integer employeesCount; //Поле не может быть null, Значение поля должно быть больше 0
    private Address postalAddress; //Поле может быть null
    //
    private static int number; // Номер организации для уникализации поля id \ Обдумать вариант с хранением всех  id в какой нибудь коллекции

    static {
        number = 0;
    }

    {
        id = number++;
    }

    /**
     * Конструктор
     * Генерирует новую сущность класса и заполняет поля случайными значениями (Возможно не соответствующими области определения)
     * Используется для тестирования или создания новых объектов с дальнейшим изменением значений
     */
    public Organization(){
        name = "Standard";
        fullName = "FUll standard";
        employeesCount = 1;
        postalAddress = new Address();

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
     * Устанавливает значение поля fullName
     * @param fullName - fullName
     * @return Возвращает true - если значение установлено, иначе false
     */
    public boolean setFullName(String fullName){
        if (fullName == null){
            return false;
        } else {
            this.fullName = fullName;
            return true;
        }
    }

    /**
     * Устанавливает значение поля employeesCount
     * @param employeesCount - employeesCount
     * @return Возвращает true - если значение установлено, иначе false
     */
    public boolean setEmployeesCount(Integer employeesCount){
        if (employeesCount == null || employeesCount <= 0){
            return false;
        } else {
            this.employeesCount = employeesCount;
            return true;
        }
    }

    /**
     * Устанавливает значение поля postalAddress
     * @param postalAddress - postalAddress
     * @return Возвращает true - если значение установлено, иначе false
     */
    public boolean setPostalAddress(Address postalAddress){
        if (postalAddress == null){
            return false;
        } else {
            this.postalAddress = postalAddress;
            return true;
        }
    }

    /**
     * Метод преобразования объекта в строку, почти JSON
     * @return Строквое представление объекта
     */
    @Override
    public String toString(){
        return "id: " + id + " name: " + name + " fullName: " + fullName + " employeesCount: " + employeesCount.toString() + " postalAddress: {" + postalAddress.toString() + "}";
    }

}
