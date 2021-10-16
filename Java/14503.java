import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N, M;
	static int r, c, d;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 0: 공간 / 1: 벽
		int[][] map = new int[N][M];

		st = new StringTokenizer(br.readLine(), " ");

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		// 0: 북 / 1: 동 / 2: 남 / 3: 서
		d = Integer.parseInt(st.nextToken());

		Robot robot = new Robot(r, c, d);

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
			}
		}

		Queue<Robot> queue = new LinkedList<Robot>();
		queue.add(robot);

		cleaning(queue, map);
		bw.write(String.valueOf(countCleaning(map)));

		bw.flush();
		bw.close();
		br.close();
	}

	public static void cleaning(Queue<Robot> queue, int[][] map) {
		int[] dirY = { -1, 0, 1, 0 };
		int[] dirX = { 0, 1, 0, -1 };

		while (!queue.isEmpty()) {
			Robot robot = queue.poll();
			// 1. 현재 위치를 청소한다.
			robot.clean(map);
			// printMap(map);

			// 2. 현재 위치에서 현재 방향을 기준으로 왼쪽 방향부터 차례대로 인접한 칸을 탐색한다.
			for (int i = 0; i < 4; i++) {
				// 현 위치 기준에서 왼쪽 방향이 동서남북 중 어디인지 저장한다.
				int leftD = (robot.d + 3 - i) % 4;

				if (isVisitable(robot.r + dirY[leftD], robot.c + dirX[leftD], map, true)) {
					// a. 왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
					Robot nextRobot = new Robot(robot.r + dirY[leftD], robot.c + dirX[leftD], leftD);
					queue.add(nextRobot);
					break;
				}

				// b. 왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다.
			}

			if (!queue.isEmpty()) {
				continue;
			}

			// c. 네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
			int backD = (robot.d + 2) % 4;

			if (isVisitable(robot.r + dirY[backD], robot.c + dirX[backD], map, false)) {
				Robot nextRobot = new Robot(robot.r + dirY[backD], robot.c + dirX[backD], robot.d);
				queue.add(nextRobot);
				continue;
			}

			// d. 네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
		}
	}

	public static boolean isVisitable(int r, int c, int[][] map, boolean state) {
		// state가 true일 경우 청소 가능한 곳으로만 이동 가능하다.
		if (state == true) {
			return (0 <= r) && (r < N) && (0 <= c) && (c < M) && (map[r][c] == 0);
		}

		// state가 false일 경우 벽만 아니면 후진이 가능하다.
		else {
			return (0 <= r) && (r < N) && (0 <= c) && (c < M) && (map[r][c] != 1);
		}
	}

	public static void printMap(int[][] map) {
		System.out.println();
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (map[n][m] == 0) {
					System.out.printf("□ ");
				} else if (map[n][m] == 1) {
					System.out.printf("   ");
				} else {
					System.out.printf("■ ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	public static int countCleaning(int[][] map) {
		int num = 0;

		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (map[n][m] == 2) {
					num += 1;
				}
			}
		}

		return num;
	}

	public static class Robot {
		int r, c, d;

		public Robot(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

		// 현재 위치를 청소한다.
		public void clean(int[][] map) {
			map[r][c] = 2;
		}
	}
}