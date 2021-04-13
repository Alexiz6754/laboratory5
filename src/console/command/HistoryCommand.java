package console.command;

import console.Parser;
import data.CollectionManager;
import exception.InvalidArgumentsCountException;

/**
 * Команда выводящая историю команд
 */
public class HistoryCommand extends Command{
    private int count;

    public HistoryCommand(CollectionManager manager) {
        super(manager);
        count = 11;
    }

    @Override
    public String getInfo() {
        return "Команда history: Выводит последние " + count + " команд (без их аргументов)";
    }

    @Override
    public void execute(String[] args, Parser parser){
        if (args.length != 1){
            throw new InvalidArgumentsCountException();
        }
        getManager().printHistory(count);
    }
}
