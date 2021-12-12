package utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UtilPersonal {

     public static Date formattingDate(String date){

        Date treatedDate;

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        try {
            treatedDate = formatter.parse(date);
            return treatedDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
