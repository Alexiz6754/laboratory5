package console.command;

import console.Parser;
import data.CollectionManager;
import exception.InvalidArgumentsCountException;
import exception.InvalidArgumentsTypeException;

/**
 * Команда удаляющая элемент коллекции по заданному id
 */
public class RemoveByIdCommand extends Command{
    public RemoveByIdCommand(CollectionManager manager) {
        super(manager);
    }

    @Override
    public String getInfo() {
        return "Команда remove_by_id (id)[int]: Удаляет элемент из коллекции по его id";
    }

    @Override
    public void execute(String[] args, Parser parser){
        if (args.length != 2){
            throw new InvalidArgumentsCountException();
        }

        int id;

        try{
            id = Integer.parseInt(args[1]);
        } catch (NumberFormatException e){
            throw new InvalidArgumentsTypeException();
        }

        System.out.println(getManager().removeById(id));
    }
}
