import console.CommandInvoker;
import console.Parser;
import console.command.*;
import data.CollectionManager;
import data.Product;

import java.util.ArrayDeque;

/**
 * Класс инкапсулирующий всю логику проекта
 */
public class Client {
    private CollectionManager manager; //Receiver
    private CommandInvoker commandInvoker; // Invoker;
    private Parser parser; // Parser

    {
        commandInvoker = new CommandInvoker();
        manager = new CollectionManager(commandInvoker);
    }

    /**
     * Метод инициализирующий необходимые сущности и запускающий основной цикл обработки ввода
     */
    public void start(){
        //Инициализация существующих команд
        registerALlCommands();

        //Инициализация коллекции значениями из файла
        parser.setFileName(parser.getCollectionFileName());
        manager.setCollection(parser.fromJSONToDeque(parser.readFile()));

        //Проверка на пустой файл
        if (manager.getCollection() == null){
            manager.setCollection(new ArrayDeque<Product>());
        }

        //Основной цикл программы
        parser.setInteractive();
        String[] args;
        while (true){
            args = parser.read();
            commandInvoker.execute(args, parser);
        }
    }

    /**
     * Конструктор класса CLient
     * @param collectionFileName Задает имя файла с коллекцией
     */
    public Client(String collectionFileName){
        parser = new Parser(collectionFileName);
    }

    /**
     * Метод инициализирующий все доступные команды
     * @see CollectionManager Связывает каждую команду с сущностью менеджера коллекции
     */
    public void registerALlCommands(){
        commandInvoker.register("help", new HelpCommand(manager));
        commandInvoker.register("info", new InfoCommand(manager));
        commandInvoker.register("clear", new ClearCommand(manager));
        commandInvoker.register("show", new ShowCommand(manager));
        commandInvoker.register("add", new AddCommand(manager));
        commandInvoker.register("update", new UpdateCommand(manager));
        commandInvoker.register("remove_by_id", new RemoveByIdCommand(manager));
        commandInvoker.register("clear", new ClearCommand(manager));
        commandInvoker.register("save",new SaveCommand(manager));
        commandInvoker.register("execute_script", new ExecuteScriptCommand(manager));
        commandInvoker.register("exit", new ExitCommand(manager));
        commandInvoker.register("remove_first", new RemoveFirstCommand(manager));
        commandInvoker.register("remove_head", new RemoveHeadCommand(manager));
        commandInvoker.register("history", new HistoryCommand(manager));
        commandInvoker.register("min_by_price", new MinByPriceCommand(manager));
        commandInvoker.register("count_less_than_price", new CountLessThanPriceCommand(manager));
        commandInvoker.register("print_field_descending_price", new PrintFieldDescendingPriceCommand(manager));
    }
}
