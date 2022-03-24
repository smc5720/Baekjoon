import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int N, M;
	public static int[][] map, seq;
	public static int[] dy = { 0, -1, 1, 0, 0 };
	public static int[] dx = { 0, 0, 0, -1, 1 };
	public static int total;
	public static HashMap<Integer, Point> hashMap;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];
		seq = new int[N + 1][N + 1];
		hashMap = new HashMap<Integer, Point>();

		for (int y = 1; y <= N; y++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int x = 1; x <= N; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		int sy = (N + 1) / 2;
		int sx = (N + 1) / 2;

		map[sy][sx] = -1;
		total = 0;

		initHash();

		for (int i = 0; i < M; i++) {
			// System.out.printf("#%d번째 블리자드\n\n", i + 1);
			st = new StringTokenizer(br.readLine(), " ");

			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			// System.out.printf("구슬 파괴\n\n");
			destroyMarble(d, s);
			// printMap();

			// System.out.printf("구슬 이동\n\n");
			moveMarble();
			// printMap();

			while (explodeMarble()) {
				// System.out.printf("구슬 폭발\n\n");
				// printMap();

				// System.out.printf("구슬 이동\n\n");
				moveMarble();
				// printMap();
			}

			// System.out.printf("그룹 나누기\n\n");
			divideGroup();
			// printMap();
		}

		bw.write(String.valueOf(total));

		bw.flush();
		br.close();
		bw.close();
	}

	public static void initHash() {
		int sy = (N + 1) / 2;
		int sx = (N + 1) / 2;

		int[] dirY = { 0, 1, 0, -1 };
		int[] dirX = { -1, 0, 1, 0 };

		int cnt = 0;
		int dir = 0;
		int limit = 1;
		int move = 0;

		hashMap.put(0, new Point(sx, sy));

		for (int i = 1; i < N * N; i++) {
			sy += dirY[dir];
			sx += dirX[dir];

			Point p = new Point(sx, sy);

			hashMap.put(i, p);
			move += 1;

			if (move == limit) {
				move = 0;
				dir += 1;
				cnt += 1;
			}

			if (cnt == 2) {
				cnt = 0;
				limit += 1;
			}

			if (dir == 4) {
				dir = 0;
			}
		}
	}

	public static void destroyMarble(int d, int s) {
		int sy = (N + 1) / 2;
		int sx = (N + 1) / 2;

		for (int i = 1; i <= s; i++) {
			map[sy + dy[d] * i][sx + dx[d] * i] = 0;
		}
	}

	public static void moveMarble() {
		Queue<Integer> queue = new LinkedList<Integer>();

		for (int i = 1; i < N * N; i++) {
			Point p = hashMap.get(i);

			if (map[p.y][p.x] > 0) {
				queue.add(map[p.y][p.x]);
				map[p.y][p.x] = 0;
			}
		}

		int idx = 1;

		while (!queue.isEmpty()) {
			Point p = hashMap.get(idx);
			map[p.y][p.x] = queue.poll();
			idx += 1;
		}
	}

	public static boolean explodeMarble() {
		boolean state = false;

		for (int i = 1; i < N * N; i++) {
			Point p = hashMap.get(i);
			int color = map[p.y][p.x];

			if (color == 0) {
				continue;
			}

			int cnt = 1;

			for (int j = 1; i + j < N * N; j++) {
				Point tp = hashMap.get(i + j);

				if (color == map[tp.y][tp.x]) {
					cnt += 1;
				} else {
					break;
				}
			}

			if (cnt >= 4) {
				total += cnt * color;
				state = true;

				for (int j = 0; j < cnt; j++) {
					Point tp = hashMap.get(i + j);
					map[tp.y][tp.x] = 0;
				}
			}
		}

		return state;
	}

	public static void divideGroup() {
		Queue<Point> queue = new LinkedList<Point>();
		boolean[][] visited = new boolean[N + 1][N + 1];

		for (int i = 1; i < N * N; i++) {
			Point p = hashMap.get(i);
			int color = map[p.y][p.x];

			if (color == 0) {
				break;
			}

			if (visited[p.y][p.x]) {
				continue;
			}

			visited[p.y][p.x] = true;
			int cnt = 1;

			for (int j = 1; i + j < N * N; j++) {
				Point tp = hashMap.get(i + j);

				if (color == map[tp.y][tp.x]) {
					visited[tp.y][tp.x] = true;
					cnt += 1;
				} else {
					break;
				}
			}

			queue.add(new Point(cnt, color));
		}

		int idx = 1;

		while (!queue.isEmpty() && idx < N * N) {
			boolean odd = true;
			Point p = queue.poll();

			int cnt = p.x;
			int color = p.y;

			for (int i = 0; i < 2; i++) {
				if (idx >= N * N) {
					break;
				}

				Point tp = hashMap.get(idx);

				if (odd) {
					map[tp.y][tp.x] = cnt;
				} else {
					map[tp.y][tp.x] = color;
				}

				odd = !odd;
				idx += 1;
			}
		}
	}

	public static void printMap() {
		for (int y = 1; y <= N; y++) {
			for (int x = 1; x <= N; x++) {
				if (map[y][x] == -1) {
					System.out.printf("*\t", map[y][x]);
				} else if (map[y][x] == 0) {
					System.out.printf("-\t", map[y][x]);
				} else {
					System.out.printf("%d\t", map[y][x]);
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void printSeq() {
		for (int i = 0; i < N * N; i++) {
			Point p = hashMap.get(i);
			seq[p.y][p.x] = i;
		}

		for (int y = 1; y <= N; y++) {
			for (int x = 1; x <= N; x++) {
				if (y == (N + 1) / 2 && x == (N + 1) / 2) {
					System.out.printf("*\t", seq[y][x]);
					continue;
				}
				System.out.printf("%d\t", seq[y][x]);
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void printSeq(int sy, int sx) {
		int[][] tmpMap = new int[N + 1][N + 1];
		int val = seq[sy][sx];

		Point p = hashMap.get(val);
		tmpMap[p.y][p.x] = 0;

		int idx = 0;

		for (int i = val - 1; i >= 1; i--) {
			idx += 1;
			p = hashMap.get(i);
			tmpMap[p.y][p.x] = idx;
		}

		idx = 0;

		for (int i = val + 1; i < N * N; i++) {
			idx += 1;
			p = hashMap.get(i);
			tmpMap[p.y][p.x] = idx;
		}

		for (int y = 1; y <= N; y++) {
			for (int x = 1; x <= N; x++) {
				if (y == (N + 1) / 2 && x == (N + 1) / 2) {
					System.out.printf("*\t", tmpMap[y][x]);
					continue;
				}
				System.out.printf("%d\t", tmpMap[y][x]);
			}
			System.out.println();
		}
		System.out.println();
	}
}