import exception.InvalidArgumentsTypeException;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        String fileName = "";
        try {
            fileName = Optional.ofNullable(System.getenv("alex")).orElseThrow(InvalidArgumentsTypeException::new);
        } catch (InvalidArgumentsTypeException e){
            System.out.println("Переменной нет, умираю");
            System.exit(0);
        }

        //fileName = "initJSON.txt";

        Client app = new Client(fileName);
        app.start();
    }
}
