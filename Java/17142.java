import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int N, M, answer;
	public static int[][] vMap, map;
	public static ArrayList<Virus> virus;
	public static Virus[] activate;
	public static boolean[][] visited;

	public static class Virus {
		int y, x, time;

		public Virus(int y, int x, int time) {
			this.y = y;
			this.x = x;
			this.time = time;
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		vMap = new int[N][N];
		map = new int[N][N];
		visited = new boolean[N][N];

		virus = new ArrayList<Virus>();
		activate = new Virus[M];

		answer = -1;

		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int x = 0; x < N; x++) {
				vMap[y][x] = Integer.parseInt(st.nextToken());

				if (vMap[y][x] == 2) {
					virus.add(new Virus(y, x, 0));
				}

				else if (vMap[y][x] == 1) {
					vMap[y][x] = -1;
				}
			}
		}

		initState();

		if (check()) {
			bw.write("0");
		} else {
			DFS(0, 0);
			bw.write(String.valueOf(answer));
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static boolean visitable(int y, int x) {
		return (0 <= y) && (y < N) && (0 <= x) && (x < N) && (!visited[y][x]) && (map[y][x] != -1);
	}

	public static void initState() {
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				visited[y][x] = false;
				map[y][x] = vMap[y][x];
			}
		}
	}

	// 모든 방에 바이러스가 퍼졌는지 확인한다.
	public static boolean check() {
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (map[y][x] == 0 && !visited[y][x]) {
					return false;
				}
			}
		}

		return true;
	}

	public static void printMap() {
		System.out.println();

		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (map[y][x] > -1) {
					System.out.printf("%d ", map[y][x]);
				} else {
					System.out.printf("  ");
				}
			}
			System.out.println();
		}

		System.out.println();
	}

	public static void DFS(int depth, int start) {
		if (depth == M) {
			// 바이러스 퍼뜨리기
			BFS();
			return;
		}

		for (int i = start; i < virus.size(); i++) {
			activate[depth] = virus.get(i);
			DFS(depth + 1, i + 1);
		}
	}

	public static void BFS() {
		initState();

		int[] dirY = { 0, 0, -1, 1 };
		int[] dirX = { -1, 1, 0, 0 };

		Queue<Virus> queue = new LinkedList<Virus>();

		for (int i = 0; i < M; i++) {
			queue.add(activate[i]);
			visited[activate[i].y][activate[i].x] = true;
			map[activate[i].y][activate[i].x] = 1;
		}

		int countTime = 0;

		// System.out.printf("%d초 경과\n", countTime);
		// printMap();

		while (!queue.isEmpty()) {
			Virus vir = queue.poll();

			if (countTime < vir.time) {
				countTime = vir.time;
				// System.out.printf("%d초 경과\n", countTime);
				// printMap();

				if (check()) {
					// System.out.printf("통과: %d초\n", countTime);
					if (answer == -1) {
						answer = countTime;
					} else {
						answer = Math.min(answer, countTime);
					}
				}
			}

			for (int i = 0; i < 4; i++) {
				int tmpY = vir.y + dirY[i];
				int tmpX = vir.x + dirX[i];

				if (visitable(tmpY, tmpX)) {
					visited[tmpY][tmpX] = true;
					map[tmpY][tmpX] = 1;
					queue.add(new Virus(tmpY, tmpX, vir.time + 1));
				}
			}
		}
	}
}