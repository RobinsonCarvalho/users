package utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilPersonal {

    public static Date formattingDate(String date){

        Date treatedDate;

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        try {
            treatedDate = formatter.parse(date);
            return treatedDate;
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

    }

    public static boolean validateEmail(String email){

        final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(email);
        return matcher.matches()? true: false;

    }

}
