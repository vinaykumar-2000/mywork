import java.util.Scanner;

class TomAndJerry
{
  public static void main(String[] args){
	Scanner sc=new Scanner(System.in);

	System.out.println("Enter an integer number: ");
	int number=sc.nextInt();

	if(number >=20 && number <= 30){
	   if(number %2 == 0){
		System.out.println("Jerry");
	    }else{
		System.out.println("Tom");
	    }
	}else{
		System.out.println("Number is not in the range of 20 to 30");
	}

	sc.close();
}
}	