package console.command;

import console.Parser;
import data.CollectionManager;
import exception.InvalidArgumentsCountException;

/**
 * Команда выводящая информацию о коллекции
 */
public class ShowCommand extends Command{
    public ShowCommand(CollectionManager manager) {
        super(manager);
    }

    @Override
    public String getInfo() {
        return "Команда show: Выводит в стандартный поток все элементы коллекции в строковом представлении";
    }

    @Override
    public void execute(String[] args, Parser parser){
        if (args.length != 1){
            throw new InvalidArgumentsCountException();
        }
        System.out.println(getManager().show());
    }
}
