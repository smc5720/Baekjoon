import java.io.*;
import java.util.*;

public class Main {
	public static int N, M;
	public static char[][] map;
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	public static int[] dirY = { -1, 1, 0, 0 };
	public static int[] dirX = { 0, 0, -1, 1 };

	public static class Ball {
		int y, x;
		boolean isAlive, isRed;

		public Ball(int y, int x, boolean isRed, boolean isAlive) {
			setPos(y, x);
			this.isAlive = isAlive;
			this.isRed = isRed;
		}

		public void setPos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static class Condition {
		int cnt;
		Ball redBall, blueBall;

		public Condition(Ball redBall, Ball blueBall, int cnt) {
			this.redBall = redBall;
			this.blueBall = blueBall;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];

		Ball redBall = new Ball(0, 0, true, true);
		Ball blueBall = new Ball(0, 0, false, true);

		for (int y = 0; y < N; y++) {
			String str = br.readLine();
			for (int x = 0; x < M; x++) {
				map[y][x] = str.charAt(x);
				if (map[y][x] == 'R') {
					map[y][x] = '.';
					redBall.setPos(y, x);
				} else if (map[y][x] == 'B') {
					map[y][x] = '.';
					blueBall.setPos(y, x);
				}
			}
		}

		int answer = BFS(redBall, blueBall);
		bw.write(String.valueOf(answer));

		bw.flush();
		bw.close();
		br.close();
	}

	public static int BFS(Ball redBall, Ball blueBall) {
		Queue<Condition> queue = new LinkedList<Condition>();
		queue.add(new Condition(redBall, blueBall, 0));

		while (!queue.isEmpty() && queue.peek().cnt < 10) {
			Condition cond = queue.poll();

			for (int i = 0; i < 4; i++) {
				Ball rb, bb;

				if (i == UP) {
					// 더 위에 있는 공이 먼저 움직여야 한다.
					if (cond.redBall.y < cond.blueBall.y) {
						rb = moveBall(cond.redBall, i, cond.blueBall);
						bb = moveBall(cond.blueBall, i, rb);
					} else {
						bb = moveBall(cond.blueBall, i, cond.redBall);
						rb = moveBall(cond.redBall, i, bb);
					}
				} else if (i == DOWN) {
					// 더 아래에 있는 공이 먼저 움직여야 한다.
					if (cond.redBall.y > cond.blueBall.y) {
						rb = moveBall(cond.redBall, i, cond.blueBall);
						bb = moveBall(cond.blueBall, i, rb);
					} else {
						bb = moveBall(cond.blueBall, i, cond.redBall);
						rb = moveBall(cond.redBall, i, bb);
					}
				} else if (i == LEFT) {
					// 더 왼쪽에 있는 공이 먼저 움직여야 한다.
					if (cond.redBall.x < cond.blueBall.x) {
						rb = moveBall(cond.redBall, i, cond.blueBall);
						bb = moveBall(cond.blueBall, i, rb);
					} else {
						bb = moveBall(cond.blueBall, i, cond.redBall);
						rb = moveBall(cond.redBall, i, bb);
					}
				} else {
					// 더 오른쪽에 있는 공이 먼저 움직여야 한다.
					if (cond.redBall.x > cond.blueBall.x) {
						rb = moveBall(cond.redBall, i, cond.blueBall);
						bb = moveBall(cond.blueBall, i, rb);
					} else {
						bb = moveBall(cond.blueBall, i, cond.redBall);
						rb = moveBall(cond.redBall, i, bb);
					}
				}

				// printBoard(rb, bb);

				if (checkBall(cond.redBall, rb) || checkBall(cond.blueBall, bb)) {
					if (bb.isAlive) {
						// 파란 구슬은 살았는데
						if (!rb.isAlive) {
							// 빨간 구슬이 빠진 경우
							return cond.cnt + 1;
						} else {
							// 빨간 구슬도 산 경우
							queue.add(new Condition(rb, bb, cond.cnt + 1));
						}
					}
				}
			}
		}

		return -1;
	}

	public static Ball moveBall(Ball ball, int dir, Ball other) {
		int y = ball.y;
		int x = ball.x;

		while (map[y][x] == '.' || map[y][x] == 'O') {
			if (map[y][x] == 'O') {
				return new Ball(y, x, ball.isRed, false);
			}

			y += dirY[dir];
			x += dirX[dir];

			if (map[y][x] == '#' || (y == other.y && x == other.x && other.isAlive)) {
				y -= dirY[dir];
				x -= dirX[dir];
				break;
			}
		}

		return new Ball(y, x, ball.isRed, true);
	}

	// 볼이 움직였는지 확인한다.
	public static Boolean checkBall(Ball b1, Ball b2) {
		return (b1.y != b2.y) || ((b1.x != b2.x));
	}

	public static void printBoard(Ball red, Ball blue) {
		System.out.println();
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				if (red.y == y && red.x == x && red.isAlive) {
					System.out.printf("R", map[y][x]);
				} else if (blue.y == y && blue.x == x && blue.isAlive) {
					System.out.printf("B", map[y][x]);
				} else {
					System.out.printf("%c", map[y][x]);
				}
			}
			System.out.println();
		}
	}
}