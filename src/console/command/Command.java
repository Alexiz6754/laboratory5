package console.command;

import console.Parser;
import data.CollectionManager;
import exception.InvalidArgumentsCountException;

/**
 * Абстрактный класс, определяющий внутренне устройство команд
 */
public abstract class Command {
    /**
     * Поле Receiver методы которого будут исполняться
     */
    private CollectionManager manager; // Receiver

    /**
     * Конструктор
     * Нужен для инициализации сущности команды и связывания Receiver с командой
     * @param manager - Receiver
     */
    public Command(CollectionManager manager){
        this.manager = manager;
    }

    /**
     * @return Сигнатура метода возвращающего информацию о команде
     */
    public abstract String getInfo();

    /**
     * Метод в котором описана вся логика команды
     * @param args - Входные данные args[0] - имя команды, args[1...N] - Аргументы команды
     * @param parser - Объект parser
     * @throws InvalidArgumentsCountException Исключение вызывающееся при неверном количестве переданных аргументов команды
     */
    //args - Массив аргументов командной строки, [0] - имя команды, [1..N] - примитивные аргументы команды
    public abstract void execute(String[] args, Parser parser) throws InvalidArgumentsCountException;

    /**
     * @return Возвращает CollectionManager (Receiver)
     */
    public CollectionManager getManager() {
        return manager;
    }


}
