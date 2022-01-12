package View.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtils {

    private static Pattern dateRegexPattern = Pattern.compile("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)");

    public static boolean isValidDate(String dateString) {
        Matcher dateMatcher = dateRegexPattern.matcher(dateString);

        if (dateMatcher.matches()) {

            dateMatcher.reset();

            if (dateMatcher.find()) {
                String day = dateMatcher.group(1);
                String month = dateMatcher.group(2);
                int year = Integer.parseInt(dateMatcher.group(3));

                if ("31".equals(day)
                        && ("4".equals(month) || "6".equals(month) || "9".equals(month)
                        || "11".equals(month) || "04".equals(month) || "06".equals(month)
                        || "09".equals(month))) {
                    return false; // 1, 3, 5, 7, 8, 10, 12 has 31 days
                } else if ("2".equals(month) || "02".equals(month)) {
                    //leap year
                    if (year % 4 == 0) {
                        return !"30".equals(day) && !"31".equals(day);
                    } else {
                        return !"29".equals(day) && !"30".equals(day) && !"31".equals(day);
                    }
                } else {
                    return true;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
