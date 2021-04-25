package data;

import console.CommandInvoker;
import console.Parser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Класс управляющий коллекцией элементов
 */
public class CollectionManager {
    private ArrayDeque<Product> collection;
    private Date dateOfInitialize;
    private CommandInvoker commandInvoker; // Invoker - нужен для работы с историей команд, вывода справки

    {
        collection = new ArrayDeque<Product>();
    }

    /**
     * Конструктор
     * @param commandInvoker - Исполнитель команд, хранит информацию о командах и истории команд
     */
    public CollectionManager(CommandInvoker commandInvoker){
        this.commandInvoker = commandInvoker;
    }

    /**
     * @return Возвращает оллекцию хранимых элементов
     */
    public ArrayDeque<Product> getCollection(){
        return collection;
    }

    /**
     * Устанавливает коллекцию
     * @param collection  - Коллекция
     */
    public void setCollection(ArrayDeque<Product> collection){
        this.collection = collection;
        this.dateOfInitialize = new Date();
    }

    //Methods from invoker
    /**
     * Выводит в поток вывода информацию о всех доступных командах
     */
    public void printInfoAboutCommands(){
        commandInvoker.getCommandMap().forEach((name,command) -> System.out.println(command.getInfo()));
    }

    /**
     * Выводит в поток вывода последние 11 исполненных команд
     */
    public void printHistory(int count){
        System.out.println("Последние " + count + " команд: ");
        for (int i = commandInvoker.getHistory().size() - 1;i >= 0 && i > commandInvoker.getHistory().size() - count;--i){
            System.out.println(commandInvoker.getHistory().get(i));
        }
    }

    //Manager methods

    /**
     * Возвращает информацию о коллекции, тип хранящихся элементов, дата инициализации, количество элементов
     * @return Строковое представление информации о коллекции
     */
    public String getInfo(){
        return "Тип: Product\n" + "Дата инициализации: " + dateOfInitialize.toString() + "\n" + "Количество элементов: " + getSize();
    }

    /**
     * Метод, позволяющий узнать количество элементов в коллекции
     * @return Возвращает количество элементов коллекции
     */
    public int getSize(){
        if (collection == null){
            return 0;
        }

        return collection.size();
    }

    /**
     * Добавляет ноывй элемент в коллекцию
     * @param product - Объект хранящийся в коллекции
     * @return Возвращает информацию о выполнении
     */
    public String add(Product product){
        if (product == null){
            return "Произошла непонятная ошибка, попробуйте еще раз";
        }

        collection.add(product);
        return "Элемент успешно добавлен в коллекцию";
    }

    /**
     * Выводит в поток вывода информацию о всех элементах коллекции
     * @return Возвращает информацию о выполнении
     */
    public String show(){
        if (collection.isEmpty()) {
            return "Ни одного элемента не обнаружено, коллекция пуста";
        }

        for (Product element : collection){
            System.out.println(element.toString());
        }
        return "Все элементы успешно представлены!";
    }

    /**
     * Обновляет элемент в коллекции по заданному параметру id
     * @param id - id элемента в коллекции
     * @param product - Объект элемента на который нужно заменить существующий
     * @return Возвращает информацию о выполнении
     */
    public String updateId(int id, Product product){
        if (product == null){
            System.out.println("Произошла непонятная ошибка, попробуйте еще раз");
        }
        product.setId(id);//Для сохранения id, т.к. введенный объект появляется с новым id

        boolean update = false;
        Product[] arr = collection.toArray(new Product[0]);
        clear();
        for (int i = 0;i < arr.length;++i){
            if (arr[i].getId() == id){
                arr[i] = product;
                update = true;
            }
            collection.add(arr[i]);
        }

        if (update){
            return "Элемент успешно обновлен!";
        } else {
            return "Элемента с введенным id не обнаружно";
        }

    }

    /**
     * Удаляет элемент из коллекции по заданному id
     * @param id - id элемента в коллекции
     * @return Возвращает информацию о выполнении
     */
    public String removeById(int id){
        boolean remove = false;
        Iterator<Product> iterator = collection.iterator();
        while(iterator.hasNext()){
            if (iterator.next().getId() == id){
                iterator.remove();
                remove = true;
            }
        }
        if (remove) {
            return "Элемент успешно удален!";
        } else {
            return "Элемента с введенным id в коллекции не обнаружено!";
        }
    }

    /**
     * Очищает коллекцию от элементов
     * @return Возвращает информацию о выполнении
     */
    public String clear(){
        collection.removeAll(collection);
        return "Коллекция успешно очищена";
    }

    /**
     * Удаляет первый элемент коллекции
     * @return Возвращает информацию о выполнении
     */
    public String removeFirst(){
        if (isEmpty()) {
            return "Коллекция пуста! Удалять нечего";
        } else {
            collection.removeFirst();
            return "Первый элемент коллекции успешно удален";
        }
    }

    /**
     * Выводит, а затем удаляет первый элемент коллекции
     * @return Возвращает информацию о выполнении
     */
    public String removeHead(){
        if (isEmpty()) {
            return "Коллекция пуста! Удалять нечего";
        } else {
            Product product = collection.removeFirst();
            System.out.println(product.toString());
            return "Первый элемент коллекции успешно удален";
        }
    }

    /**
     * Выводит в поток вывода элемент с наименьшим значением поля price
     * @return Возвращает информацию о выполнении
     */
    public String minByPrice(){
        if (isEmpty()){
            return "Коллекция пуста";
        }

        Product tempest = collection.peek();
        for (Product element : collection) {
            if (tempest.getPrice() > element.getPrice()) {
                tempest = element;
            }
        }
        System.out.println(tempest.toString());
        return "Объект успешно обнаружен";
    }

    /**
     * Выводит в поток вывода количество элементов, значение поля price, которых меньше заданного
     * @param price - указаннное значение price
     * @return Возвращает информацию о выполнении
     */
    public String countLessThanPrice(double price){
        if (isEmpty()){
            return "Коллекция пуста";
        }
        int count = 0;
        for (Product element : collection) {
            if (price > element.getPrice()) {
                ++count;
            }
        }
        System.out.println(count);
        return "Успешно выполнено";
    }

    /**
     * Выводит в поток вывода поле price каждого элемента в порядке убывания
     * @return Возвращает информацию о выполнении
     */
    public String printFieldDescendingPrice(){
        Product[] arr = collection.toArray(new Product[0]);
        Arrays.sort(arr, Collections.reverseOrder());
        clear();
        for (Product product : arr) {
            collection.add(product);
            System.out.println(product.getPrice());
        }
        return "Успешно выполнено";
    }

    /**
     * Исполняет команды из переданного скрипта
     * @param parser - Объекта парсера
     * @return Возвращает информацию о выполнении
     */
    public String executeScript(Parser parser){
        String[] args;
        while (true) {
            try {
                args = parser.read();
                if (args == null) {
                    break;
                }

                commandInvoker.execute(args, parser);
            } catch (OutOfMemoryError | StackOverflowError e){
                return "Не хватает ресурсов ЭВМ для исполненения скрипта, исполнении прервано";
            }
        }

        //return "Скрипт успешно исполнен!"; //Убрано из-за лишних строк при уходе в рекурсию
        return "";
    }

    /**
     * Проверяет наличие элементов в коллекции
     * @return Возвращает true - если коллекция пуста, иначе false
     */
    public boolean isEmpty(){
        return collection.isEmpty();
    }

}
