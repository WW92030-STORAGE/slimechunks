import java.util.Random;
import java.util.Scanner;

public class slimechunk {
	static int parseInt(String s) {
		try 
        { 
            Integer.parseInt(s); 
            return Integer.parseInt(s);
        }  
        catch (NumberFormatException e)  
        { 
            return s.hashCode();
        } 
	}
	
	static boolean isChunk(int xc, int zc, long seed) { // formal algorithm to determine slime chunks
		Random rnd = new Random(
                seed +
                (int) (xc * xc * 0x4c1906) +
                (int) (xc * 0x5ac0db) +
                (int) (zc * zc) * 0x4307a7L +
                (int) (zc * 0x5f24f) ^ 0x3ad8025fL
        );

        return rnd.nextInt(10) == 0;
	}
	
	public static void main(String[] args) {
		Scanner x = new Scanner(System.in);
		int xc, zc;
		long s;
		while (true) {
			xc = x.nextInt();
			zc = x.nextInt();
			s = parseInt(x.next());
			System.out.println(isChunk(xc, zc, s));
		}
		
		// examples
		// 0 0 69420 = false
		// -2 -1 abcde = true
	}
}
