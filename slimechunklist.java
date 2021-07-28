import java.util.Random;
import java.util.Scanner;

public class slimechunklist {
	static boolean isChunk(int xc, int zc, long seed) { // slime chunk?
		Random rnd = new Random(
                seed +
                (int) (xc * xc * 0x4c1906) +
                (int) (xc * 0x5ac0db) +
                (int) (zc * zc) * 0x4307a7L +
                (int) (zc * 0x5f24f) ^ 0x3ad8025fL
        );

        return rnd.nextInt(10) == 0;
	}	
	
	static int[][] getList(long seed, int x1, int y1, int x2, int y2) {
		int count = 0;
		for (int i = x1; i <= x2; i++) {
			for (int j = y1; j <= y2; j++) {
				if (isChunk(i, j, seed)) count++;
			}
		}
		int[][] list = new int[count][2];
		count = 0;
		for (int i = x1; i <= x2; i++) {
			for (int j = y1; j <= y2; j++) {
				if (isChunk(i, j, seed)) {
					list[count][0] = i;
					list[count][1] = j;
					count++;
				}
			}
		}
		return list;
	}
	
	static int[][] getList(long seed) {
		return getList(seed, 0, 0, 255, 255);
	}
	
	public static void main(String[] args) {
		Scanner x = new Scanner(System.in);
		long s;
		while (true) {
			s = x.nextLong();
			int[][] list = getList(s);
			for (int[] r : list) System.out.println(r[0] + " " + r[1]);
		}
		
		// examples
		// 0 0 69420 = false
		// -2 -1 abcde = true
	}
}
