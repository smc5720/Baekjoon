import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringTokenizer st;
	public static int n, m, r;
	public static int[] t;
	public static int[][] map;
	public static final int MAX = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		t = new int[n + 1];
		map = new int[n + 1][n + 1];

		st = new StringTokenizer(br.readLine(), " ");

		for (int i = 1; i <= n; i++) {
			t[i] = Integer.parseInt(st.nextToken());

			for (int j = 1; j <= n; j++) {
				if (i == j) {
					map[i][j] = 0;
				} else {
					map[i][j] = MAX;
				}
			}
		}

		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			map[a][b] = w;
			map[b][a] = w;
		}

		for (int share = 1; share <= n; share++) {
			for (int start = 1; start <= n; start++) {
				for (int dest = 1; dest <= n; dest++) {
					if (map[start][share] < MAX && map[share][dest] < MAX) {
						map[start][dest] = Math.min(map[start][dest], map[start][share] + map[share][dest]);
					}
				}
			}
		}

		int maxItems = 0;

		for (int i = 1; i <= n; i++) {
			int sum = 0;

			for (int j = 1; j <= n; j++) {
				if (map[i][j] <= m) {
					sum += t[j];
				}
			}

			maxItems = Math.max(maxItems, sum);
		}

		// printMap();

		bw.write(String.valueOf(maxItems));

		br.close();
		bw.close();
	}

	public static void printMap() {
		System.out.println();

		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0 && j == 0) {
					System.out.printf(" \t");
					continue;
				}

				if (i == 0) {
					System.out.printf("%d\t", j);
					continue;
				}

				if (j == 0) {
					System.out.printf("%d\t", i);
					continue;
				}

				if (map[i][j] == MAX) {
					System.out.printf("-\t");
					continue;
				}

				System.out.printf("%d\t", map[i][j]);
			}

			System.out.println();
		}

		System.out.println();
	}
}