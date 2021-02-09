package employee.util;

public class StringUtil {

    public static boolean isEmpty(String text) {
        return text == null || text.trim().length() == 0;
    }

    public static String toLowerCase(String text) {
        return text == null ? null : text.trim().toLowerCase();
    }

}
