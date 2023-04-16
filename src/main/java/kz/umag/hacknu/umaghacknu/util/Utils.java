package kz.umag.hacknu.umaghacknu.util;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class Utils {
    public static boolean isOld(Date date) {
        Instant fromInstant = date.toInstant();
        Instant toInstant = (new Date()).toInstant();
        Duration duration = Duration.between(fromInstant, toInstant);
        final Duration THIRTY_DAYS = Duration.ofDays(30);

        // Update is older than 30 days
        return duration.compareTo(THIRTY_DAYS) > 0;
    }
}
