package console.command;

import console.Parser;
import data.CollectionManager;
import exception.InvalidArgumentsCountException;

/**
 * Команда выводящая значение поля price всех элементов коллекции в порядке убывания
 */
public class PrintFieldDescendingPriceCommand extends Command{
    public PrintFieldDescendingPriceCommand(CollectionManager manager) {
        super(manager);
    }

    @Override
    public String getInfo() {
        return "Команда print_field_descending_price: Выводит значение поля price всех элементов в порядке убывания";
    }

    @Override
    public void execute(String[] args, Parser parser){
        if (args.length != 1){
            throw new InvalidArgumentsCountException();
        }
        System.out.println(getManager().printFieldDescendingPrice());
    }
}
