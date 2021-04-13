package console.command;

import console.Parser;
import data.CollectionManager;
import exception.InvalidArgumentsCountException;
import exception.InvalidArgumentsTypeException;

/**
 * Команда подсчета количества элементов значение поля price которых, меньше заданного
 */
public class CountLessThanPriceCommand extends Command{
        public CountLessThanPriceCommand(CollectionManager manager) {
                super(manager);
        }

        @Override
        public String getInfo() {
            return "Команда count_less_than_price (price)[double]: Выводит количество элементов значение поля price которого, меньше заданного";
        }

        @Override
        public void execute(String[] args, Parser parser){
                if (args.length != 2){
                        throw new InvalidArgumentsCountException();
                }

                double price;
                try {
                        price = Double.parseDouble(args[1]);
                } catch (NumberFormatException e){
                        throw new InvalidArgumentsTypeException();
                }

                System.out.println(getManager().countLessThanPrice(price));
        }
}
