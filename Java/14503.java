import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int N, M, r, c, d, cnt;
	public static int[] dr = { -1, 0, 1, 0 };
	public static int[] dc = { 0, 1, 0, -1 };
	public static int[][] map;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		st = new StringTokenizer(br.readLine(), " ");

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		cnt = 0;

		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int x = 0; x < M; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		clean();
		// printMap();

		while (check()) {
			clean();
			// printMap();
		}

		bw.write(String.valueOf(cnt));

		bw.flush();
		br.close();
		bw.close();
	}

	public static void clean() {
		if (map[r][c] == 0) {
			map[r][c] = 2;
			cnt += 1;
		}
	}

	public static boolean check() {
		for (int i = 1; i <= 4; i++) {
			int dir = d - i;

			if (dir < 0) {
				dir += 4;
			}

			int tr = r + dr[dir];
			int tc = c + dc[dir];

			if (visitable(tr, tc) && map[tr][tc] == 0) {
				d = dir;
				r = tr;
				c = tc;
				return true;
			}
		}

		int dir = (d + 2) % 4;
		int tr = r + dr[dir];
		int tc = c + dc[dir];

		if (visitable(tr, tc) && map[tr][tc] != 1) {
			r = tr;
			c = tc;
			return check();
		}

		return false;
	}

	public static boolean visitable(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	public static void printMap() {
		System.out.println();

		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				System.out.printf("%d ", map[y][x]);
			}
			System.out.println();
		}
		System.out.println();
	}
}