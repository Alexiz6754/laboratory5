package console.command;

import console.Parser;
import data.CollectionManager;
import exception.InvalidArgumentsCountException;

import java.io.*;

/**
 * Команда считывающая и исполняющая скрипт из файла
 */
public class ExecuteScriptCommand extends Command{
    public ExecuteScriptCommand(CollectionManager manager) {
        super(manager);
    }

    @Override
    public String getInfo() {
        return "Команда execute_script (fileName)[String]: Считает и исполнит скрипт из указанного файла";
    }

    @Override
    public void execute(String[] args, Parser parser){
        if (args.length != 2){
            throw new InvalidArgumentsCountException();
        }

        try {
            parser.checkFileAccess(args[1]);
            parser.setFileName(args[1]);
            parser.setBufferedReader(new BufferedReader(new InputStreamReader(new FileInputStream(args[1]))));
        } catch (FileNotFoundException | IllegalAccessException e) {
            System.out.println("С файлом что-то не так. Убедитесь в его существовании и проверьте права на чтение");
            parser.setInteractive();
            return;
        }

        System.out.println(getManager().executeScript(parser));
        parser.setInteractive();

    }
}
