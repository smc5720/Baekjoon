import java.io.*;
import java.util.*;

public class Main {
	public static final int inf = 87654321;
	public static int[][] weight;
	public static int n, m; 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		weight = new int[n + 1][n + 1];

		for (int start = 1; start <= n; start++) {
			for (int dest = 1; dest <= n; dest++) {
				if (start == dest) {
					weight[start][dest] = 0;
				} else {
					weight[start][dest] = inf;
				}
			}
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			weight[a][b] = Math.min(weight[a][b], c);
		}

		for (int via = 1; via <= n; via++) {
			for (int start = 1; start <= n; start++) {
				for (int dest = 1; dest <= n; dest++) {
					weight[start][dest] = Math.min(weight[start][dest], weight[start][via] + weight[via][dest]);
				}
			}
		}

		printMap();

		bw.flush();
		bw.close();
		br.close();
	}

	public static void printMap() {
		for (int start = 1; start <= n; start++) {
			for (int dest = 1; dest <= n; dest++) {
				if (weight[start][dest] != inf) {
					System.out.printf("%d ", weight[start][dest]);
				} else {
					System.out.printf("0 ");
				}
			}
			System.out.println();
		}
	}
}