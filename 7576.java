import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static Queue<int[]> queue = new LinkedList<int[]>();
	static int M = 0;
	static int N = 0;
	static int[][] map = new int[1000][1000];

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int y = 0; y < M; y++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int x = 0; x < N; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
				if (map[y][x] == 1) {
					int[] tmp = { y, x, 0 };
					queue.add(tmp);
				}
			}
		}

		int result = BFS();

		if (checkMap()) {
			bw.write(String.valueOf(result));
		} else {
			bw.write("-1");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static int BFS() {
		int day = 0;
		int[] dirY = { -1, 1, 0, 0 };
		int[] dirX = { 0, 0, -1, 1 };
		int Y = 0;
		int X = 1;

		while (!queue.isEmpty()) {
			int[] pos = queue.poll();
			day = pos[2];

			for (int i = 0; i < 4; i++) {
				int nowY = pos[Y] + dirY[i];
				int nowX = pos[X] + dirX[i];

				if (isVisitable(nowY, nowX)) {
					map[nowY][nowX] = 1;
					int[] tmp = { nowY, nowX, pos[2] + 1 };
					queue.add(tmp);
				}
			}
		}

		return day;
	}

	public static boolean isVisitable(int Y, int X) {
		return (0 <= Y && Y < M) && (0 <= X && X < N) && (map[Y][X] == 0);
	}

	public static boolean checkMap() {
		for (int y = 0; y < M; y++) {
			for (int x = 0; x < N; x++) {
				if (map[y][x] == 0)
					return false;
			}
		}
		return true;
	}

	public static void printMap() {
		System.out.println();

		for (int y = 0; y < M; y++) {
			for (int x = 0; x < N; x++) {
				System.out.printf("%d ", map[y][x]);
			}
			System.out.println();
		}
	}
}