package console.command;

import console.Parser;
import data.CollectionManager;
import exception.InvalidArgumentsCountException;

/**
 * Команда очищения коллекции
 */
public class ClearCommand extends Command{
    public ClearCommand(CollectionManager manager) {
        super(manager);
    }

    @Override
    public String getInfo() {
        return "Команда clear: Очищает коллекцию элементов";
    }

    @Override
    public void execute(String[] args, Parser parser){
        if (args.length != 1){
            throw new InvalidArgumentsCountException();
        }
        System.out.println(getManager().clear());
    }
}
