import java.util.Random;
import java.util.Scanner;

public class slimechunkmap {
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
	
	static boolean isChunk(int xc, int zc, int seed) { // slime chunk?
		Random rnd = new Random(
                seed +
                (int) (xc * xc * 0x4c1906) +
                (int) (xc * 0x5ac0db) +
                (int) (zc * zc) * 0x4307a7L +
                (int) (zc * 0x5f24f) ^ 0x3ad8025fL
        );

        return rnd.nextInt(10) == 0;
	}
	
	static boolean[][] map(int seed, int x1, int y1, int x2, int y2) {
		int temp = 0;
		if (x1 > x2) {
			temp = x1;
			x1 = x2;
			x2 = temp;
		}
		if (y1 > y2) {
			temp = y1;
			y1 = y2;
			y2 = temp;
		}
		
		boolean[][] grid = new boolean[y2 - y1 + 1][x2 - x1 + 1];
		int xc, yc;
		for (int y = 0; y < y2 - y1 + 1; y++) {
			yc = y + y1;
			for (int x = 0; x < x2 - x1 + 1; x++) {
				xc = x + x1; 
				grid[y][x] = isChunk(xc, yc, seed);
			}
		}
		
		return grid;
	}
	
	static void printMap(int seed, int x1, int y1, int x2, int y2, int zoom) {
		boolean[][] grid = map(seed, x1, y1, x2, y2);
		for (boolean[] r : grid) {
			for (int z1 = 0; z1 < zoom; z1++) {
				for (boolean c : r) {
					for (int z2 = 0; z2 < zoom; z2++) {
						if (c) System.out.print('#');
						else System.out.print('.');
					}
				}
				System.out.println();
			}
			
		}
	}
	
	
	
	public static void main(String[] args) {
		Scanner x = new Scanner(System.in);
		int xc, zc, s;
		while (true) {
			xc = x.nextInt();
			zc = x.nextInt();
			s = parseInt(x.next());
			System.out.println(isChunk(xc, zc, s));
			printMap(s, -8, -8, 7, 7, 1);
		}
		
		// examples
		// 0 0 69420 = false
		// -2 -1 abcde = true
	}
}
