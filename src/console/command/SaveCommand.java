package console.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import console.Parser;
import data.CollectionManager;
import exception.InvalidArgumentsCountException;

/**
 * Команда сохраняющая коллекцию в файл
 */
public class SaveCommand extends Command{
    public SaveCommand(CollectionManager manager) {
        super(manager);
    }

    @Override
    public String getInfo() {
        return "Команда save: Сохраняет коллекцию в файл";
    }

    @Override
    public void execute(String[] args, Parser parser){
        if (args.length != 1){
            throw new InvalidArgumentsCountException();
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = new Gson();

        parser.setFileName(parser.getCollectionFileName());

        System.out.println(parser.writeFile(parser.collectionToJSON(getManager())));

        parser.setInteractive();
    }
}
