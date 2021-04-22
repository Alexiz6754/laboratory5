package console;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import data.*;

import java.io.*;
import java.util.ArrayDeque;


/**
 * Функциональный интерфейс для парсинга полей классов, определяет метод приводящий строку к нужному типу данных
 */
@FunctionalInterface
interface ParseSupplier{
    Object parse(String line);
}

/**
 * Функциональный интерфейс для парсинга полей классов, определяет метод изменяющий значение в конкретном классе
 */
@FunctionalInterface
interface SetPredicate{
    boolean test(Object o);
}

/**
 * Класс обрабатывающий поток ввода
 */
public class Parser {
    private static String collectionFileName; // Имя файла из которого инициализируем коллекцию, и куда сохраняем
    private String fileName; //Имя файла с которым мы сейчас работаем
    BufferedReader bufferedReader;

    {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        fileName = "";
    }

    /**
     * Конструктор
     * @param name - Имя файла в котором хранится коллекция
     */
    public Parser(String name){
        collectionFileName = name;
    }

    /**
     * Метод, возвращающий актуальный поток из которого производится чтение
     * @return Возвращает актуальный поток для чтения
     */
    public BufferedReader getBufferedReader(){
        return bufferedReader;
    }

    /**
     * Возвращает имя файла с которым приложение работает в данный момент
     * @return Возвращает имя текущего файла
     */
    public String getFileName(){
        return this.fileName;
    }

