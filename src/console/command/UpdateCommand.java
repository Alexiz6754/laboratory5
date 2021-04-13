package console.command;

import console.Parser;
import data.CollectionManager;
import exception.InvalidArgumentsCountException;
import exception.InvalidArgumentsTypeException;

/**
 * Команда обновляющая элемент коллекции по заданному значению id
 */
public class UpdateCommand extends Command{
    public UpdateCommand(CollectionManager manager) {
        super(manager);
    }

    @Override
    public String getInfo() {
        return "Команда update (id)[int] {element}: Обновляет значение элемента id которого, равен заданному";
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

        System.out.println(getManager().updateId(id,parser.parseProduct()));
    }
}
