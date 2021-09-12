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

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N + 1][N + 1];
		int[][] dp = new int[N + 1][N + 1];

		for (int x = 0; x <= N; x++) {
			if (x != 0) {
				st = new StringTokenizer(br.readLine(), " ");
			}

			for (int y = 0; y <= N; y++) {
				if (x == 0 || y == 0) {
					map[x][y] = 0;
					dp[x][y] = 0;
					continue;
				}
				map[x][y] = Integer.parseInt(st.nextToken());
				dp[x][y] = dp[x - 1][y] + dp[x][y - 1] + map[x][y] - dp[x - 1][y - 1];
			}
		}
	
		// printMap(map);
		// printMap(dp);

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			// (x2, y2) - (x1 - 1, y2) - (x2, y1 - 1) + (x1 - 1, y1 - 1)
			int sum = dp[x2][y2] - dp[x1 - 1][y2] - dp[x2][y1 - 1] + dp[x1 - 1][y1 - 1];

			bw.write(String.valueOf(sum));
			bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static void printMap(int[][] map) {
		System.out.println();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.printf("%d\t", map[i][j]);
			}
			System.out.println();
		}
	}
}