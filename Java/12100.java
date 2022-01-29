import java.io.*;
import java.util.*;

public class Main {
	public static int N, result;
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	public static int[] dirY = { -1, 1, 0, 0 };
	public static int[] dirX = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		result = 0;
		int[][] map = new int[N + 2][N + 2];

		for (int y = 0; y <= N + 1; y++) {
			for (int x = 0; x <= N + 1; x++) {
				if (y == 0 || x == 0 || y == N + 1 || x == N + 1) {
					map[y][x] = -1;
				}
			}
		}

		for (int y = 1; y <= N; y++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int x = 1; x <= N; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
				result = Math.max(result, map[y][x]);
			}
		}

		DFS(map, 0);

		bw.write(String.valueOf(result));

		bw.flush();
		bw.close();
		br.close();
	}

	public static void DFS(int[][] map, int depth) {
		if (depth == 5) {
			return;
		}

		int[][] board = new int[N + 2][N + 2];

		for (int i = 0; i < 4; i++) {
			copyMap(map, board);
			if (moveBoard(board, i)) {
				// printBoard(map, board, i, depth + 1);
				DFS(board, depth + 1);
			}
		}
	}

	// origin의 내용을 copy에 복사한다.
	public static void copyMap(int[][] origin, int[][] copy) {
		for (int y = 0; y <= N + 1; y++) {
			for (int x = 0; x <= N + 1; x++) {
				copy[y][x] = origin[y][x];
			}
		}
	}

	public static void printBoard(int[][] map, int[][] board, int dir, int depth) {
		String[] direction = { "UP", "DOWN", "LEFT", "RIGHT" };

		System.out.println();
		for (int i = 1; i < depth; i++) {
			System.out.printf("\t");
		}
		System.out.printf("%s\t%d\n", direction[dir], depth);

		for (int y = 1; y <= N; y++) {
			for (int x = 1; x <= N; x++) {
				System.out.printf("%d\t", map[y][x]);
			}

			System.out.printf("\t\t");

			for (int x = 1; x <= N; x++) {
				System.out.printf("%d\t", board[y][x]);
			}
			System.out.println();
		}
	}

	public static boolean visitable(int y, int x) {
		return (0 <= y) && (y <= N + 1) && (0 <= x) && (x <= N + 1);
	}

	public static boolean moveBoard(int[][] map, int dir) {
		boolean isChanged = false;

		if (dir == UP) {
			for (int y = 1; y <= N; y++) {
				for (int x = 1; x <= N; x++) {
					int val = map[y][x];
					int tmpY = y + 1;

					while (visitable(tmpY, x)) {
						if (val == 0) {
							if (map[tmpY][x] > 0) {
								val = map[tmpY][x];
								map[tmpY][x] = 0;
								isChanged = true;
							}
						} else {
							if (map[tmpY][x] != 0) {
								if (val == map[tmpY][x]) {
									map[y][x] = 2 * val;
									map[tmpY][x] = 0;
									isChanged = true;
								} else {
									map[y][x] = val;
								}
								result = Math.max(map[y][x], result);
								break;
							}
						}
						tmpY += 1;
					}
				}
			}
		} else if (dir == DOWN) {
			for (int y = N; y >= 1; y--) {
				for (int x = 1; x <= N; x++) {
					int val = map[y][x];
					int tmpY = y - 1;

					while (visitable(tmpY, x)) {
						if (val == 0) {
							if (map[tmpY][x] > 0) {
								val = map[tmpY][x];
								map[tmpY][x] = 0;
								isChanged = true;
							}
						} else {
							if (map[tmpY][x] != 0) {
								if (val == map[tmpY][x]) {
									map[y][x] = 2 * val;
									map[tmpY][x] = 0;
									isChanged = true;
								} else {
									map[y][x] = val;
								}
								result = Math.max(map[y][x], result);
								break;
							}
						}
						tmpY -= 1;
					}
				}
			}
		} else if (dir == LEFT) {
			for (int y = 1; y <= N; y++) {
				for (int x = 1; x <= N; x++) {
					int val = map[y][x];
					int tmpX = x + 1;

					while (visitable(y, tmpX)) {
						if (val == 0) {
							if (map[y][tmpX] > 0) {
								val = map[y][tmpX];
								map[y][tmpX] = 0;
								isChanged = true;
							}
						} else {
							if (map[y][tmpX] != 0) {
								if (val == map[y][tmpX]) {
									map[y][x] = 2 * val;
									map[y][tmpX] = 0;
									isChanged = true;
								} else {
									map[y][x] = val;
								}
								result = Math.max(map[y][x], result);
								break;
							}
						}
						tmpX += 1;
					}
				}
			}
		} else {
			for (int y = 1; y <= N; y++) {
				for (int x = N; x >= 1; x--) {
					int val = map[y][x];
					int tmpX = x - 1;

					while (visitable(y, tmpX)) {
						if (val == 0) {
							if (map[y][tmpX] > 0) {
								val = map[y][tmpX];
								map[y][tmpX] = 0;
								isChanged = true;
							}
						} else {
							if (map[y][tmpX] != 0) {
								if (val == map[y][tmpX]) {
									map[y][x] = 2 * val;
									map[y][tmpX] = 0;
									isChanged = true;
								} else {
									map[y][x] = val;
								}
								result = Math.max(map[y][x], result);
								break;
							}
						}
						tmpX -= 1;
					}
				}
			}
		}

		return isChanged;
	}
}