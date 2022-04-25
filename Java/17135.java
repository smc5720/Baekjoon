import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringTokenizer st;
	public static int N, M, D, max, total;
	public static int[][] ori, map;
	public static int[] pos;

	public static class Pos implements Comparable<Pos> {
		int y, x, w;

		public Pos(int y, int x, int w) {
			this.y = y;
			this.x = x;
			this.w = w;
		}

		@Override
		public int compareTo(Pos o) {
			if (this.w == o.w) {
				return Integer.compare(this.x, o.x);
			}

			return Integer.compare(this.w, o.w);
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		ori = new int[N][M];
		map = new int[N][M];
		pos = new int[3];

		max = 0;
		total = 0;

		for (int y = N - 1; y >= 0; y--) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int x = 0; x < M; x++) {
				ori[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < M - 2; i++) {
			for (int j = i + 1; j < M - 1; j++) {
				if (i == j) {
					continue;
				}

				for (int k = j + 1; k < M; k++) {
					if (i == k || j == k) {
						continue;
					}

					pos[0] = i;
					pos[1] = j;
					pos[2] = k;

					copyMap();
					play();

					max = Math.max(max, total);
					total = 0;
				}
			}
		}

		bw.write(String.valueOf(max));

		br.close();
		bw.close();
	}

	public static void copyMap() {
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				map[y][x] = ori[y][x];
			}
		}
	}

	public static void play() {
		while (isStillEnemy()) {
			for (int i = 0; i < 3; i++) {
				aim(pos[i]);
			}

			kill();
			moveDown();
		}
	}

	public static void aim(int sx) {
		int sy = -1;

		PriorityQueue<Pos> pqueue = new PriorityQueue<Pos>();

		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				if (map[y][x] != 0) {
					int abs = getAbs(sy, sx, y, x);

					if (abs <= D) {
						pqueue.add(new Pos(y, x, abs));
					}
				}
			}
		}

		if (!pqueue.isEmpty()) {
			Pos p = pqueue.peek();
			map[p.y][p.x] = 2;
		}
	}

	public static void kill() {
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				if (map[y][x] == 2) {
					total += 1;
					map[y][x] = 0;
				}
			}
		}
	}

	public static boolean isStillEnemy() {
		int sum = 0;

		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				sum += map[y][x];
			}
		}

		return sum > 0;
	}

	public static void moveDown() {
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				if (y != N - 1) {
					map[y][x] = map[y + 1][x];
				} else {
					map[y][x] = 0;
				}
			}
		}
	}

	public static int getAbs(int sy, int sx, int ey, int ex) {
		return Math.abs(sy - ey) + Math.abs(sx - ex);
	}
}