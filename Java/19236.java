import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static Fish[] fishes;
	public static int[][] map;
	public static int[] dirY = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	public static int[] dirX = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	public static int answer;

	public static class Fish {
		int y, x, num, dir;
		boolean alive;

		public Fish(int y, int x, int num, int dir, boolean alive) {
			this.y = y;
			this.x = x;
			this.num = num;
			this.dir = dir;
			this.alive = alive;
		}

		public void rotate() {
			dir += 1;

			if (dir == 9) {
				dir = 1;
			}
		}

		public void move() {
			// System.out.printf("물고기 %d 이동\n", num);

			for (int i = 0; i < 8; i++) {
				int tmpY = y + dirY[dir];
				int tmpX = x + dirX[dir];

				if (visitable(tmpY, tmpX)) {
					if (map[tmpY][tmpX] != 0) {
						swap(tmpY, tmpX);
					} else {
						map[tmpY][tmpX] = num;
						map[y][x] = 0;
						y = tmpY;
						x = tmpX;
					}

					break;
				} else {
					rotate();
				}
			}

			// printMap();
		}

		public void swap(int dy, int dx) {
			int tmpNum = map[dy][dx];

			map[dy][dx] = num;
			map[y][x] = tmpNum;

			int tmpY = dy;
			int tmpX = dx;

			fishes[tmpNum].y = y;
			fishes[tmpNum].x = x;

			y = tmpY;
			x = tmpX;
		}

		public boolean visitable(int y, int x) {
			return (0 <= y) && (y < 4) && (0 <= x) && (x < 4) && (map[y][x] != 17);
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		fishes = new Fish[17];
		map = new int[4][4];
		answer = 0;

		for (int y = 0; y < 4; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			for (int x = 0; x < 4; x++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				map[y][x] = a;
				fishes[a] = new Fish(y, x, a, b, true);
			}
		}

		int sy = 0;
		int sx = 0;
		int sd = fishes[map[0][0]].dir;
		int eat = map[0][0];

		fishes[map[0][0]].alive = false;
		map[0][0] = 17;

		DFS(sy, sx, sd, eat);

		bw.write(String.valueOf(answer));

		bw.flush();
		br.close();
		bw.close();
	}

	public static void fishesMove() {
		for (int i = 1; i <= 16; i++) {
			if (fishes[i].alive) {
				fishes[i].move();
			}
		}
	}

	public static void DFS(int sy, int sx, int sd, int eat) {
		answer = Math.max(answer, eat);

		int[][] tmpMap = new int[4][4];

		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				tmpMap[y][x] = map[y][x];
			}
		}

		Fish[] tmpFishes = new Fish[17];

		for (int i = 1; i <= 16; i++) {
			tmpFishes[i] = new Fish(fishes[i].y, fishes[i].x, fishes[i].num, fishes[i].dir, fishes[i].alive);
		}

		fishesMove();

		for (int i = 1; i <= 3; i++) {
			int tmpY = sy + dirY[sd] * i;
			int tmpX = sx + dirX[sd] * i;

			if ((0 <= tmpY) && (tmpY < 4) && (0 <= tmpX) && (tmpX < 4) && (0 < map[tmpY][tmpX])
					&& (map[tmpY][tmpX] < 17)) {
				// System.out.println("상어 이동 전");
				// printMap();

				int fishN = map[tmpY][tmpX];
				int tmpDir = fishes[fishN].dir;

				map[sy][sx] = 0;
				map[tmpY][tmpX] = 17;
				fishes[fishN].alive = false;

				// System.out.printf("상어 %d번째 이동 후\n", depth);
				// printMap();

				DFS(tmpY, tmpX, tmpDir, eat + fishN);

				fishes[fishN].alive = true;
				map[tmpY][tmpX] = fishN;
				map[sy][sx] = 17;
			}
		}

		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				map[y][x] = tmpMap[y][x];
			}
		}

		for (int j = 1; j <= 16; j++) {
			fishes[j] = new Fish(tmpFishes[j].y, tmpFishes[j].x, tmpFishes[j].num, tmpFishes[j].dir,
					tmpFishes[j].alive);
		}
	}

	public static void printMap(int sd) {
		System.out.println();

		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (map[y][x] == 0) {
					System.out.printf(" \t", map[y][x]);
				} else if (map[y][x] == 17) {
					System.out.printf("상어\t", map[y][x]);
				} else {
					if (!fishes[map[y][x]].alive) {
						System.out.printf(" \t", map[y][x]);
					} else {
						System.out.printf("%d\t", map[y][x]);
					}
				}
			}

			System.out.printf("\t\t");

			for (int x = 0; x < 4; x++) {
				int dir;

				if (map[y][x] == 17) {
					dir = sd;
				} else if (map[y][x] == 0) {
					dir = 0;
				} else {
					dir = fishes[map[y][x]].dir;
				}

				switch (dir) {
				case 1:
					System.out.printf("↑\t");
					break;
				case 2:
					System.out.printf("↖\t");
					break;
				case 3:
					System.out.printf("←\t");
					break;
				case 4:
					System.out.printf("↙\t");
					break;
				case 5:
					System.out.printf("↓\t");
					break;
				case 6:
					System.out.printf("↘\t");
					break;
				case 7:
					System.out.printf("→\t");
					break;
				case 8:
					System.out.printf("↗\t");
					break;
				default:
					System.out.printf("*\t");
					break;
				}
			}

			System.out.println();
		}

		System.out.println();
	}
}