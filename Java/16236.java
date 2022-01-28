import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int[][] map;
	public static boolean[][] visited;
	public static int sharkSize = 2;
	public static Point sharkPos;
	public static int fishCount = 0;

	public static class Pos {
		int y, x, dist;

		public Pos(int y, int x, int dist) {
			this.y = y;
			this.x = x;
			this.dist = dist;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int j = 0; j < N; j++) {
				visited[i][j] = false;
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 9) {
					sharkPos = new Point(j, i);
				}
			}
		}

		int state = 0;
		int answer = 0;

		do {
			// printMap();
			state = getNearestFish();
			answer += state;
			// System.out.printf("%d초 소요\t현재까지 %d초\n", state, answer);
		} while (state != 0);

		bw.write(String.valueOf(answer));

		bw.flush();
		bw.close();
		br.close();
	}

	// 가장 가까운 '먹을 수 있는 물고기'까지의 거리를 반환한다.
	// 없다면 0을 반환한다.
	public static int getNearestFish() {
		resetVisited();

		Queue<Pos> queue = new LinkedList<Pos>();
		queue.add(new Pos(sharkPos.y, sharkPos.x, 0));
		visited[sharkPos.y][sharkPos.x] = true;

		int[] dirY = { -1, 0, 0, 1 };
		int[] dirX = { 0, -1, 1, 0 };

		PriorityQueue<Pos> pq = new PriorityQueue<Pos>(new Comparator<Pos>() {
			@Override
			public int compare(Pos o1, Pos o2) {
				if (o1.dist == o2.dist) {
					if (o1.y == o2.y) {
						return Integer.compare(o1.x, o2.x);
					}
					return Integer.compare(o1.y, o2.y);
				}
				return Integer.compare(o1.dist, o2.dist);
			}
		});

		while (!queue.isEmpty()) {
			Pos p = queue.poll();

			for (int i = 0; i < 4; i++) {
				int tmpY = p.y + dirY[i];
				int tmpX = p.x + dirX[i];

				if (visitable(tmpY, tmpX)) {
					if (map[tmpY][tmpX] != 0 && sharkSize > map[tmpY][tmpX]) {
						pq.add(new Pos(tmpY, tmpX, p.dist + 1));
					}

					visited[tmpY][tmpX] = true;
					queue.add(new Pos(tmpY, tmpX, p.dist + 1));
				}
			}
		}

		if (pq.isEmpty()) {
			return 0;
		}

		Pos dest = pq.poll();
		fishCount += 1;
		map[sharkPos.y][sharkPos.x] = 0;
		map[dest.y][dest.x] = 9;
		sharkPos = new Point(dest.x, dest.y);

		// System.out.printf("\n먹은 물고기 수: %d\t현재 사이즈: %d\n", fishCount, sharkSize);

		if (fishCount == sharkSize) {
			fishCount = 0;
			sharkSize += 1;
			// System.out.printf("\n먹은 물고기 수: %d\t현재 사이즈: %d\n", fishCount, sharkSize);
		}

		return dest.dist;
	}

	public static boolean visitable(int y, int x) {
		return (0 <= y) && (y < N) && (0 <= x) && (x < N) && !visited[y][x] && (sharkSize >= map[y][x]);
	}

	public static void resetVisited() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				visited[i][j] = false;
			}
		}
	}

	public static void printMap() {
		System.out.println();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.printf("%d ", map[i][j]);
			}
			System.out.println();
		}
	}
}