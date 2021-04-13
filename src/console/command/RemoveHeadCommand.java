package console.command;

import console.Parser;
import data.CollectionManager;
import exception.InvalidArgumentsCountException;

/**
 * Команда выводящая и удаляющая первый элемент коллекции
 */
public class RemoveHeadCommand extends Command{
    public RemoveHeadCommand(CollectionManager manager) {
        super(manager);
    }

    @Override
    public String getInfo() {
        return "Команда remove_head: Выводит первый элемент коллекции и удаляет его";
    }

    @Override
    public void execute(String[] args, Parser parser){
        if (args.length != 1){
            throw new InvalidArgumentsCountException();
        }
        System.out.println(getManager().removeHead());
    }
}
