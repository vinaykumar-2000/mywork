import java.util.HashSet;
import java.util.Scanner;

public class UniqueNumberChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int number = scanner.nextInt();

            if (isUnique(number)) {
                System.out.println(number + " is a unique number.");
            } else {
                System.out.println(number + " is not a unique number.");
            }
    }
    
    public static boolean isUnique(int number) {
        String numStr = Integer.toString(number);
        HashSet<Character> seenDigits = new HashSet<>();
        
        for (char digit : numStr.toCharArray()) {
            if (!seenDigits.add(digit)) {
                
                return false;
            }
        }
        
        return true; 
    }
}
