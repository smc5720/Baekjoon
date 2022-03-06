import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int[][] map;
	public static int N, Q;
	public static int mLen;
	public static int[] dirY = { -1, 1, 0, 0 };
	public static int[] dirX = { 0, 0, -1, 1 };
	public static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		mLen = (int) (Math.pow(2, N));
		map = new int[mLen][mLen];
		visited = new boolean[mLen][mLen];

		for (int r = 0; r < mLen; r++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int c = 0; c < mLen; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				visited[r][c] = false;
			}
		}

		st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < Q; i++) {
			int L = Integer.parseInt(st.nextToken());

			// 작은 사각형 한 변의 크기
			int sqSize = (int) (Math.pow(2, L));

			// 작은 사각형 개수
			int sq = (mLen * mLen) / (sqSize * sqSize);

			int sqLen = (int) (Math.sqrt(sq));

			for (int j = 0; j < sq; j++) {
				int sy, sx, dy, dx;

				sy = j / sqLen * sqSize;
				sx = j % sqLen * sqSize;
				dy = sy + (sqSize - 1);
				dx = sx + (sqSize - 1);

				rotate(sy, sx, dy, dx, sqSize);
			}

			// System.out.println("map을 회전합니다.");
			// printMap();

			// System.out.println("얼음이 녹습니다.");
			melt();
			// printMap();
		}

		int answer = 0;

		for (int r = 0; r < mLen; r++) {
			for (int c = 0; c < mLen; c++) {
				if (!visited[r][c] && map[r][c] > 0) {
					answer = Math.max(answer, getIceSize(r, c));
				}
			}
		}

		bw.write(String.valueOf(getTotalIce()));
		bw.newLine();
		bw.write(String.valueOf(answer));

		bw.flush();
		br.close();
		bw.close();
	}

	public static void rotate(int sy, int sx, int dy, int dx, int L) {
		for (int i = 0; i < L / 2; i++) {
			int ly, lx, ry, rx, l;
			int[] arr;

			l = L - i * 2;
			arr = new int[l - 1];

			ly = sy + i;
			lx = sx + i;
			ry = ly + (l - 1);
			rx = lx + (l - 1);

			int idx = 0;

			for (int y = ly; y < ry; y++) {
				arr[idx] = map[y][lx];
				idx += 1;
			}

			idx = lx;

			for (int y = ly; y < ry; y++) {
				map[y][lx] = map[ry][idx];
				idx += 1;
			}

			idx = ry;

			for (int x = lx; x < rx; x++) {
				map[ry][x] = map[idx][rx];
				idx -= 1;
			}

			idx = rx;

			for (int y = ry; y > ly; y--) {
				map[y][rx] = map[ly][idx];
				idx -= 1;
			}

			idx = 0;

			for (int x = rx; x > lx; x--) {
				map[ly][x] = arr[idx];
				idx += 1;
			}
		}
	}

	public static int checkIce(int y, int x) {
		int cnt = 0;

		for (int i = 0; i < 4; i++) {
			int ty = y + dirY[i];
			int tx = x + dirX[i];

			if (visitable(ty, tx)) {
				cnt += 1;
			}
		}

		return cnt;
	}

	public static boolean visitable(int y, int x) {
		return (0 <= y) && (y < mLen) && (0 <= x) && (x < mLen) && (map[y][x] > 0);
	}

	public static void melt() {
		ArrayList<Point> list = new ArrayList<Point>();

		for (int r = 0; r < mLen; r++) {
			for (int c = 0; c < mLen; c++) {
				if (map[r][c] > 0 && checkIce(r, c) < 3) {
					Point p = new Point(c, r);
					list.add(p);
				}
			}
		}

		for (Point p : list) {
			map[p.y][p.x] -= 1;
		}
	}

	public static int getTotalIce() {
		int total = 0;

		for (int r = 0; r < mLen; r++) {
			for (int c = 0; c < mLen; c++) {
				total += map[r][c];
			}
		}

		return total;
	}

	public static int getIceSize(int y, int x) {
		int size = 0;

		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(x, y));

		visited[y][x] = true;

		while (!queue.isEmpty()) {
			Point p = queue.poll();
			size += 1;

			for (int i = 0; i < 4; i++) {
				int ty = p.y + dirY[i];
				int tx = p.x + dirX[i];

				if (visitable(ty, tx) && !visited[ty][tx]) {
					visited[ty][tx] = true;
					queue.add(new Point(tx, ty));
				}
			}
		}

		return size;
	}

	public static void printMap() {
		for (int r = 0; r < mLen; r++) {
			for (int c = 0; c < mLen; c++) {
				if (map[r][c] > 0) {
					System.out.printf("%d ", map[r][c]);
				} else {
					System.out.printf("  ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void printSquare(int sy, int sx, int dy, int dx) {
		for (int y = sy; y <= dy; y++) {
			for (int x = sx; x <= dx; x++) {
				System.out.printf("%d ", map[y][x]);
			}
			System.out.println();
		}
		System.out.println();
	}
}