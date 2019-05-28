package co.maxbi.converter.date;

import java.util.Date;

/**
 * Клас для операції з датами
 */
public class DateOperations {


    /**
     * Метод дозволяє знайти різницю між двома датами
     * УВАГА! ПРВОДИТЬСЯ ЗАОКРУГЛЕННЯ ДО ЦІЛОГО!
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int diffInDays(Date date1, Date date2) {
        int daysInt;
        double daysDuble;
        long milliseconds = date2.getTime() - date1.getTime();

        // 24 години = 1 440 хвилин = 1 день
        daysInt = Math.round((float) ((double) milliseconds / (24 * 60 * 60 * 1000)));
        daysDuble = ((double) milliseconds / (24 * 60 * 60 * 1000));
        System.out.println("date2 " + date2 + " = " + date2.getTime());
        System.out.println("date1 " + date1 + " = " + date1.getTime());
        System.out.println("Разница между датами в днях (int): " + daysInt);
        System.out.println("Разница между датами в днях (int): " + daysDuble);


        return daysInt;
    }
}
