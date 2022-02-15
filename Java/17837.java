import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int N, K;
	public static Tile[][] map;
	public static ChessMan[] chessMan;

	// 우, 좌, 상, 하
	public static int[] dirY = { 0, 0, 0, -1, 1 };
	public static int[] dirX = { 0, 1, -1, 0, 0 };

	public static class Tile {
		int y, x, color;
		Stack<ChessMan> stack;

		public Tile(int y, int x, int color) {
			this.y = y;
			this.x = x;
			this.color = color;
			stack = new Stack<ChessMan>();
		}

		public int getSize() {
			return stack.size();
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < stack.size(); i++) {
				sb.append(stack.get(i).num + " ");
			}

			return "(y, x) = (" + y + ", " + x + ")\t" + sb.toString();
		}
	}

	public static class ChessMan {
		int y, x, dir, num;

		public ChessMan(int y, int x, int dir, int num) {
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.num = num;
		}

		public void move(int my, int mx) {
			// 이동하려는 칸이 흰색일 때
			if (map[my][mx].color == 0) {
				boolean state = false;
				Stack<ChessMan> stack = map[y][x].stack;
				Stack<ChessMan> mstack = map[my][mx].stack;

				ArrayList<ChessMan> list = new ArrayList<ChessMan>();

				while (!state) {
					ChessMan cm = stack.pop();

					if (cm.num == this.num) {
						state = true;
					}

					list.add(cm);
				}

				for (int i = list.size() - 1; i >= 0; i--) {
					ChessMan cm = list.get(i);

					cm.y = my;
					cm.x = mx;

					mstack.add(cm);
				}
			}

			// 이동하려는 칸이 빨간색일 때
			else if (map[my][mx].color == 1) {
				boolean state = false;

				Stack<ChessMan> stack = map[y][x].stack;
				Stack<ChessMan> mstack = map[my][mx].stack;

				while (!state) {
					ChessMan cm = stack.pop();

					if (cm.num == this.num) {
						state = true;
					}

					cm.y = my;
					cm.x = mx;

					mstack.add(cm);
				}
			}
		}

		// 이동 준비
		public void ready() {
			// 이동하려는 좌표
			int my, mx;

			my = y + dirY[dir];
			mx = x + dirX[dir];

			// 이동하려는 칸이 파란색일 때
			if (map[my][mx].color == 2) {
				this.reverse();

				my = y + dirY[dir];
				mx = x + dirX[dir];

				move(my, mx);
			} else {
				move(my, mx);
			}
		}

		public void reverse() {
			if (dir == 1) {
				dir = 2;
			} else if (dir == 2) {
				dir = 1;
			} else if (dir == 3) {
				dir = 4;
			} else if (dir == 4) {
				dir = 3;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new Tile[N + 2][N + 2];
		chessMan = new ChessMan[K + 1];

		for (int y = 0; y <= N + 1; y++) {
			if (1 <= y && y <= N) {
				st = new StringTokenizer(br.readLine(), " ");
			}

			for (int x = 0; x <= N + 1; x++) {
				if (y == 0 || x == 0 || y == N + 1 || x == N + 1) {
					map[y][x] = new Tile(y, x, 2);
					continue;
				}

				map[y][x] = new Tile(y, x, Integer.parseInt(st.nextToken()));
			}
		}

		for (int k = 1; k <= K; k++) {
			st = new StringTokenizer(br.readLine(), " ");

			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());

			chessMan[k] = new ChessMan(y, x, dir, k);
			map[y][x].stack.add(chessMan[k]);
		}

		// printMap();

		boolean state = false;

		Loop: for (int i = 1; i <= 1000; i++) {
			for (int k = 1; k <= K; k++) {
				chessMan[k].ready();
				// printMap();

				int cy, cx;

				cx = chessMan[k].x;
				cy = chessMan[k].y;

				// System.out.printf("%s\n", map[cy][cx]);

				if (map[cy][cx].getSize() >= 4) {
					bw.write(String.valueOf(i));
					state = true;
					break Loop;
				}
			}
		}

		if (!state) {
			bw.write("-1");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static void printMap() {
		System.out.println();

		for (int y = 0; y <= N + 1; y++) {
			for (int x = 0; x <= N + 1; x++) {
				if (map[y][x].color == 2) {
					System.out.printf("  ");
				} else {
					System.out.printf("%d ", map[y][x].color);
				}
			}

			System.out.printf("\t");

			for (int x = 0; x <= N + 1; x++) {
				if (map[y][x].color == 2) {
					System.out.printf("  ");
				} else {
					System.out.printf("%d ", map[y][x].getSize());
				}
			}
			System.out.println();
		}

		System.out.println();
	}
}