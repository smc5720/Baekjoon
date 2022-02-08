import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int R, C, T, answer;
	public static AirCleaner airCleaner;
	public static boolean[][] visited;
	public static Dust[][] map;
	public static ArrayList<Dust> dusts;

	public static class AirCleaner {
		int upY, upX, downY, downX;

		public AirCleaner(int upY, int upX, int downY, int downX) {
			this.upY = upY;
			this.upX = upX;
			this.downY = downY;
			this.downX = downX;
		}
	}

	public static class Dust {
		int y, x;
		int dust, addDust;

		public Dust(int y, int x, int dust) {
			this.y = y;
			this.x = x;
			this.dust = dust;
			this.addDust = 0;
		}

		public void calDust() {
			dust += addDust;
			addDust = 0;
		}

		public void sync() {
			map[this.y][this.x] = this;
		}

		public void move() {
			// 위쪽 공기는 반시계 방향으로 순환
			if (this.y <= airCleaner.upY) {
				// 아래로 이동
				if (this.x == 1) {
					if (this.y < airCleaner.upY) {
						this.y += 1;
					} else {
						this.x += 1;
					}
				}

				// 위로 이동
				else if (this.x == C) {
					if (this.y > 1) {
						this.y -= 1;
					} else {
						this.x -= 1;
					}
				}

				// 왼쪽으로 이동
				else if (this.y == 1) {
					if (this.x > 1) {
						this.x -= 1;
					} else {
						this.y += 1;
					}
				}

				// 오른쪽으로 이동
				else if (this.y == airCleaner.upY) {
					if (this.x < C) {
						this.x += 1;
					} else {
						this.y -= 1;
					}
				}
			}

			// 아래쪽 공기는 시계 방향으로 순환
			else {
				// 위로 이동
				if (this.x == 1) {
					if (this.y > airCleaner.downY) {
						this.y -= 1;
					} else {
						this.x += 1;
					}
				}

				// 아래로 이동
				else if (this.x == C) {
					if (this.y < R) {
						this.y += 1;
					} else {
						this.x -= 1;
					}
				}

				// 오른쪽으로 이동
				else if (this.y == airCleaner.downY) {
					if (this.x < C) {
						this.x += 1;
					} else {
						this.y += 1;
					}
				}

				// 왼쪽으로 이동
				else if (this.y == R) {
					if (this.x > 1) {
						this.x -= 1;
					} else {
						this.y -= 1;
					}
				}
			}

			if (isAirCleaner(this.y, this.x)) {
				this.dust = 0;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new Dust[R + 1][C + 1];
		airCleaner = new AirCleaner(0, 0, 0, 0);
		dusts = new ArrayList<Dust>();
		answer = 0;

		for (int y = 1; y <= R; y++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int x = 1; x <= C; x++) {
				map[y][x] = new Dust(y, x, Integer.parseInt(st.nextToken()));

				if (map[y][x].dust == -1) {
					if (airCleaner.upX == 0) {
						airCleaner = new AirCleaner(y, x, y + 1, x);
					}
					map[y][x].dust = 0;
				}

				dusts.add(map[y][x]);
			}
		}

		// printMap();

		for (int t = 0; t < T; t++) {
			spread();
			// printMap();
			moveDust();
			// printMap();
		}

		for (Dust d : dusts) {
			answer += d.dust;
		}

		bw.write(String.valueOf(answer));

		bw.flush();
		bw.close();
		br.close();
	}

	public static boolean visitable(int y, int x) {
		return (1 <= y) && (y <= R) && (1 <= x) && (x <= C) && !isAirCleaner(y, x);
	}

	public static boolean isAirCleaner(int y, int x) {
		return ((y == airCleaner.upY) && (x == airCleaner.upX)) || ((y == airCleaner.downY) && (x == airCleaner.downX));
	}

	// 미세먼지가 확산된다.
	public static void spread() {
		Queue<Dust> queue = new LinkedList<Dust>();

		int[] dirY = { 0, 0, -1, 1 };
		int[] dirX = { -1, 1, 0, 0 };

		for (int y = 1; y <= R; y++) {
			for (int x = 1; x <= C; x++) {
				if (map[y][x].dust > 0) {
					queue.add(map[y][x]);
				}
			}
		}

		while (!queue.isEmpty()) {
			Dust d = queue.poll();

			// 확산되는 먼지
			int t = d.dust / 5;
			int cnt = 0;

			for (int i = 0; i < 4; i++) {
				int tmpY = d.y + dirY[i];
				int tmpX = d.x + dirX[i];

				if (visitable(tmpY, tmpX)) {
					cnt += 1;
					map[tmpY][tmpX].addDust += t;
				}
			}

			d.dust = d.dust - t * cnt;
		}

		for (int y = 1; y <= R; y++) {
			for (int x = 1; x <= C; x++) {
				map[y][x].calDust();
			}
		}
	}

	// 공기가 순환한다.
	public static void moveDust() {
		for (Dust d : dusts) {
			d.move();
			d.sync();
		}
	}

	public static void printMap() {
		System.out.println();

		for (int y = 1; y <= R; y++) {
			for (int x = 1; x <= C; x++) {
				if (((airCleaner.upY == y) && (airCleaner.upX == x))
						|| ((airCleaner.downY == y) && (airCleaner.downX == x))) {
					System.out.printf("* ", map[y][x].dust);
				} else {
					System.out.printf("%d ", map[y][x].dust);
				}
			}
			System.out.println();
		}

		System.out.println();
	}
}