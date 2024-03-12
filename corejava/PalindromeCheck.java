import java.util.Scanner;

class PalindromeCheck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        long number = scanner.nextLong();
        
        if (isPalindrome(number)) {
            System.out.println(number + " is palindrome");
            
            long sumOfEvenDigits = sumOfEvenDigits(number);
            if (sumOfEvenDigits > 25) {
                System.out.println("The sum of even numbers is greater than 25");
            } else {
                System.out.println("The sum of even numbers is less than or equal to 25");
            }
        } else {
            System.out.println(number + " is not palindrome");
        }
        
        scanner.close();
    }
    
  
    public static boolean isPalindrome(long number) {
        long temp = number;
        long reverse = 0;
        
        while (temp != 0) {
            long digit = temp % 10;
            reverse = reverse * 10 + digit;
            temp /= 10;
        }
        
        return reverse == number;
    }
    
   
    public static long sumOfEvenDigits(long number) {
        long sum = 0;
        while (number > 0) {
            long digit = number % 10;
            if (digit % 2 == 0) {
                sum += digit;
            }
            number /= 10;
        }
        return sum;
    }
}
