import java.util.Scanner;

public class Prec {
    /**
     *     public static boolean isACorrectInt(String value) {
     *         boolean isANumber = true;
     *         if (value.startsWith("-")) {
     *             value = value.substring(1);
     *         }
     *         if(value.length() != 0){
     *             for (int indexChar = 0; indexChar < value.length(); indexChar++) {
     *                 if (value.charAt(indexChar) < '0' || value.charAt(indexChar) > '9') {
     *                     isANumber = false;
     *                     break;
     *                 }
     *             }
     *         }
     *         else {
     *             isANumber = false;
     *         }
     *         return isANumber;
     *     }
     */
    /*
     *     public static Integer saisieIntegerWithoutTryAndCatch(Scanner scan) {
     *         boolean isInputCorrect = false;
     *         Integer nombre = null;
     *         do {
     *             String numberString = scan.next();
     *             if (isACorrectInt(numberString)) {
     *                 nombre = Integer.parseInt(numberString);
     *                 isInputCorrect = true;
     *             } else {
     *                 System.out.println("Votre saisie est incorrecte. Veuillez reesayez");
     *             }
     *         } while(!isInputCorrect);
     *         return nombre;
     *
     *     }
     */

    /**
     * Method that checks whether or not the string corresponds to an int.
     * @param value
     * @return
     */
    public static boolean isACorrectInt(String value) {
        boolean isANumber = true;
        if (value.startsWith("-")) {
            value = value.substring(1);
        }
        if(!value.isEmpty()){
            for (int indexChar = 0; indexChar < value.length(); indexChar++) {
                if (value.charAt(indexChar) < '0' || value.charAt(indexChar) > '9') {
                    isANumber = false;
                    break;
                }
            }
        }
        else {
            isANumber = false;
        }
        return isANumber;
    }

    public static Integer saisieIntegerWithoutTryAndCatch(Scanner scan) throws NotAIntegerException {
        Integer nombre = null;
        String numberString = scan.next();
        if (isACorrectInt(numberString)) {
            nombre = Integer.parseInt(numberString);
        } else {
            throw new NotAIntegerException();
        }
        return nombre;

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir un nombre");
        try {
            saisieIntegerWithoutTryAndCatch(scanner);
        }catch (NotAIntegerException e){
            e.printStackTrace();
        }
        scanner.close();
    }
}
