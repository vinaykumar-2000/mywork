import java.util.Scanner;

class SumOfIntegers
{
   public static void main(String[] args){
	Scanner sc=new Scanner(System.in);
	int sum=0;

	System.out.println("Enter integers separated by space (non-integer to end): ");
	while(sc.hasNextInt()){
		int num= sc.nextInt();
		sum+= num;
	}

	System.out.println("Total sum: " + sum);
	sc.close();
	}
}