import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class knuhcemilsTEST { // seed attempt. this program is inputted a seed and tries to reverse it from a list of slime chunks it generates
	// the program outputs the seed mod 1 << 48 due to the nature of java.util.Random and its effect on world generation
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
	
	static int[][] getList(long seed, int x1, int y1, int x2, int y2) { // generates a list of slime chunks
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
	
	static int xx = 255;
	static int[][] getList(long seed) { // generates a list of slime chunks
		return getList(seed, 0, 0, xx, xx);
	}
	
	static int lower18(int[][] chunks) { // check parities on lower 18 bits. nextInt(10) of a number N has the same parity as N - M * 2^18
		// this program works virtually instantly
		for (int i = 0; i < 1 << 18; i++) {
			boolean isGood = true;
			for (int[] r : chunks) {
				if (!chunkEven(r[0], r[1], i)) {
					isGood = false;
					break;
				}
			}
			if (isGood) return i;
		}
		return -1;
	}
	
	static ArrayList<Long> lower48(int[][] chunks) { // check 1 << 30 cases to find the lower 48 bits
		// this program takes up to 30 seconds
		ArrayList<Long> candidates = new ArrayList<Long>();
		long last = lower18(chunks);
		if (last == -1) return candidates;
		for (long i = 0; i < 1L << 30; i++) {
			long cur = (1L << 18) * i + last;
			boolean isGood = true;
			for (int[] r : chunks) {
				if (!isChunk(r[0], r[1], cur)) {
					isGood = false;
					break;
				}
			}
			if (isGood) candidates.add(cur);
		}
		return candidates;
	}
	
	public static void main(String[] args) {
		Scanner x = new Scanner(System.in);
		long n;
		n = x.nextLong();
		
		int[][] arr = getList(n); // generates a list of slime chunks
		
		for (int[] r : arr) System.out.println(r[0] + " " + r[1]);
		
		// here we try to use the list to derive the original seed (mod 1 << 48)
		long start = System.nanoTime();
		long res = lower18(arr);
		System.out.println(res + " = " + Long.toBinaryString(res)); // 314159265 = 10010101110 011011000010100001
		
		ArrayList<Long> list = lower48(arr);
		for (long i : list) System.out.println("--" + i + " " + Long.toBinaryString(i));
		
		System.out.println("Time taken: " + (double)((System.nanoTime() - start) / 1000000) / 1000);
		
		// examples
		// 314159265 takes 23s when x = y = 255
		// 609567216262790763 (173891684220523) takes 24s when x = y = 255
		// 3257840388504953787 (49008055821243) takes 24s when x = y = 255
	}
}
