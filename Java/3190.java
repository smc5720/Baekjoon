import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
	public static int N, K, L;
	public static int[][] map;
	public static int[] dirY = { 0, 1, 0, -1 };
	public static int[] dirX = { 1, 0, -1, 0 };
	public static Queue<Point> snake;
	public static int snakeDir = 0;
	public static Point snakeHead = new Point(0, 0);

	public static class Cmd {
		int timer;
		char dir;

		public Cmd(int timer, char dir) {
			this.timer = timer;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st;

		snake = new LinkedList<Point>();
		snake.add(new Point(0, 0));

		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());

		map = new int[N][N];

		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				map[y][x] = 0;
			}
		}

		map[0][0] = 2;

		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine(), " ");

			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;

			map[y][x] = 1;
		}

		L = Integer.parseInt(br.readLine());

		Queue<Cmd> queue = new LinkedList<Cmd>();

		for (int l = 0; l < L; l++) {
			st = new StringTokenizer(br.readLine(), " ");

			int X = Integer.parseInt(st.nextToken());
			char C = st.nextToken().charAt(0);

			queue.add(new Cmd(X, C));
		}

		int timer = 0;

		while (moveSnake()) {
			timer += 1;

			if (!queue.isEmpty() && timer == queue.peek().timer) {
				turnSnake(queue.poll().dir);
			}
		}

		bw.write(String.valueOf(timer + 1));

		bw.flush();
		bw.close();
		br.close();
	}

	public static void turnSnake(char Dir) {
		if (Dir == 'L') {
			snakeDir -= 1;
			if (snakeDir < 0) {
				snakeDir = 3;
			}
		} else {
			snakeDir += 1;
			if (snakeDir > 3) {
				snakeDir = 0;
			}
		}

		/*
		 * if (snakeDir == 0) { System.out.println("방향 변경: 우"); } else if (snakeDir ==
		 * 1) { System.out.println("방향 변경: 하"); } else if (snakeDir == 2) {
		 * System.out.println("방향 변경: 좌"); } else if (snakeDir == 3) {
		 * System.out.println("방향 변경: 상"); }
		 */

		return;
	}

	// 뱀이 해당 방향으로 이동이 가능한지를 반환한다.
	public static boolean moveSnake() {
		// printMap();
		// System.out.printf("y(%d) + %d\nx(%d) + %d\n", snakeHead.y, dirY[snakeDir],
		// snakeHead.x, dirX[snakeDir]);

		int tempY = snakeHead.y + dirY[snakeDir];
		int tempX = snakeHead.x + dirX[snakeDir];

		// System.out.printf("(y, x) -> (%d, %d)\n", tempY, tempX);

		if (visitable(tempY, tempX)) {
			snake.add(new Point(tempX, tempY));
			snakeHead = new Point(tempX, tempY);

			if (map[tempY][tempX] == 0) {
				Point snakeTail = snake.poll();
				map[snakeTail.y][snakeTail.x] = 0;
			}

			map[tempY][tempX] = 2;
		} else {
			return false;
		}

		return true;
	}

	public static boolean visitable(int y, int x) {
		return (0 <= y) && (y < N) && (0 <= x) && (x < N) && (map[y][x] != 2);
	}

	public static void printMap() {
		System.out.println();

		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				System.out.printf("%d ", map[y][x]);
			}
			System.out.println();
		}
	}
}