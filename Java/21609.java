import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int[][] map;
	public static boolean[][] visited;
	public static int N, M;
	public static int[] dirY = { -1, 1, 0, 0 };
	public static int[] dirX = { 0, 0, -1, 1 };
	public static int blockCnt;
	public static int rainbowCnt;
	public static Point maxPos;
	public static int score;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];
		visited = new boolean[N + 1][N + 1];

		for (int y = 1; y <= N; y++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int x = 1; x <= N; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
				visited[y][x] = false;
			}
		}

		int totalScore = 0;

		while (search()) {
			// System.out.println("탐색 완료");
			// printMap();

			score = 0;
			boom(maxPos.y, maxPos.x, map[maxPos.y][maxPos.x]);
			// System.out.printf("스코어: %d → %d\n", totalScore, totalScore + score * score);
			totalScore += score * score;

			// System.out.println("폭파 완료");
			// printMap();

			gravity();
			// System.out.println("중력 완료");
			// printMap();

			rotate();
			// System.out.println("회전 완료");
			// printMap();

			gravity();
			// System.out.println("중력 완료");
			// printMap();
		}

		bw.write(String.valueOf(totalScore));

		bw.flush();
		br.close();
		bw.close();
	}

	public static void initVisited(boolean onlyZero) {
		if (onlyZero) {
			for (int y = 1; y <= N; y++) {
				for (int x = 1; x <= N; x++) {
					if (map[y][x] == 0) {
						visited[y][x] = false;
					}
				}
			}
		} else {
			for (int y = 1; y <= N; y++) {
				for (int x = 1; x <= N; x++) {
					visited[y][x] = false;
				}
			}
		}
	}

	public static boolean search() {
		initVisited(false);

		int maxCnt = 0;
		int maxRainbow = 0;
		maxPos = new Point(0, 0);

		boolean searched = false;

		for (int y = 1; y <= N; y++) {
			for (int x = 1; x <= N; x++) {
				if (!visited[y][x] && map[y][x] > 0) {
					// System.out.printf("%d, %d 서치: %d\n", y, x, map[y][x]);
					blockCnt = 0;
					rainbowCnt = 0;
					DFS(y, x, map[y][x]);

					// System.out.printf("blockCnt: %d\n", blockCnt);
					// System.out.printf("rainbowCnt: %d\n", rainbowCnt);
					initVisited(true);

					if (blockCnt >= 2) {
						if (maxCnt < blockCnt) {
							// System.out.printf("maxCnt 갱신: %d → %d\n", maxCnt, blockCnt);
							searched = true;
							maxCnt = blockCnt;
							maxPos = new Point(x, y);
							maxRainbow = rainbowCnt;
						} else if (maxCnt == blockCnt) {
							if (maxRainbow <= rainbowCnt) {
								maxPos = new Point(x, y);
								maxRainbow = rainbowCnt;
							}
						}
					}
				}
			}
		}

		return searched;
	}

	public static void DFS(int y, int x, int color) {
		visited[y][x] = true;
		blockCnt += 1;

		if (map[y][x] == 0) {
			rainbowCnt += 1;
		}

		for (int i = 0; i < 4; i++) {
			int ty = y + dirY[i];
			int tx = x + dirX[i];

			if (visitable(ty, tx) && !visited[ty][tx] && (map[ty][tx] == 0 || map[ty][tx] == color)) {
				DFS(ty, tx, color);
			}
		}
	}

	public static void boom(int y, int x, int color) {
		score += 1;
		map[y][x] = -2;

		for (int i = 0; i < 4; i++) {
			int ty = y + dirY[i];
			int tx = x + dirX[i];

			if (visitable(ty, tx) && (map[ty][tx] == 0 || map[ty][tx] == color)) {
				boom(ty, tx, color);
			}
		}
	}

	public static void gravity() {
		for (int x = 1; x <= N; x++) {
			for (int y = N; y > 1; y--) {
				if (map[y][x] == -2) {
					int ty = y - 1;

					while (ty >= 1 && map[ty][x] == -2) {
						ty -= 1;
					}

					if (ty != 0 && map[ty][x] > -1) {
						int tmp = map[y][x];
						map[y][x] = map[ty][x];
						map[ty][x] = tmp;
					}
				}
			}
		}
	}

	public static void rotate() {
		for (int i = 0; i < N / 2; i++) {
			int len = N - 2 * i;
			int sy = i + 1;
			int sx = i + 1;
			int dy = i + len;
			int dx = i + len;

			int[] arr = new int[len - 1];
			int idx = 0;

			for (int x = sx; x < dx; x++) {
				arr[idx] = map[sy][x];
				idx += 1;
			}

			idx = sx;

			for (int y = sy; y < dy; y++) {
				map[sy][idx] = map[y][dx];
				idx += 1;
			}

			idx = sy;

			for (int x = dx; x > sx; x--) {
				map[idx][dx] = map[dy][x];
				idx += 1;
			}

			idx = dx;

			for (int y = dy; y > sy; y--) {
				map[dy][idx] = map[y][sx];
				idx -= 1;
			}

			idx = 0;

			for (int y = dy; y > sy; y--) {
				map[y][sx] = arr[idx];
				idx += 1;
			}
		}
	}

	public static boolean visitable(int y, int x) {
		return (1 <= y) && (y <= N) && (1 <= x) && (x <= N);
	}

	public static void printMap() {
		System.out.println();

		for (int y = 1; y <= N; y++) {
			for (int x = 1; x <= N; x++) {
				if (map[y][x] == -2) {
					System.out.printf("  ", map[y][x]);
				} else if (map[y][x] == -1) {
					System.out.printf("■ ", map[y][x]);
				} else {
					System.out.printf("%d ", map[y][x]);
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}