package console.command;

import console.Parser;
import data.CollectionManager;

/**
 * Команда выводящая информацию о коллекции
 */
public class InfoCommand extends Command{
    public InfoCommand(CollectionManager manager) {
        super(manager);
    }

    @Override
    public String getInfo() {
        return "Команда info: Выводит информацию о коллекции";
    }

    @Override
    public void execute(String[] args, Parser parser){}
}
