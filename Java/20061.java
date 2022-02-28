import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int N;
	public static int[][] map;
	public static boolean[][] needBoom;
	public static boolean mustBoom;
	public static Queue<Integer> greenQueue;
	public static Queue<Integer> blueQueue;
	public static int score;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		map = new int[10][10];
		needBoom = new boolean[10][10];
		mustBoom = false;
		greenQueue = new LinkedList<Integer>();
		blueQueue = new LinkedList<Integer>();
		score = 0;

		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				map[x][y] = 0;
			}
		}

		StringTokenizer st;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			// System.out.println("블록 배치");
			putRed(t, x, y);
			putGreen(t, x, y);
			putBlue(t, x, y);
			// printMap();

			// System.out.println("빙고 제거");
			boomMap();
			// printMap();

			// System.out.println("특수구간 처리");
			checkSpecial();
			// printMap();

			cleanRed(t, x, y);
		}

		bw.write(score + "\n" + countBlocks());

		bw.flush();
		br.close();
		bw.close();
	}

	public static void initBoomState() {
		mustBoom = false;

		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				needBoom[x][y] = false;
			}
		}
	}

	public static void putRed(int t, int x, int y) {
		if (t == 1) {
			map[x][y] = 1;
		} else if (t == 2) {
			map[x][y] = 1;
			map[x][y + 1] = 1;
		} else {
			map[x][y] = 1;
			map[x + 1][y] = 1;
		}
	}

	public static void putGreen(int t, int x, int y) {
		if (t == 1) {
			for (int r = x + 1; r < 10; r++) {
				if (map[r][y] == 1) {
					map[r - 1][y] = 1;
					break;
				}

				if (r + 1 == 10) {
					map[r][y] = 1;
				}
			}
		} else if (t == 2) {
			for (int r = x + 1; r < 10; r++) {
				if (map[r][y] == 1 || map[r][y + 1] == 1) {
					map[r - 1][y] = 1;
					map[r - 1][y + 1] = 1;
					break;
				}

				if (r + 1 == 10) {
					map[r][y] = 1;
					map[r][y + 1] = 1;
				}
			}
		} else {
			for (int r = x + 2; r < 10; r++) {
				if (map[r][y] == 1) {
					map[r - 2][y] = 1;
					map[r - 1][y] = 1;
					break;
				}

				if (r + 1 == 10) {
					map[r - 1][y] = 1;
					map[r][y] = 1;
				}
			}
		}
	}

	public static void boomMap() {
		initBoomState();

		// 초록색 영역 - 행 검사
		for (int x = 6; x < 10; x++) {
			// System.out.printf("\n초록색 %d행 검사 중\n", x - 6);
			boolean state = true;

			for (int y = 0; y < 4; y++) {
				if (map[x][y] == 0) {
					state = false;
					// System.out.printf("%d행 %d열에서 실패: %d\n", x - 6, y, map[x][y]);
					break;
				}
			}

			if (state) {
				// System.out.printf("빙고!\n");
				mustBoom = true;
				greenQueue.add(x);
				score += 1;

				for (int y = 0; y < 4; y++) {
					needBoom[x][y] = true;
				}
			}
		}

		/*
		 * // 초록색 영역 - 열 검사 for (int y = 0; y < 4; y++) { //
		 * System.out.printf("\n초록색 %d열 검사 중\n", y); boolean state = true;
		 * 
		 * for (int x = 6; x < 10; x++) { if (map[x][y] == 0) { state = false; //
		 * System.out.printf("%d행 %d열에서 실패: %d\n", x - 6, y, map[x][y]); break; } }
		 * 
		 * if (state) { // System.out.printf("빙고!\n"); mustBoom = true;
		 * 
		 * for (int x = 6; x < 10; x++) { needBoom[x][y] = true; } } }
		 * 
		 * // 파란색 영역 - 행 검사 for (int x = 0; x < 4; x++) { //
		 * System.out.printf("\n파란색 %d행 검사 중\n", x); boolean state = true;
		 * 
		 * for (int y = 6; y < 10; y++) { if (map[x][y] == 0) { state = false; //
		 * System.out.printf("%d행 %d열에서 실패: %d\n", x, y - 6, map[x][y]); break; } }
		 * 
		 * if (state) { // System.out.printf("빙고!\n"); mustBoom = true;
		 * 
		 * for (int y = 6; y < 10; y++) { needBoom[x][y] = true; } } }
		 */

		// 파란색 영역 - 열 검사
		for (int y = 6; y < 10; y++) {
			// System.out.printf("\n파란색 %d열 검사 중\n", y - 6);
			boolean state = true;

			for (int x = 0; x < 4; x++) {
				if (map[x][y] == 0) {
					state = false;
					// System.out.printf("%d행 %d열에서 실패: %d\n", x, y - 6, map[x][y]);
					break;
				}
			}

			if (state) {
				// System.out.printf("빙고!\n");
				mustBoom = true;
				blueQueue.add(y);
				score += 1;

				for (int x = 0; x < 4; x++) {
					needBoom[x][y] = true;
				}
			}
		}

		// 터뜨리기
		if (mustBoom) {
			for (int x = 6; x < 10; x++) {
				for (int y = 0; y < 4; y++) {
					if (needBoom[x][y]) {
						map[x][y] = 0;
					}
				}
			}

			while (!greenQueue.isEmpty()) {
				int r = greenQueue.poll();
				pullGreen(r);
			}

			for (int y = 6; y < 10; y++) {
				for (int x = 0; x < 4; x++) {
					if (needBoom[x][y]) {
						map[x][y] = 0;
					}
				}
			}

			while (!blueQueue.isEmpty()) {
				int c = blueQueue.poll();
				pullBlue(c);
			}
		}
	}

	public static void checkSpecial() {
		for (int x = 4; x < 6; x++) {
			boolean state = true;

			for (int y = 0; y < 4; y++) {
				if (map[x][y] == 1) {
					state = false;
					break;
				}
			}

			if (!state) {
				greenQueue.add(x + 4);
			}
		}

		while (!greenQueue.isEmpty()) {
			int r = greenQueue.poll();
			pullGreen(r);
		}

		for (int x = 4; x < 6; x++) {
			for (int y = 0; y < 4; y++) {
				map[x][y] = 0;
			}
		}

		for (int y = 4; y < 6; y++) {
			boolean state = true;

			for (int x = 0; x < 4; x++) {
				if (map[x][y] == 1) {
					state = false;
					break;
				}
			}

			if (!state) {
				blueQueue.add(y + 4);
			}
		}

		while (!blueQueue.isEmpty()) {
			int c = blueQueue.poll();
			pullBlue(c);
		}

		for (int y = 4; y < 6; y++) {
			for (int x = 0; x < 4; x++) {
				map[x][y] = 0;
			}
		}
	}

	public static void pullGreen(int r) {
		for (int y = 0; y < 4; y++) {
			for (int x = r; x >= 5; x--) {
				int tmp = map[x][y];
				map[x][y] = map[x - 1][y];
				map[x - 1][y] = tmp;
			}
		}
	}

	public static void pullBlue(int c) {
		for (int x = 0; x < 4; x++) {
			for (int y = c; y >= 5; y--) {
				int tmp = map[x][y];
				map[x][y] = map[x][y - 1];
				map[x][y - 1] = tmp;
			}
		}
	}

	public static void putBlue(int t, int x, int y) {
		if (t == 1) {
			for (int c = y + 1; c < 10; c++) {
				if (map[x][c] == 1) {
					map[x][c - 1] = 1;
					break;
				}

				if (c + 1 == 10) {
					map[x][c] = 1;
				}
			}
		} else if (t == 2) {
			for (int c = y + 2; c < 10; c++) {
				if (map[x][c] == 1) {
					map[x][c - 2] = 1;
					map[x][c - 1] = 1;
					break;
				}

				if (c + 1 == 10) {
					map[x][c - 1] = 1;
					map[x][c] = 1;
				}
			}
		} else {
			for (int c = y + 1; c < 10; c++) {
				if (map[x][c] == 1 || map[x + 1][c] == 1) {
					map[x][c - 1] = 1;
					map[x + 1][c - 1] = 1;
					break;
				}

				if (c + 1 == 10) {
					map[x][c] = 1;
					map[x + 1][c] = 1;
				}
			}
		}
	}

	public static void cleanRed(int t, int x, int y) {
		if (t == 1) {
			map[x][y] = 0;
		} else if (t == 2) {
			map[x][y] = 0;
			map[x][y + 1] = 0;
		} else {
			map[x][y] = 0;
			map[x + 1][y] = 0;
		}
	}

	public static int countBlocks() {
		int blocks = 0;

		for (int x = 6; x < 10; x++) {
			for (int y = 0; y < 4; y++) {
				if (map[x][y] == 1) {
					blocks += 1;
				}
			}
		}

		for (int y = 6; y < 10; y++) {
			for (int x = 0; x < 4; x++) {
				if (map[x][y] == 1) {
					blocks += 1;
				}
			}
		}

		return blocks;
	}

	public static void printMap() {
		System.out.println();

		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				if (x > 3 && y > 3) {
					System.out.printf("  ");
				} else {
					if (map[x][y] == 0) {
						if (4 <= x && x <= 5 || 4 <= y && y <= 5) {
							System.out.printf("▨ ");
						} else {
							System.out.printf("□ ");
						}
					} else {
						System.out.printf("■ ");
					}
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}