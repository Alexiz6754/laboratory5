package exception;

/**
 * Класс исключений, проверяет корректность количества переданных команде аргументов
 */
public class InvalidArgumentsCountException extends RuntimeException{
    public InvalidArgumentsCountException(){
        super("Ошибка! Введено несоответствующее сигнатуре команды количество аргументов\nУзнать описание всех команд вы сможете вызвав help\n");
    }

    public InvalidArgumentsCountException(String message){
        super(message);
    }
}
