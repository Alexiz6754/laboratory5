package console.command;

import console.Parser;
import data.CollectionManager;
import exception.InvalidArgumentsCountException;

/**
 * Команда выводящая любой объект из коллекции значение поля price, которого - минимально
 */
public class MinByPriceCommand extends Command{
    public MinByPriceCommand(CollectionManager manager) {
        super(manager);
    }

    @Override
    public String getInfo() {
        return "Команда min_by_price: Выводит любой объект из коллекции значение поля price, которого является минимальным";
    }

    @Override
    public void execute(String[] args, Parser parser){
        if (args.length != 1){
            throw new InvalidArgumentsCountException();
        }
        System.out.println(getManager().minByPrice());
    }
}
