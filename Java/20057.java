import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int[][] map;
	public static int N;
	public static int answer;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		answer = 0;

		StringTokenizer st;

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		tornado();

		bw.write(String.valueOf(answer));

		bw.flush();
		br.close();
		bw.close();
	}

	public static void tornado() {
		int[] dr = { 0, 1, 0, -1 };
		int[] dc = { -1, 0, 1, 0 };

		int y = N / 2;
		int x = N / 2;
		int cnt = 0;
		int dir = 0;
		int blocks = 1;
		int limit = 1;
		int n = 0;

		while (blocks < N * N) {
			if (map[y + dr[dir]][x + dc[dir]] > 0) {
				spreadSand(y + dr[dir], x + dc[dir], dir);
			}
			// printVisited();
			// printMap();

			if (cnt == 2) {
				cnt = 0;
				limit += 1;
			}

			y += dr[dir];
			x += dc[dir];

			blocks += 1;
			n += 1;

			if (n == limit) {
				n = 0;
				cnt += 1;
				dir += 1;

				if (dir == 4) {
					dir = 0;
				}
			}
		}
	}

	public static int getTotalSand() {
		int total = 0;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				total += map[r][c];
			}
		}

		return total;
	}

	public static void spreadSand(int y, int x, int dir) {
		// α를 제외한 나머지 칸은 모두 9칸이다.
		int[] dy = { 0, -1, -1, -1, -2, 1, 1, 1, 2, 0 };
		int[] dx = { -2, -1, 0, 1, 0, -1, 0, 1, 0, -1 };
		int[] per = { 5, 10, 7, 1, 2, 10, 7, 1, 2, 0 };
		int total = 0;

		for (int i = 0; i < 10; i++) {
			int ty = 0;
			int tx = 0;

			if (dir == 0) {
				ty = y + dy[i];
				tx = x + dx[i];
			} else if (dir == 1) {
				ty = y - dx[i];
				tx = x + dy[i];
			} else if (dir == 2) {
				ty = y - dy[i];
				tx = x - dx[i];
			} else if (dir == 3) {
				ty = y + dx[i];
				tx = x - dy[i];
			}

			int sand = map[y][x] * per[i] / 100;
			total += sand;

			if (visitable(ty, tx)) {
				if (i < 9) {
					map[ty][tx] += sand;
				} else {
					map[ty][tx] += map[y][x] - total;
				}
			} else {
				if (i < 9) {
					answer += sand;
				} else {
					answer += map[y][x] - total;
				}
			}
		}

		map[y][x] = 0;
	}

	public static boolean visitable(int y, int x) {
		return (0 <= y) && (y < N) && (0 <= x) && (x < N);
	}

	public static void printMap() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				System.out.printf("%d ", map[r][c]);
			}
			System.out.println();
		}
		System.out.println("\n");
	}
}