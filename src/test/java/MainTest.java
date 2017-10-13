import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

public class    MainTest {

    public static void main(String[] args) throws ParseException {


        String strDate="2012-03-22";
        java.sql.Date sqlDate = Date.valueOf(strDate);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(sqlDate);
        System.out.println(dateStr);


        java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);

        System.out.println(sqlDate.getTime());
        System.out.println(utilDate.getTime());


    }


}