    /**
     * Метод, позволяющий изменить имя целевого файла с которым работает Parser
     * @param fileName - Имя файла
     */
    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    /**
     * Метод, позволяющий изменить поток чтения, используется для переключения между чтением из файла и из консоли
     * @param bufferedReader - Новый поток из которого происходит чтение данных
     */
    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    /**
     * Метод, устанавливающий интерактивный режим работы с пользователем
     */
    //Перевод парсера в интерактивное взаимодействие с пользователем
    public void setInteractive(){
        this.fileName = "";
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Метод, позволяющий узнать имя файла в котором хранится коллекция
     * @return
     */
    public String getCollectionFileName(){
        return collectionFileName;
    }

    /**
     * Метод, считывающий строку и разбивающий ее на слова в виде названия команды и ее аргументов
     * @return Возвращает массив
     */
    public String[] read(){
        String[] words;
        if (fileName.isEmpty()) {
            System.out.print("> "); // Обозначает ввод, для красоты
        }
        while (true){
            String line;

            try {
                line = bufferedReader.readLine().trim(); //Получили строку и убрали лишние пробелы
            } catch (IOException e) {
                System.out.println("Непредвиденная ошибка! Убедитесь в корректности ввода");
                continue;
            } catch (NullPointerException e){

                //Если читаем из файла, то заканчиваем читать
                if (!(fileName.isEmpty())){
                    return null;
                }

                line = "exit"; // Закрываем программу если получили на вход EOF == "^D"
            }

            //Если строка пустая, пропускаем ее и читаем дальше
            if (line.isEmpty()) {
                if (fileName.isEmpty()) {
                    System.out.print("> ");
                }
                continue;
            }

            words = line.trim().split(" *\\s"); //Разбили строку на слова

            return words;
        }
    }


    //Методы парсящие объекты

    /**
     * Метод, парсящий объект класса Product
     * @return - объект Product
     */
    public Product parseProduct(){
        System.out.println("Начинается инициализация объекта Product: ");
        Product result = new Product();

        ParseSupplier stringSupplier = new ParseSupplier() {
            @Override
            public Object parse(String line) {
                return line;
            }
        };
        ParseSupplier doubleSupplier = new ParseSupplier() {
            @Override
            public Object parse(String line) {
                return Double.parseDouble(line);
            }
        };
        ParseSupplier integerSupplier = new ParseSupplier() {
            @Override
            public Object parse(String line) {
                return Integer.parseInt(line);
            }
        };

        parseField("name", stringSupplier, new SetPredicate() {
            @Override
            public boolean test(Object o) {
                return result.setName((String) o);
            }
        });
        parseField("price", doubleSupplier, new SetPredicate() {
            @Override
            public boolean test(Object o) {
                if (o == null){
                    return false;
                }
                return result.setPrice((double) o);
            }
        });
        parseField("manufactureCost", integerSupplier, new SetPredicate() {
            @Override
            public boolean test(Object o) {
                return result.setManufactureCost((Integer) o);
            }
        });

        result.setCoordinates(parseCoordinates());
        result.setUnitOfMeasure(parseUnitOfMeasure());
        result.setManufacturer(parseOrganization());

        return result;
    }

    /**
     * Метод, парсящий объект класса Coordinates
     * @return - объект Coordinates
     */
    public Coordinates parseCoordinates(){
        System.out.println("Начинается инициализация объекта Coordinates: ");
        Coordinates result = new Coordinates();

        ParseSupplier longSupplier = new ParseSupplier() {
            @Override
            public Object parse(String line) {
                return Long.parseLong(line);
            }
        };

        parseField("x", longSupplier, new SetPredicate() {
            @Override
            public boolean test(Object o) {
                return result.setX((Long) o);
            }
        });
        parseField("y", longSupplier, new SetPredicate() {
            @Override
            public boolean test(Object o) {
                return result.setY((Long) o);
            }
        });

        return result;
    }

    /**
     * Метод, парсящий объект класса Address
     * @return - объект Address
     */
    public Address parseAddress(){
        System.out.println("Начинается инициализация объекта Address");
        Address result = new Address();

        ParseSupplier stringSupplier = new ParseSupplier() {
            @Override
            public Object parse(String line) {
                return line;
            }
        };

        parseField("zipCode", stringSupplier, new SetPredicate() {
            @Override
            public boolean test(Object o) {
                return result.setZipCode((String) o);
            }
        });

        return result;
    }

    /**
     * Метод, парсящий объект класса Organization
     * @return - объект Organization
     */
    public Organization parseOrganization(){
        System.out.println("Начинается инициализация объекта Organization: ");
        Organization result = new Organization();

        ParseSupplier stringSupplier = new ParseSupplier() {
            @Override
            public Object parse(String line) {
                return line;
            }
        };

        ParseSupplier integerSupplier = new ParseSupplier() {
            @Override
            public Object parse(String line) {
                return Integer.parseInt(line);
            }
        };

        parseField("name", stringSupplier, new SetPredicate() {
            @Override
            public boolean test(Object o) {
                return result.setName((String) o);
            }
        });
        parseField("fullName", stringSupplier, new SetPredicate() {
            @Override
            public boolean test(Object o) {
                return result.setFullName((String) o);
            }
        });
        parseField("employeesCount", integerSupplier, new SetPredicate() {
            @Override
            public boolean test(Object o) {
                return result.setEmployeesCount((Integer) o);
            }
        });

        result.setPostalAddress(parseAddress());

        return result;
    }

    /**
     * Метод, парсящий объект класса UnitOfMeasure
     * @return - обхект класса UnitOfMeasure
     */
    public UnitOfMeasure parseUnitOfMeasure(){
        System.out.println("Начинается инициализация объекта UnitOfMeasure: ");
        System.out.println(UnitOfMeasure.getDescription());
        UnitOfMeasure result;

        String unit;
        while (true){
            if (fileName.isEmpty()) {
                System.out.print("Введите поле unitOfMeasure: ");
            }

            try {
                unit = bufferedReader.readLine().trim();
            } catch (IOException e) {
                System.out.println("Некорректный ввод. Попробуйте снова");
                continue;
            }

            if (unit.isEmpty()){
                unit = null;
            }

            for (UnitOfMeasure unitOfMeasure : UnitOfMeasure.values()){
                if (unitOfMeasure.toString().equals(unit)){
                    result = unitOfMeasure;
                    return result;
                }
            }

            System.out.println("Некорректный ввод. Попробуйте снова");
        }
    }

    /**
     * Метод, парсящий 'простые' поля (String, int/Integer, long/Long, double/Double ...)
     * @param varName - Название поля, ко вводу которого получает приглашение пользователь
     * @param parse - Метод, реализующий парсинг, нужного типа данных
     * @param set - Метод, реализующий установку, нужного поля
     * @return - полученный объект с типом Object
     */
    public Object parseField(String varName, ParseSupplier parse, SetPredicate set){
        Object result = null;
        String line = "";
        while (true){
            if (fileName.isEmpty()) {
                System.out.print("Введите поле " + varName + ": ");
            }

            try {
                line = bufferedReader.readLine().trim();
                result = parse.parse(line);

                if (line.equals("")){
                    result = null;
                }

            } catch (IOException e) {
                System.out.println("Некорректный ввод. Попробуйте снова");
                continue;
            } catch (NumberFormatException e){
                if (!(line.equals(""))){
                    System.out.println("Некорректный ввод. Попробуйте снова");
                    continue;
                }
            } catch (NullPointerException e){
                System.out.println("Обнаружен конец потока ввода");
                System.out.println("Программа успешно завершена");
                System.exit(0);
            }

            if (set.test(result)) {
                break;
            }

            System.out.println("Некорректный ввод. Попробуйте снова");
        }
        return result;
    }

    /**
     * Метод, преобразующий коллекцию в строку в формате JSON
     * @param collectionManager - объект CollectionManager
     * @return - Строку в формате JSON
     */
    public String collectionToJSON(CollectionManager collectionManager){
        Gson gson = new Gson();
        return gson.toJson(collectionManager.getCollection());
    }

    /**
     * Метод, преобразующий стркоу в формате JSON в ArrayDeque
     * @param source - Строка в формате JSON
     * @return - Коллекция ArrayDeque
     */
    public ArrayDeque fromJSONToDeque(String source){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        //Передаем ArrayDeque и Полную информацию о типе коллекции
        return gson.fromJson(source, new TypeToken<ArrayDeque<Product>>() {}.getType());
    }

    /**
     * Метоод, считывающий файл с которым работает парсер
     * @return - Весь файл в виде строки
     */
    public String readFile(){
        StringBuilder stringBuilder = new StringBuilder();

        try {
            checkFileAccess(this.fileName);
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));

            String str = bufferedReader.readLine();
            while (str != null){
                stringBuilder.append(str);
                str = bufferedReader.readLine();
            }
        } catch (FileNotFoundException | IllegalAccessException e) {
            System.out.println("С файлом что-то не так. Убедитесь в его существовании и проверьте права на чтение");
            return "";
        } catch (IOException e){
            System.out.println("Что-то пошло не так, убедитесь, что с файлом все впорядке");
            return "";
        }

