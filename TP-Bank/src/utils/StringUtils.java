package utils;

public class StringUtils {
    public static String padOrTrunc(String string, int size) {
        if(string.length() > size){
            return string.substring(0, size);
        }
        StringBuilder res = new StringBuilder();
        res.append(string);
        while (res.length() != size) {
            res.append(" ");
        }
        return res.toString();
    }
    public static String repeat(String string, int number){
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < number; i++){
            res.append(string);
        }
        return res.toString();
    }
}
