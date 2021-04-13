package console.command;

import console.Parser;
import data.CollectionManager;
import exception.InvalidArgumentsCountException;

/**
 * Команда выхода и завершения работы программы
 */
public class ExitCommand extends Command{
    public ExitCommand(CollectionManager manager) {
        super(manager);
    }

    @Override
    public String getInfo() {
        return "Команда exit: Завершает программу (без сохранения в файл)";
    }

    @Override
    public void execute(String[] args, Parser parser){
        if (args.length != 1){
            throw new InvalidArgumentsCountException();
        }
        System.out.println("Программа успешно заверешена");
        System.exit(0);
    }
}
