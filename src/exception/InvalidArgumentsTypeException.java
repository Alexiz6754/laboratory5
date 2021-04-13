package exception;

/**
 * Класс исключений, проверяет корректность типов аргументов переданных команде
 */
public class InvalidArgumentsTypeException extends NumberFormatException{
    public InvalidArgumentsTypeException(){
        super("Ошибка! Тип введенного аргумента не соответсвует указанному\nУзнать описание всех команд вы сможете вызвав help");
    }

    public InvalidArgumentsTypeException(String message){
        super(message);
    }
}
