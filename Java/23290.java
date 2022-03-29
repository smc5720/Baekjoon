import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int M, S;
	public static int sx, sy, total, cnt;
	public static boolean[][] visited;
	public static int[] dx = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	public static int[] dy = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	public static int[] dirX = { -1, 0, 1, 0 };
	public static int[] dirY = { 0, -1, 0, 1 };
	public static Tile[][] map;
	public static Point[] movePos, maxMovePos;

	public static class Tile {
		int x, y;
		int[] fish, copy, move;
		boolean[] smell = new boolean[3];

		public Tile(int x, int y) {
			this.x = x;
			this.y = y;
			this.fish = new int[9];
			this.copy = new int[9];
			this.move = new int[9];
		}

		public void add(int dir) {
			this.fish[dir] += 1;
		}

		public void copyFish() {
			for (int i = 1; i <= 8; i++) {
				copy[i] = fish[i];
			}
		}

		public void copyToFish() {
			for (int i = 1; i <= 8; i++) {
				fish[i] += copy[i];
				copy[i] = 0;
			}
		}

		public void moveFish() {
			for (int i = 1; i <= 8; i++) {

				if (fish[i] <= 0) {
					continue;
				}

				for (int j = 0; j < 8; j++) {
					int dir = i - j;

					if (dir < 1) {
						dir += 8;
					}

					int tx = x + dx[dir];
					int ty = y + dy[dir];

					if (visitable(tx, ty)) {
						// System.out.printf("방향 %d → %d\t(%d, %d) → (%d, %d) %d마리 이동\n", i, dir, x, y,
						// tx, ty, fish[i]);
						map[tx][ty].move[dir] += fish[i];
						fish[i] = 0;
						break;
					}
				}
			}
		}

		public void moveToFish() {
			for (int i = 1; i <= 8; i++) {
				fish[i] += move[i];
				move[i] = 0;
			}
		}

		public void eatFish(int t) {
			int fcnt = getFishCount();

			if (fcnt > 0) {
				int tm = t % 3;
				smell[tm] = true;
				total += fcnt;

				for (int i = 1; i <= 8; i++) {
					fish[i] = 0;
				}
			}
		}

		public int getFishCount() {
			int fcnt = 0;

			for (int i = 1; i <= 8; i++) {
				fcnt += fish[i];
			}

			return fcnt;
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		total = 0;

		map = new Tile[5][5];
		movePos = new Point[3];
		maxMovePos = new Point[3];

		visited = new boolean[5][5];

		for (int x = 1; x <= 4; x++) {
			for (int y = 1; y <= 4; y++) {
				map[x][y] = new Tile(x, y);
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int fx = Integer.parseInt(st.nextToken());
			int fy = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			map[fx][fy].add(d);
		}

		st = new StringTokenizer(br.readLine(), " ");

		sx = Integer.parseInt(st.nextToken());
		sy = Integer.parseInt(st.nextToken());

		// printMap();

		for (int t = 0; t < S; t++) {
			// System.out.printf("%d번째 복제 마법 시전\n", t);

			// 상어가 모든 물고기에게 복제 마법을 시전한다.
			for (int x = 1; x <= 4; x++) {
				for (int y = 1; y <= 4; y++) {
					map[x][y].copyFish();
				}
			}

			// System.out.println("물고기 이동");

			// 모든 물고기가 한 칸 이동한다.
			for (int x = 1; x <= 4; x++) {
				for (int y = 1; y <= 4; y++) {
					map[x][y].moveFish();
				}
			}

			for (int x = 1; x <= 4; x++) {
				for (int y = 1; y <= 4; y++) {
					map[x][y].moveToFish();
				}
			}

			// printMap();
			// System.out.println("상어 이동");

			// 상어가 연속해서 3칸 이동한다.
			cnt = -1;
			DFS(sx, sy, 0, 0);

			for (int i = 0; i < 3; i++) {
				sx = maxMovePos[i].x;
				sy = maxMovePos[i].y;

				map[sx][sy].eatFish(t);
			}

			// printMap();
			// System.out.println("냄새 제거");

			// 두 번 전 연습에서 생긴 물고기의 냄새가 격자에서 사라진다.
			if (t >= 2) {
				for (int x = 1; x <= 4; x++) {
					for (int y = 1; y <= 4; y++) {
						map[x][y].smell[(t - 2) % 3] = false;
					}
				}
			}

			// printMap();
			// System.out.println("마법 완료");

			// 사용한 복제 마법이 완료된다.
			for (int x = 1; x <= 4; x++) {
				for (int y = 1; y <= 4; y++) {
					map[x][y].copyToFish();
				}
			}

			// printMap();
		}

		int answer = 0;

		for (int x = 1; x <= 4; x++) {
			for (int y = 1; y <= 4; y++) {
				answer += map[x][y].getFishCount();
			}
		}

		bw.write(String.valueOf(answer));

		bw.flush();
		br.close();
		bw.close();
	}

	public static boolean visitable(int x, int y) {
		// 격자의 범위를 벗어나는 칸
		if (!((1 <= x) && (x <= 4) && (1 <= y) && (y <= 4))) {
			return false;
		}

		// 상어가 있는 칸
		if ((x == sx && y == sy)) {
			return false;
		}

		// 물고기의 냄새가 있는 칸
		for (int i = 0; i < 3; i++) {
			if (map[x][y].smell[i]) {
				return false;
			}
		}

		return true;
	}

	public static void DFS(int stx, int sty, int depth, int score) {
		if (depth == 3) {
			if (cnt < score) {
				cnt = score;

				for (int i = 0; i < 3; i++) {
					maxMovePos[i] = movePos[i];
				}
			}

			return;
		}

		for (int i = 0; i < 4; i++) {
			int dtx = stx + dirX[i];
			int dty = sty + dirY[i];

			if ((1 <= dtx) && (dtx <= 4) && (1 <= dty) && (dty <= 4)) {
				movePos[depth] = new Point(dtx, dty);
				if (!visited[dtx][dty]) {
					visited[dtx][dty] = true;
					DFS(dtx, dty, depth + 1, score + map[dtx][dty].getFishCount());
					visited[dtx][dty] = false;
				} else {
					DFS(dtx, dty, depth + 1, score);
				}
			}
		}
	}

	public static void printMap() {
		System.out.println();

		for (int x = 1; x <= 4; x++) {
			for (int y = 1; y <= 4; y++) {
				if (x == sx && y == sy) {
					System.out.printf("*\t");
					continue;
				}

				System.out.printf("%d\t", map[x][y].getFishCount());
			}

			System.out.printf("\t\t");

			for (int y = 1; y <= 4; y++) {
				for (int i = 0; i < 3; i++) {
					if (map[x][y].smell[i]) {
						System.out.printf("*\t");
						break;
					}

					if (i == 2) {
						System.out.printf("0\t");
					}
				}
			}

			System.out.println();
		}
		System.out.println();
	}
}