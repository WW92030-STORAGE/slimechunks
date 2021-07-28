import java.util.Random;
import java.util.Scanner;

public class lower18 { // exploring an important property of nextInt(10)
	static boolean chunkEven(int xc, int zc, long seed) { // slime chunk?
		Random rnd = new Random(
                seed +
                (int) (xc * xc * 0x4c1906) +
                (int) (xc * 0x5ac0db) +
                (int) (zc * zc) * 0x4307a7L +
                (int) (zc * 0x5f24f) ^ 0x3ad8025fL
        );

        return rnd.nextInt(10) % 2 == 0;
	}
	
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
				//	System.out.println(i + " " + j);
					list[count][0] = i;
					list[count][1] = j;
					count++;
				}
			}
		}
		return list;
	}
	
	static int[][] getList(long seed) {
		return getList(seed, 0, 0, 15, 15);
	}
	public static void main(String[] args) {
		Scanner x = new Scanner(System.in);
		long n;
		while (true) {
			n = x.nextLong();
			int[][] list = getList(n);
			
			for (int[] r : list) {
				System.out.println(r[0] + " " + r[1] + " " + chunkEven(r[0], r[1], n));
			}
			n -= (69 * 1 << 18); // parities are preserved mod 2^18
			System.out.println("+++" + n);
			for (int[] r : list) {
				System.out.println(r[0] + " " + r[1] + " " + chunkEven(r[0], r[1], n));
			}
		}
	}
}

/* example test case
609567216262790763 
0 2
0 4
0 14
1 0
1 2
1 8
1 9
3 5
3 10
4 5
4 9
5 2
6 0
6 2
6 4
6 6
6 14
7 13
8 7
9 9
10 0
10 3
11 3
12 4
14 1
14 12
15 6
*/
