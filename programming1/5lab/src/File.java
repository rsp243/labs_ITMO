package src;

public class File {
    public static String getArray(String[] args) {
        String str = "";
        for (Integer i = 1; i < 5; i++) {
            str += (String) i;
        }
        return str;
    }
}
