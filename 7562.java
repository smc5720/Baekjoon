import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static boolean[][] visited = new boolean[300][300];
	static final int Y = 0;
	static final int X = 1;
	static final int MOVES = 2;
	static int mapSize = 0;

	public static void main(String[] args) throws IOException {

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			mapSize = Integer.parseInt(br.readLine());

			for (int y = 0; y < mapSize; y++) {
				for (int x = 0; x < mapSize; x++) {
					visited[y][x] = false;
				}
			}

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int[] start = { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };

			st = new StringTokenizer(br.readLine(), " ");
			int[] end = { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };

			bw.write(String.valueOf(BFS(start, end)));
			bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static boolean isVisitable(int y, int x) {
		return (0 <= y && y < mapSize) && (0 <= x && x < mapSize) && !visited[y][x];
	}

	public static int BFS(int[] start, int[] end) {
		if (start[X] == end[X] && start[Y] == end[Y]) {
			return 0;
		}

		Queue<int[]> queue = new LinkedList<int[]>();

		int[] tmp = { start[Y], start[X], 0 };
		queue.add(tmp);

		int[] dirY = { -2, -1, 1, 2, 2, 1, -1, -2 };
		int[] dirX = { 1, 2, 2, 1, -1, -2, -2, -1 };

		visited[start[Y]][start[X]] = true;

		while (!queue.isEmpty()) {
			// 현재 위치와 이동 횟수가 주어진다.
			int[] now = queue.poll();

			for (int i = 0; i < 8; i++) {
				int posY = now[Y] + dirY[i];
				int posX = now[X] + dirX[i];

				// 방문할 수 있는 위치라면
				if (isVisitable(posY, posX)) {
					if (posY == end[Y] && posX == end[X]) {
						return now[MOVES] + 1;
					}

					visited[posY][posX] = true;

					int[] dum = {posY, posX, now[MOVES] + 1};
					queue.add(dum);
				}
			}
		}

		return -1;
	}
}