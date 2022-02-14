import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int N, M;
	public static Tile[][] A;
	public static int[] dy = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	public static int[] dx = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	public static Queue<Tile> queue;

	public static class Tile {
		int y, x, water, save;
		boolean disabled;

		public Tile(int y, int x, int water) {
			this.y = y;
			this.x = x;
			this.water = water;
			this.disabled = false;
		}

		public void magic() {
			int[] dirY = { -1, -1, 1, 1 };
			int[] dirX = { -1, 1, -1, 1 };

			for (int i = 0; i < 4; i++) {
				int r = y + dirY[i];
				int c = x + dirX[i];

				if (visitable(r, c) && A[r][c].water > 0) {
					save += 1;
				}
			}
		}

		public void save() {
			A[y][x].water += save;
			save = 0;
		}

		public boolean visitable(int r, int c) {
			return (1 <= r) && (r <= N) && (1 <= c) && (c <= N);
		}
	}

	public static class Cloud {
		int y, x, d, s;

		public Cloud(int n, int d, int s) {
			this.d = d;
			this.s = s;

			if (n == 0) {
				setPos(N, 1);
			} else if (n == 1) {
				setPos(N, 2);
			} else if (n == 2) {
				setPos(N - 1, 1);
			} else if (n == 3) {
				setPos(N - 1, 2);
			}
		}

		public void setPos(int y, int x) {
			this.y = y;
			this.x = x;
		}

		public void move() {
			// System.out.printf("이동하기 전: (%d, %d)\n", y, x);
			y = calculate(y + dy[d] * s);
			x = calculate(x + dx[d] * s);
			// System.out.printf("이동 후: (%d, %d)\n", y, x);
		}

		public void rain() {
			A[y][x].water += 1;
			queue.add(A[y][x]);
		}

		public int calculate(int n) {
			while (n < 0) {
				n += N;
			}

			n %= N;

			if (n == 0) {
				n = N;
			}

			return n;
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		A = new Tile[N + 1][N + 1];
		queue = new LinkedList<Tile>();

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 1; c <= N; c++) {
				A[r][c] = new Tile(r, c, Integer.parseInt(st.nextToken()));
			}
		}

		ArrayList<Cloud> clouds = new ArrayList<Cloud>();

		// 비구름이 생긴다.
		for (int j = 0; j < 4; j++) {
			clouds.add(new Cloud(j, 0, 0));
		}

		// printMap();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= N; c++) {
					A[r][c].disabled = false;
				}
			}

			for (Cloud cloud : clouds) {
				cloud.d = d;
				cloud.s = s;
			}

			int size = clouds.size();

			// 모든 구름이 d 방향으로 s칸 이동한다.
			for (int j = 0; j < size; j++) {
				clouds.get(j).move();
			}

			// 각 구름에서 비가 내린다.
			for (int j = 0; j < size; j++) {
				clouds.get(j).rain();
			}

			// 구름이 사라진 자리 마킹
			for (int j = 0; j < size; j++) {
				Cloud c = clouds.get(j);
				A[c.y][c.x].disabled = true;
			}

			// 구름이 모두 사라진다.
			clouds = new ArrayList<Cloud>();

			// 물복사버그 마법 시전
			for (int j = 0; j < queue.size(); j++) {
				Tile t = queue.poll();
				t.magic();
				queue.add(t);
			}

			while (!queue.isEmpty()) {
				Tile t = queue.poll();
				t.save();
			}

			// 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다.
			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= N; c++) {
					if (!A[r][c].disabled && A[r][c].water >= 2) {
						Cloud cl = new Cloud(4, 0, 0);
						cl.setPos(r, c);
						clouds.add(cl);
						A[r][c].water -= 2;
					}
				}
			}

			// printMap();
		}

		int answer = 0;

		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				answer += A[r][c].water;
			}
		}

		bw.write(String.valueOf(answer));

		bw.flush();
		bw.close();
		br.close();
	}

	public static void printMap() {
		System.out.println();

		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				System.out.printf("%d ", A[r][c].water);
			}
			System.out.println();
		}

		System.out.println();
	}
}