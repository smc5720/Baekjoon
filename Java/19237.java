import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int N, M, k;
	public static Tile[][] tile;
	public static Shark[] sharks;
	public static int[] dirY = { 0, -1, 1, 0, 0 };
	public static int[] dirX = { 0, 0, 0, -1, 1 };

	public static class Tile {
		int y, x, smell, count, shark;

		public Tile(int y, int x) {
			this.y = y;
			this.x = x;
		}

		public void setSmell(int s) {
			count = k;
			smell = s;
		}

		public void spendTime() {
			if (smell != 0) {
				count -= 1;

				if (count == 0) {
					smell = 0;
				}
			}
		}
	}

	public static class Shark {
		int y, x, n, dir;
		int[][] d;
		boolean alive;

		public Shark(int y, int x, int n) {
			this.y = y;
			this.x = x;
			this.n = n;
			d = new int[5][4];
			alive = true;
		}

		public void setD(int dir, int[] d) {
			for (int i = 0; i < 4; i++) {
				this.d[dir][i] = d[i];
			}
		}

		// 현재 구역에 냄새를 살포한다.
		public void spread() {
			tile[y][x].setSmell(n);
		}

		// 상어가 이동한다.
		public void move() {
			// System.out.printf("\n%d번 상어(%d 방향) 이동\n", n, dir);
			boolean state = false;

			int ty = 0;
			int tx = 0;

			for (int i = 0; i < 4; i++) {
				int nd = d[dir][i];
				// System.out.printf("\n현 방향: %d\n", d[dir][i]);

				ty = y + dirY[nd];
				tx = x + dirX[nd];

				if (!visitable(ty, tx)) {
					continue;
				}

				if (tile[ty][tx].smell == 0) {
					state = true;
					dir = nd;
					break;
				}
			}

			if (!state) {
				for (int i = 0; i < 4; i++) {
					int nd = d[dir][i];
					ty = y + dirY[nd];
					tx = x + dirX[nd];

					if (!visitable(ty, tx)) {
						continue;
					}

					if (tile[ty][tx].smell == n) {
						dir = nd;
						break;
					}
				}
			}

			tile[y][x].shark = 0;

			if (tile[ty][tx].shark == 0) {
				y = ty;
				x = tx;
				tile[ty][tx].shark = n;
			} else {
				if (tile[ty][tx].shark < n) {
					// System.out.printf("\n%d번 상어 사망\n", n);
					alive = false;
				} else {
					System.out.println("Error: 발생하면 안되는 케이스");
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		tile = new Tile[N + 1][N + 1];
		sharks = new Shark[M + 1];

		for (int y = 1; y <= N; y++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int x = 1; x <= N; x++) {
				tile[y][x] = new Tile(y, x);

				int s = Integer.parseInt(st.nextToken());
				tile[y][x].shark = s;

				if (s != 0) {
					sharks[s] = new Shark(y, x, s);
				}
			}
		}

		st = new StringTokenizer(br.readLine(), " ");

		for (int m = 1; m <= M; m++) {
			sharks[m].dir = Integer.parseInt(st.nextToken());
		}

		for (int m = 1; m <= M; m++) {
			for (int i = 1; i <= 4; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int[] arr = new int[4];

				for (int j = 0; j < 4; j++) {
					arr[j] = Integer.parseInt(st.nextToken());
				}

				sharks[m].setD(i, arr);
			}
		}

		int answer = -1;

		spreadSmell();

		for (int i = 1; i <= 1000; i++) {
			// printTile();
			moveSharks();
			spendSecond();
			spreadSmell();

			if (countSharks() == 1) {
				answer = i;
				break;
			}
		}

		bw.write(String.valueOf(answer));

		bw.flush();
		br.close();
		bw.close();
	}

	public static boolean visitable(int y, int x) {
		return (1 <= y) && (y <= N) && (1 <= x) && (x <= N);
	}

	public static void spreadSmell() {
		for (int i = 1; i <= M; i++) {
			if (sharks[i].alive) {
				sharks[i].spread();
			}
		}
	}

	public static void spendSecond() {
		for (int y = 1; y <= N; y++) {
			for (int x = 1; x <= N; x++) {
				tile[y][x].spendTime();
			}
		}
	}

	public static void moveSharks() {
		for (int i = 1; i <= M; i++) {
			if (sharks[i].alive) {
				sharks[i].move();
			}
		}
	}

	public static int countSharks() {
		int cnt = 0;

		for (int i = 1; i <= M; i++) {
			if (sharks[i].alive) {
				cnt += 1;
			}
		}

		return cnt;
	}

	public static void printTile() {
		System.out.println();

		for (int y = 1; y <= N; y++) {
			for (int x = 1; x <= N; x++) {
				System.out.printf("%d ", tile[y][x].smell);
			}
			System.out.println();
		}
		System.out.println();
	}
}