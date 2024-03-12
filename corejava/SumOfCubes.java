class SumOfCubes
{
public static void main(String[] args){

	int num=123;
	int result = sumOf(num);
	System.out.println("Sum of the cubes of digits is: "+num + ": "+result);
}

public static int sumOf(int num){
	int sum=0;
	while(num>0){
		int digit=num%10;
		sum +=cube(digit);
		num/=10;
}
return sum;
}

public static int cube(int num){
return num*num*num;
}
}
	
