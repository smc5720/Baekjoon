import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int N;
	public static int[] dy = { 0, 0, 1, -1 };
	public static int[] dx = { 1, -1, 0, 0 };
	public static double[] per;
	public static double answer;
	public static boolean[][] visited; 

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());

		per = new double[4];
		visited = new boolean[30][30];
		answer = 0;

		for (int i = 0; i < 4; i++) {
			per[i] = (double) Integer.parseInt(st.nextToken()) / 100.0;
		}

		visited[15][15] = true;
		DFS(15, 15, 1.0, 0);

		bw.write(String.valueOf(answer));

		br.close();
		bw.close();
	}

	public static void DFS(int sy, int sx, double perc, int moves) {
		if (moves == N) {
			answer += perc;
			return;
		}

		for (int i = 0; i < 4; i++) {
			int ty = sy + dy[i];
			int tx = sx + dx[i];

			if (visitable(ty, tx)) {
				visited[ty][tx] = true;
				DFS(ty, tx, per[i] * perc, moves + 1);
				visited[ty][tx] = false;
			}
		}
	}

	public static boolean visitable(int y, int x) {
		return 0 <= y && y < 30 && 0 <= x && x < 30 && !visited[y][x];
	}
}