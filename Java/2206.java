import java.io.*;
import java.util.*;

public class Main {
	public static int N, M;
	public static int[][] map;
	public static int[] dirY = { 0, 0, -1, 1 };
	public static int[] dirX = { -1, 1, 0, 0 };
	public static boolean[][][] visited;

	public static class Way {
		// 현재 좌표
		int y, x;
		// 벽을 부술 수 있는 기술 사용 여부
		boolean skill;
		// 움직인 거리
		int moves;

		public Way(int y, int x, boolean skill, int moves) {
			this.y = y;
			this.x = x;
			this.skill = skill;
			this.moves = moves;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1][2];

		for (int y = 1; y <= N; y++) {
			String str = br.readLine();
			for (int x = 1; x <= M; x++) {
				map[y][x] = str.charAt(x - 1) - '0';
				visited[y][x][0] = false;
				visited[y][x][1] = false;
			}
		}

		bw.write(String.valueOf(BFS()));

		bw.flush();
		bw.close();
		br.close();
	}

	public static int BFS() {
		Queue<Way> queue = new LinkedList<Way>();

		Way start = new Way(1, 1, false, 1);
		visited[1][1][0] = true;
		visited[1][1][1] = true;

		queue.add(start);

		while (!queue.isEmpty()) {
			Way way = queue.poll();

			if (way.y == N && way.x == M) {
				return way.moves;
			}

			for (int i = 0; i < 4; i++) {
				int tmpY = way.y + dirY[i];
				int tmpX = way.x + dirX[i];

				// 방문할 수 있는 좌표인가?
				if (visitable(tmpY, tmpX)) {
					if (!way.skill) {
						// 벽을 부술 수 있다면
						if (map[tmpY][tmpX] == 0) {
							// 벽이 아니라면
							if (!visited[tmpY][tmpX][0]) {
								Way newWay = new Way(tmpY, tmpX, false, way.moves + 1);
								visited[tmpY][tmpX][0] = true;
								queue.add(newWay);
							}
						} else {
							// 벽이라면
							if (!visited[tmpY][tmpX][1]) {
								Way newWay = new Way(tmpY, tmpX, true, way.moves + 1);
								visited[tmpY][tmpX][1] = true;
								queue.add(newWay);
							}
						}
					} else {
						// 벽을 부술 수 없다면
						if (map[tmpY][tmpX] != 1) {
							// 벽이 아닐 경우에만 통과 가능
							if (!visited[tmpY][tmpX][1]) {
								Way newWay = new Way(tmpY, tmpX, true, way.moves + 1);
								visited[tmpY][tmpX][1] = true;
								queue.add(newWay);
							}
						}
					}
				}
			}
		}

		return -1;
	}

	public static boolean visitable(int y, int x) {
		return (1 <= y) && (y <= N) && (1 <= x) && (x <= M);
	}

	public static void printMap() {
		System.out.println();

		for (int y = 1; y <= N; y++) {
			for (int x = 1; x <= M; x++) {
				System.out.printf("%d ", map[y][x]);
			}
			System.out.println();
		}
	}
}