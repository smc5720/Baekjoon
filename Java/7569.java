import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int M, N, H;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		Queue<Position> queue = new LinkedList<Position>();

		int[][][] tomato = new int[H][N][M];
		boolean[][][] visited = new boolean[H][N][M];

		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int m = 0; m < M; m++) {
					tomato[h][n][m] = Integer.parseInt(st.nextToken());
					visited[h][n][m] = false;

					if (tomato[h][n][m] == 1) {
						visited[h][n][m] = true;
						Position pos = new Position(m, n, h);
						queue.add(pos);
					}
				}
			}
		}

		int answer = Math.max(0, BFS(queue, tomato, visited) - 1);

		if (isClean(tomato)) {
			bw.write(String.valueOf(answer));
		} else {
			bw.write("-1");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static int BFS(Queue<Position> queue, int[][][] tomato, boolean[][][] visited) {
		int day = 0;

		while (!queue.isEmpty()) {
			Position pos = queue.poll();
			int x = pos.x;
			int y = pos.y;
			int h = pos.h;

			day = tomato[h][y][x];

			int[] dirX = { -1, 1, 0, 0, 0, 0 };
			int[] dirY = { 0, 0, -1, 1, 0, 0 };
			int[] dirH = { 0, 0, 0, 0, -1, 1 };

			for (int i = 0; i < 6; i++) {
				int nowX = x + dirX[i];
				int nowY = y + dirY[i];
				int nowH = h + dirH[i];

				if (isVisitable(nowX, nowY, nowH, visited)) {
					if (tomato[nowH][nowY][nowX] == 0) {
						visited[nowH][nowY][nowX] = true;
						tomato[nowH][nowY][nowX] = tomato[h][y][x] + 1;
						Position nowPos = new Position(nowX, nowY, nowH);
						queue.add(nowPos);
					}
				}
			}
		}

		return day;
	}

	public static boolean isVisitable(int x, int y, int h, boolean[][][] visited) {
		return (0 <= x) && (x < M) && (0 <= y) && (y < N) && (0 <= h) && (h < H) && !visited[h][y][x];
	}

	public static boolean isClean(int[][][] tomato) {
		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				for (int m = 0; m < M; m++) {
					if (tomato[h][n][m] == 0) {
						return false;
					}
				}
			}
		}

		return true;
	}

	static class Position {
		int x, y, h;

		public Position(int x, int y, int h) {
			this.x = x;
			this.y = y;
			this.h = h;
		}
	}
}