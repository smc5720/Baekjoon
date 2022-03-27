import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int R, C, K, W;
	public static int[][] map;
	public static ArrayList<Point> checkPos;
	public static final int RIGHT = 1;
	public static final int LEFT = 2;
	public static final int UP = 3;
	public static final int DOWN = 4;
	public static int[] dirX = { 0, 0, -1, 1 };
	public static int[] dirY = { -1, 1, 0, 0 };
	public static int[][] dx = { { 0, 0, 0 }, { -1, 0, 1 }, { -1, 0, 1 }, { -1, -1, -1 }, { 1, 1, 1 } };
	public static int[][] dy = { { 0, 0, 0 }, { 1, 1, 1 }, { -1, -1, -1 }, { -1, 0, 1 }, { -1, 0, 1 } };
	public static ArrayList<Heater> heater;
	public static HashMap<Wall, Boolean> wall;

	public static class Heater {
		public int r, c, dir;
		private ArrayList<Point> heat5;
		private ArrayList<Point> heat4;
		private ArrayList<Point> heat3;
		private ArrayList<Point> heat2;
		private ArrayList<Point> heat1;

		public Heater(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.heat5 = new ArrayList<Point>();
			this.heat4 = new ArrayList<Point>();
			this.heat3 = new ArrayList<Point>();
			this.heat2 = new ArrayList<Point>();
			this.heat1 = new ArrayList<Point>();
		}

		public void initHeatSpot() {
			boolean[][] visited = new boolean[R + 1][C + 1];

			int tx = r + dx[dir][1];
			int ty = c + dy[dir][1];

			if (!visitable(tx, ty)) {
				return;
			}

			visited[tx][ty] = true;
			heat5.add(new Point(tx, ty));

			for (Point p : heat5) {
				for (int i = 0; i < 3; i++) {
					int tmx = p.x + dx[dir][i];
					int tmy = p.y + dy[dir][i];

					if (!visitable(tmx, tmy) || visited[tmx][tmy]) {
						continue;
					}

					int sx, sy, ex, ey;

					if (i == 1) {
						sx = p.x;
						sy = p.y;
						ex = tmx;
						ey = tmy;

						// System.out.printf("벽 체크: %d, %d, %d, %d\n", sx, sy, ex, ey, ex, ey, sx, sy);

						if (wall.containsKey(new Wall(sx, sy, ex, ey))) {
							continue;
						}
					} else {
						if (dir < 3) {
							sx = p.x;
							sy = p.y;
							ex = tmx;
							ey = p.y;

							// System.out.printf("벽 체크: %d, %d, %d, %d\n", sx, sy, ex, ey, ex, ey, sx, sy);

							if (wall.containsKey(new Wall(sx, sy, ex, ey))) {
								continue;
							}

							sx = tmx;
							sy = p.y;
							ex = tmx;
							ey = tmy;

							// System.out.printf("벽 체크: %d, %d, %d, %d\n", sx, sy, ex, ey, ex, ey, sx, sy);

							if (wall.containsKey(new Wall(sx, sy, ex, ey))) {
								continue;
							}
						} else {
							sx = p.x;
							sy = p.y;
							ex = p.x;
							ey = tmy;

							// System.out.printf("벽 체크: %d, %d, %d, %d\n", sx, sy, ex, ey, ex, ey, sx, sy);

							if (wall.containsKey(new Wall(sx, sy, ex, ey))) {
								continue;
							}

							sx = p.x;
							sy = tmy;
							ex = tmx;
							ey = tmy;

							// System.out.printf("벽 체크: %d, %d, %d, %d\n", sx, sy, ex, ey, ex, ey, sx, sy);

							if (wall.containsKey(new Wall(sx, sy, ex, ey))) {
								continue;
							}
						}
					}

					visited[tmx][tmy] = true;
					heat4.add(new Point(tmx, tmy));
				}
			}

			if (heat4.size() == 0) {
				return;
			}

			for (Point p : heat4) {
				for (int i = 0; i < 3; i++) {
					int tmx = p.x + dx[dir][i];
					int tmy = p.y + dy[dir][i];

					if (!visitable(tmx, tmy) || visited[tmx][tmy]) {
						continue;
					}

					int sx, sy, ex, ey;

					if (i == 1) {
						sx = p.x;
						sy = p.y;
						ex = tmx;
						ey = tmy;

						// System.out.printf("벽 체크: %d, %d, %d, %d\n", sx, sy, ex, ey, ex, ey, sx, sy);

						if (wall.containsKey(new Wall(sx, sy, ex, ey))) {
							continue;
						}
					} else {
						if (dir < 3) {
							sx = p.x;
							sy = p.y;
							ex = tmx;
							ey = p.y;

							// System.out.printf("벽 체크: %d, %d, %d, %d\n", sx, sy, ex, ey, ex, ey, sx, sy);

							if (wall.containsKey(new Wall(sx, sy, ex, ey))) {
								continue;
							}

							sx = tmx;
							sy = p.y;
							ex = tmx;
							ey = tmy;

							// System.out.printf("벽 체크: %d, %d, %d, %d\n", sx, sy, ex, ey, ex, ey, sx, sy);

							if (wall.containsKey(new Wall(sx, sy, ex, ey))) {
								continue;
							}
						} else {
							sx = p.x;
							sy = p.y;
							ex = p.x;
							ey = tmy;

							// System.out.printf("벽 체크: %d, %d, %d, %d\n", sx, sy, ex, ey, ex, ey, sx, sy);

							if (wall.containsKey(new Wall(sx, sy, ex, ey))) {
								continue;
							}

							sx = p.x;
							sy = tmy;
							ex = tmx;
							ey = tmy;

							// System.out.printf("벽 체크: %d, %d, %d, %d\n", sx, sy, ex, ey, ex, ey, sx, sy);

							if (wall.containsKey(new Wall(sx, sy, ex, ey))) {
								continue;
							}
						}
					}

					visited[tmx][tmy] = true;
					heat3.add(new Point(tmx, tmy));
				}
			}

			if (heat3.size() == 0) {
				return;
			}

			for (Point p : heat3) {
				for (int i = 0; i < 3; i++) {
					int tmx = p.x + dx[dir][i];
					int tmy = p.y + dy[dir][i];

					if (!visitable(tmx, tmy) || visited[tmx][tmy]) {
						continue;
					}

					int sx, sy, ex, ey;

					if (i == 1) {
						sx = p.x;
						sy = p.y;
						ex = tmx;
						ey = tmy;

						// System.out.printf("벽 체크: %d, %d, %d, %d\n", sx, sy, ex, ey, ex, ey, sx, sy);

						if (wall.containsKey(new Wall(sx, sy, ex, ey))) {
							continue;
						}
					} else {
						if (dir < 3) {
							sx = p.x;
							sy = p.y;
							ex = tmx;
							ey = p.y;

							// System.out.printf("벽 체크: %d, %d, %d, %d\n", sx, sy, ex, ey, ex, ey, sx, sy);

							if (wall.containsKey(new Wall(sx, sy, ex, ey))) {
								continue;
							}

							sx = tmx;
							sy = p.y;
							ex = tmx;
							ey = tmy;

							// System.out.printf("벽 체크: %d, %d, %d, %d\n", sx, sy, ex, ey, ex, ey, sx, sy);

							if (wall.containsKey(new Wall(sx, sy, ex, ey))) {
								continue;
							}
						} else {
							sx = p.x;
							sy = p.y;
							ex = p.x;
							ey = tmy;

							// System.out.printf("벽 체크: %d, %d, %d, %d\n", sx, sy, ex, ey, ex, ey, sx, sy);

							if (wall.containsKey(new Wall(sx, sy, ex, ey))) {
								continue;
							}

							sx = p.x;
							sy = tmy;
							ex = tmx;
							ey = tmy;

							// System.out.printf("벽 체크: %d, %d, %d, %d\n", sx, sy, ex, ey, ex, ey, sx, sy);

							if (wall.containsKey(new Wall(sx, sy, ex, ey))) {
								continue;
							}
						}
					}

					visited[tmx][tmy] = true;
					heat2.add(new Point(tmx, tmy));
				}
			}

			if (heat2.size() == 0) {
				return;
			}

			for (Point p : heat2) {
				for (int i = 0; i < 3; i++) {
					int tmx = p.x + dx[dir][i];
					int tmy = p.y + dy[dir][i];

					if (!visitable(tmx, tmy) || visited[tmx][tmy]) {
						continue;
					}

					int sx, sy, ex, ey;

					if (i == 1) {
						sx = p.x;
						sy = p.y;
						ex = tmx;
						ey = tmy;

						// System.out.printf("벽 체크: %d, %d, %d, %d\n", sx, sy, ex, ey, ex, ey, sx, sy);

						if (wall.containsKey(new Wall(sx, sy, ex, ey))) {
							continue;
						}
					} else {
						if (dir < 3) {
							sx = p.x;
							sy = p.y;
							ex = tmx;
							ey = p.y;

							// System.out.printf("벽 체크: %d, %d, %d, %d\n", sx, sy, ex, ey, ex, ey, sx, sy);

							if (wall.containsKey(new Wall(sx, sy, ex, ey))) {
								continue;
							}

							sx = tmx;
							sy = p.y;
							ex = tmx;
							ey = tmy;

							// System.out.printf("벽 체크: %d, %d, %d, %d\n", sx, sy, ex, ey, ex, ey, sx, sy);

							if (wall.containsKey(new Wall(sx, sy, ex, ey))) {
								continue;
							}
						} else {
							sx = p.x;
							sy = p.y;
							ex = p.x;
							ey = tmy;

							// System.out.printf("벽 체크: %d, %d, %d, %d\n", sx, sy, ex, ey, ex, ey, sx, sy);

							if (wall.containsKey(new Wall(sx, sy, ex, ey))) {
								continue;
							}

							sx = p.x;
							sy = tmy;
							ex = tmx;
							ey = tmy;

							// System.out.printf("벽 체크: %d, %d, %d, %d\n", sx, sy, ex, ey, ex, ey, sx, sy);

							if (wall.containsKey(new Wall(sx, sy, ex, ey))) {
								continue;
							}
						}
					}

					visited[tmx][tmy] = true;
					heat1.add(new Point(tmx, tmy));
				}
			}
		}

		public void heat() {
			for (Point p : heat5) {
				map[p.x][p.y] += 5;
			}

			for (Point p : heat4) {
				map[p.x][p.y] += 4;
			}

			for (Point p : heat3) {
				map[p.x][p.y] += 3;
			}

			for (Point p : heat2) {
				map[p.x][p.y] += 2;
			}

			for (Point p : heat1) {
				map[p.x][p.y] += 1;
			}
		}
	}

	public static class Wall {
		public int sx, sy, ex, ey;

		public Wall(int sx, int sy, int ex, int ey) {
			this.sx = sx;
			this.sy = sy;
			this.ex = ex;
			this.ey = ey;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;

			Wall other = (Wall) o;

			return other.sx == sx && other.sy == sy && other.ex == ex && other.ey == ey;
		}

		@Override
		public int hashCode() {
			return Objects.hash(sx, sy, ex, ey);
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[R + 1][C + 1];

		checkPos = new ArrayList<Point>();
		heater = new ArrayList<Heater>();
		wall = new HashMap<Wall, Boolean>();

		for (int x = 1; x <= R; x++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int y = 1; y <= C; y++) {
				int n = Integer.parseInt(st.nextToken());

				if (n == 5) {
					checkPos.add(new Point(x, y));
				} else if (n > 0) {
					heater.add(new Heater(x, y, n));
				}

				map[x][y] = 0;
			}
		}

		W = Integer.parseInt(br.readLine());

		for (int i = 0; i < W; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			if (v == 0) {
				int ex = sx - 1;
				int ey = sy;
				wall.put(new Wall(sx, sy, ex, ey), true);
				wall.put(new Wall(ex, ey, sx, sy), true);
				// System.out.printf("벽 생성: %d, %d, %d, %d\t%d, %d, %d, %d\n", sx, sy, ex, ey,
				// ex, ey, sx, sy);
			} else {
				int ex = sx;
				int ey = sy + 1;
				wall.put(new Wall(sx, sy, ex, ey), true);
				wall.put(new Wall(ex, ey, sx, sy), true);
				// System.out.printf("벽 생성: %d, %d, %d, %d\t%d, %d, %d, %d\n", sx, sy, ex, ey,
				// ex, ey, sx, sy);
			}
		}

		int chocolate = 0;

		initHeatSpot();

		do {
			// System.out.println("온풍기 가동");
			HeatMap();
			// printMap();

			// System.out.println("온도 조절");
			controlHeat();

			// System.out.println("바깥 온도 1 감소");
			decreaseHeat();
			// printMap();

			chocolate += 1;
			// System.out.printf("초콜릿: %d → %d개\n", chocolate - 1, chocolate);
		} while (!checkHeat() && chocolate <= 100);

		bw.write(String.valueOf(chocolate));

		bw.flush();
		br.close();
		bw.close();
	}

	public static void printMap() {
		System.out.println();

		for (int x = 1; x <= R; x++) {
			for (int y = 1; y <= C; y++) {
				System.out.printf("%d ", map[x][y]);
			}
			System.out.println();
		}
		System.out.println();
	}

	public static boolean visitable(int x, int y) {
		return (1 <= x) && (x <= R) && (1 <= y) && (y <= C);
	}

	public static void initHeatSpot() {
		for (Heater htr : heater) {
			htr.initHeatSpot();
		}
	}

	public static void HeatMap() {
		for (Heater htr : heater) {
			htr.heat();
		}
	}

	public static void controlHeat() {
		boolean[][] visited = new boolean[R + 1][C + 1];
		int[][] tmp = new int[R + 1][C + 1];

		for (int x = 1; x <= R; x++) {
			for (int y = 1; y <= C; y++) {
				tmp[x][y] = map[x][y];
			}
		}

		for (int x = 1; x <= R; x++) {
			for (int y = 1; y <= C; y++) {
				if (!visited[x][y]) {
					visited[x][y] = true;

					for (int i = 0; i < 4; i++) {
						int tx = x + dirX[i];
						int ty = y + dirY[i];

						if (visitable(tx, ty) && !visited[tx][ty] && !wall.containsKey(new Wall(x, y, tx, ty))) {
							if (map[x][y] > map[tx][ty]) {
								int val = (map[x][y] - map[tx][ty]) / 4;
								tmp[x][y] -= val;
								tmp[tx][ty] += val;
							} else if (map[x][y] < map[tx][ty]) {
								int val = (map[tx][ty] - map[x][y]) / 4;
								tmp[x][y] += val;
								tmp[tx][ty] -= val;
							}
						}
					}
				}
			}
		}

		for (int x = 1; x <= R; x++) {
			for (int y = 1; y <= C; y++) {
				map[x][y] = tmp[x][y];
			}
		}
	}

	public static void decreaseHeat() {
		for (int x = 1; x <= R; x++) {
			for (int y = 1; y <= C; y++) {
				if (x == 1 || x == R) {
					if (map[x][y] > 0) {
						map[x][y] -= 1;
					}
					continue;
				}

				if (y == 1 || y == C) {
					if (map[x][y] > 0) {
						map[x][y] -= 1;
					}
					continue;
				}
			}
		}
	}

	public static boolean checkHeat() {
		for (Point p : checkPos) {
			if (map[p.x][p.y] < K) {
				return false;
			}
		}

		return true;
	}
}