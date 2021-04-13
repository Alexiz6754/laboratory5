public class Main {
    public static void main(String[] args) {

        //Берет значение из переменной окружения с названием args[0], если его нет, то = initJSON.txt
        String fileName;
        if (args.length == 1){
            fileName = System.getenv(args[0]);
            if (fileName == null){
                fileName = "initJSON.txt";
            }
        } else {
            fileName = "initJSON.txt";
        }

        Client app = new Client(fileName);
        app.start();
    }
}
