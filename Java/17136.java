import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringTokenizer st;
	public static int[] paper = { 0, 5, 5, 5, 5, 5 };
	public static int[][] map;
	public static int minVal;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		map = new int[10][10];
		minVal = Integer.MAX_VALUE;

		for (int y = 0; y < 10; y++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int x = 0; x < 10; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		DFS(0, 0, 0);

		if (minVal < Integer.MAX_VALUE) {
			bw.write(String.valueOf(minVal));
		} else {
			bw.write("-1");
		}

		br.close();
		bw.close();
	}

	public static void DFS(int y, int x, int cnt) {
		if (y > 9) {
			if (isClean()) {
				minVal = Math.min(minVal, cnt);
			}

			return;
		}

		if (cnt >= minVal) {
			return;
		}

		if (x > 9) {
			DFS(y + 1, 0, cnt);
			return;
		}

		if (map[y][x] == 1) {
			for (int i = 5; i >= 1; i--) {
				if (paper[i] == 0) {
					continue;
				}

				if (locatable(y, x, i)) {
					locate(y, x, i, 0);
					paper[i] -= 1;
					DFS(y, x + 1, cnt + 1);
					paper[i] += 1;
					locate(y, x, i, 1);
				}
			}
		} else {
			DFS(y, x + 1, cnt);
		}
	}

	public static boolean locatable(int sy, int sx, int size) {
		for (int y = sy; y < sy + size; y++) {
			for (int x = sx; x < sx + size; x++) {
				if (!(0 <= y && y < 10 && 0 <= x && x < 10)) {
					return false;
				}

				if (map[y][x] == 0) {
					return false;
				}
			}
		}

		return true;
	}

	public static void locate(int sy, int sx, int size, int val) {
		for (int y = sy; y < sy + size; y++) {
			for (int x = sx; x < sx + size; x++) {
				map[y][x] = val;
			}
		}
	}

	public static boolean isClean() {
		for (int y = 0; y < 10; y++) {
			for (int x = 0; x < 10; x++) {
				if (map[y][x] == 1) {
					return false;
				}
			}
		}

		return true;
	}
}