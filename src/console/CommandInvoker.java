package console;

import console.command.Command;
import data.CollectionManager;
import exception.InvalidArgumentsCountException;
import exception.InvalidArgumentsTypeException;
import exception.InvalidCommandNameException;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Класс отвечающий за исполнение команд
 */
public class CommandInvoker {
    CollectionManager manager;
    ArrayList<String> history;
    HashMap<String, Command> commandMap;

    /**
     * Конструктор
     * Инициализирует поля класса
     */
    public CommandInvoker(){
        history = new ArrayList<String>();
        commandMap = new HashMap<String, Command>();
    }

    /**
     * Метод добавляющий команду в Map доступных команд
     * @param commandName - Имя команды
     * @param command - Объект команды
     */
    public void register(String commandName, Command command){
        commandMap.put(commandName,command);
    }

    /**
     * Метод исполняющий команду и в случае успеха добавляющий ее в историю
     * @param param - Входные данные [0] - имя команды, [1...N] - примитивные аргументы команды
     * @param parser - Объект Parser
     */
    //param - Массив аргументов командной строки, [0] - имя команды, [1..N] - примитивные аргументы команды
    public void execute(String[] param, Parser parser) {
        Command command = commandMap.get(param[0]);

        try {
            if (command == null){
                throw new InvalidCommandNameException();
            }

            command.execute(param, parser);
        } catch (InvalidArgumentsCountException | InvalidCommandNameException | InvalidArgumentsTypeException e){
            System.out.println(e.getMessage());
            return;
        }

        history.add(param[0]);
    }

    /**
     * @return Возвращает Map с зарегистрированными командами
     */
    public HashMap<String, Command> getCommandMap() {
        return commandMap;
    }

    /**
     * @return Возвращает список истории команд
     */
    public ArrayList<String> getHistory() {
        return history;
    }
}
