import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringTokenizer st;
	public static int N, answer;
	public static int[][] map;

	public static enum Dir {
		horizon, vertical, diagonal
	};

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		answer = 0;

		for (int y = 1; y <= N; y++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int x = 1; x <= N; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		map[1][1] = 2;
		map[1][2] = 2;

		DFS(1, 2, Dir.horizon);
		bw.write(String.valueOf(answer));

		br.close();
		bw.close();
	}

	public static void DFS(int sy, int sx, Dir dir) {
		if (sy == N && sx == N) {
			answer += 1;
			return;
		}

		int dy = sy;
		int dx = sx + 1;

		if (visitable(dy, dx) && dir != Dir.vertical) {
			map[dy][dx] = 2;
			DFS(dy, dx, Dir.horizon);
			map[dy][dx] = 0;
		}

		dy = sy + 1;
		dx = sx + 1;

		if (visitable(dy, dx) && visitable(dy - 1, dx) && visitable(dy, dx - 1)) {
			map[dy][dx] = 2;
			map[dy - 1][dx] = 2;
			map[dy][dx - 1] = 2;
			DFS(dy, dx, Dir.diagonal);
			map[dy][dx] = 0;
			map[dy - 1][dx] = 0;
			map[dy][dx - 1] = 0;
		}

		dy = sy + 1;
		dx = sx;

		if (visitable(dy, dx) && dir != Dir.horizon) {
			map[dy][dx] = 2;
			DFS(dy, dx, Dir.vertical);
			map[dy][dx] = 0;
		}
	}

	public static boolean visitable(int y, int x) {
		return 1 <= y && y <= N && 1 <= x && x <= N && map[y][x] == 0;
	}
}