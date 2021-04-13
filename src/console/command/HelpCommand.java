package console.command;

import console.Parser;
import data.CollectionManager;
import exception.InvalidArgumentsCountException;

/**
 * Команда выводящая справку по всем доступным командам
 */
public class HelpCommand extends Command{
    public HelpCommand(CollectionManager manager) {
        super(manager);
    }

    @Override
    public String getInfo() {
        return "Команда help: Выводит справку по всем доступным командам";
    }

    @Override
    public void execute(String[] args, Parser parser){
        if (args.length != 1){
            throw new InvalidArgumentsCountException();
        }
        getManager().printInfoAboutCommands();
    }
}
