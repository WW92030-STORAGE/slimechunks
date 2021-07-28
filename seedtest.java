
public class seedtest {
	public static void main(String[] args) { // input 2 numbers and it returns their difference mod 1 << 48
		long a = Long.parseLong("3257840388504953787");
		long b = Long.parseLong("49008055821243");
		System.out.println(Math.abs(a - b) % (1 << 48));
		
	}
}