        return stringBuilder.toString();
    }

    /**
     * Метод, записывающий строку в файл
     * @param source - Строка, которую необходимо записать
     * @return - Строку описывающую успешность записи
     */
    public String writeFile(String source){
        try {
            //Проверяем доступен ли файл
            checkFileAccess(this.fileName);

            //Инициализируем буфер
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));

            //Записываем значения в буфер
            bufferedWriter.write(source);

            //Закрываем буфер и во время flush() пишем
            bufferedWriter.close();

        } catch (FileNotFoundException | IllegalAccessException e) {
            System.out.println("С файлом что-то не так. Убедитесь в его существовании и проверьте права на чтение");
            return "Запись в файл остановлена";
        } catch (IOException e) {
            System.out.println("Что-то пошло не так, убедитесь, что с файлом все впорядке");
            return "Запись в файл остановлена";
        }

        return "Файл успешно записан";
    }

    /**
     * Метод для проверки наличия нужных прав доступа к файлу
     * @param fileName - Имя файла
     * @return - true - если права есть
     * @throws IllegalAccessException - Если прав недостаточно, то возвращает исключение
     */
    public boolean checkFileAccess(String fileName) throws IllegalAccessException {
        File file = new File(fileName);
        if (!(file.exists() || file.canRead())){
            throw new IllegalAccessException();
        }

        return true;
    }
}
