package common.helpers;

import java.util.Scanner;

/**
 * Created by kylebutz1 on 10/31/2016.
 */
public class StringHelper extends CommonHelper {
    //notes:    This checks a string. if it is null or empty returns true.
    //          Otherwise, returns false.
    public static boolean isNullOrEmpty(String s){
        return s == null || s.length() == 0;
    }
    public static char[] StringToCharArray (String s){
        if (s == null) {
            return null;
        }
        int len = s.length();
        char[] charArray = new char[len];
        for (int i = 0; i < len; ++i){
            charArray[i] = s.charAt(i);
        }
        return charArray;
    }

    //String s is user prompt
    public static String getNonEmptyUserInput(String s){
        Scanner reader = new Scanner(System.in);
        String stringCheck;

        do {
            System.out.println(s);
            stringCheck = reader.nextLine();
        } while (stringCheck.isEmpty());
        return stringCheck;
    }

}
