import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringTokenizer st;
	public static boolean[][] visited = new boolean[7][7];
	public static int[] dirY = { 1, 2, 2, 1, -1, -2, -2, -1 };
	public static int[] dirX = { -2, -1, 1, 2, 2, 1, -1, -2 };

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int sy = 0;
		int sx = 0;
		int py = 0;
		int px = 0;

		boolean state = true;

		for (int i = 0; i < 36; i++) {
			String str = br.readLine();

			int y = str.charAt(0) - 'A' + 1;
			int x = str.charAt(1) - '0';

			if (i == 0) {
				sy = y;
				sx = x;
			} else {
				if (!visitable(py, px, y, x) || visited[y][x]) {
					state = false;
					break;
				}
			}

			py = y;
			px = x;

			visited[y][x] = true;
		}

		if (!visitable(py, px, sy, sx)) {
			state = false;
		}

		if (state) {
			bw.write("Valid");
		} else {
			bw.write("Invalid");
		}

		br.close();
		bw.close();
	}

	public static boolean visitable(int sy, int sx, int dy, int dx) {
		boolean state = false;

		for (int i = 0; i < 8; i++) {
			int ty = sy + dirY[i];
			int tx = sx + dirX[i];

			if (ty == dy && tx == dx) {
				state = true;
				break;
			}
		}

		return state;
	}
}