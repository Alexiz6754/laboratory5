package console.command;

import console.Parser;
import data.CollectionManager;
import exception.InvalidArgumentsCountException;

/**
 * Команда добавления объекта в коллекцию
 */
public class AddCommand extends Command{
    public AddCommand(CollectionManager manager){
        super(manager);
    }

    @Override
    public String getInfo() {
        return "Команда add {element}: Добавляет новый элемент в коллекцию";
    }

    @Override
    public void execute(String[] args, Parser parser){
        if (args.length != 1){
            throw new InvalidArgumentsCountException();
        }
        System.out.println(getManager().add(parser.parseProduct()));
    }
}
