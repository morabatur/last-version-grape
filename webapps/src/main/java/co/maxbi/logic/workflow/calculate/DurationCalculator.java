package co.maxbi.logic.workflow.calculate;

import co.maxbi.converter.date.DateOperations;
import org.apache.log4j.Logger;

import java.util.Date;

/**
 * Клас призначений для розрахунку спеціального показника "Продолжительность ВТП"
 */
public class DurationCalculator {

    private static final Logger log = Logger.getLogger(DurationCalculator.class);


    public static int calculateDurationVtp(Date begin, Date end, Date periodStart, Date periodEnd) {
        int durationVTP = -1;

        log.info("begin " + begin);
        log.info("end " + end);
        log.info("periodStart " + periodStart);
        log.info("periodEnd " + periodEnd);

        if (begin.before(periodStart)
                && end.after(periodEnd)) {
            durationVTP = DateOperations.diffInDays(periodStart, periodEnd);


        } else if ((begin.after(periodStart) || begin.equals(periodStart))
                && end.after(periodEnd)) {
            durationVTP = DateOperations.diffInDays(begin, periodEnd);


        } else if (begin.before(periodStart)
                && (end.before(periodEnd) || end.equals(periodEnd))) {

            durationVTP = DateOperations.diffInDays(periodStart, end) + 1;


        } else if ((begin.after(periodStart) || begin.equals(periodStart))
                && (end.before(periodEnd) || end.equals(periodEnd))) {

            durationVTP = DateOperations.diffInDays(begin, end) + 1;


        }
        return durationVTP;
    }
}
