package common.helpers;

import java.util.Scanner;

public class MathHelper extends CommonHelper {

    public static final double E = 2.71;
    public static final double PI = 3.14;

    public static int square(int val){ return val*val; }
    public static int adder(int val1, int val2){ return val1+val2; }

    private static String userOperandIsMathFunction() {
        Scanner reader = new Scanner(System.in);
        String operand;
        boolean operandTest = true;
        do {
            operand = reader.nextLine();
            if (!operand.equals("+")
                    && !operand.equals("-")
                    && !operand.equals("*")
                    && !operand.equals("/")) {
                System.out.println("INVALID");
                operandTest = false;
            } else {
                operandTest = true;
            }
        } while (!operandTest);
        System.out.println("You chose: '" + operand + "' as your operator.");
        return operand;
    }

}
