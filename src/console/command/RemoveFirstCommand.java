package console.command;

import console.Parser;
import data.CollectionManager;
import exception.InvalidArgumentsCountException;

/**
 * Команда удаляющая первый элемент из коллекции
 */
public class RemoveFirstCommand extends Command{
    public RemoveFirstCommand(CollectionManager manager) {
        super(manager);
    }

    @Override
    public String getInfo() {
        return "Команда remove_first: Удаляет первый элемент из коллекции";
    }

    @Override
    public void execute(String[] args, Parser parser){
        if (args.length != 1){
            throw new InvalidArgumentsCountException();
        }
        System.out.println(getManager().removeFirst());
    }
}
