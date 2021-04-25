package exception;

/**
 * Класс исключений, проверяет корректность названия команды
 */
public class InvalidCommandNameException extends RuntimeException{
    public InvalidCommandNameException(){
        super("Ошибка! Команды с таким именем не обнаружено\nУзнать описание всех команд вы сможете вызвав help\n");
    }

    public InvalidCommandNameException(String message){
        super(message);
    }
}
