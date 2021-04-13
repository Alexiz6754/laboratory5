package data;

/**
 * Класс перечисление
 */
public enum UnitOfMeasure {
    METERS,
    SQUARE_METERS,
    LITERS,
    MILLILITERS,
    GRAMS;

    /**
     * @return Возвращает строковое представление всех констант
     */
    public static String getDescription(){
        StringBuilder builder = new StringBuilder();
        builder.append("Список констант:\n");
        builder.append("METERS\n");
        builder.append("SQUARE_METERS\n");
        builder.append("LITERS\n");
        builder.append("MILLILITERS\n");
        builder.append("GRAMS\n");
        return builder.toString();
    }
}
