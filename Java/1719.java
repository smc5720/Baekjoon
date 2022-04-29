import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringTokenizer st;
	public static int n, m;
	public static int[][] dp, way;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		dp = new int[n + 1][n + 1];
		way = new int[n + 1][n + 1];

		for (int start = 1; start <= n; start++) {
			for (int dest = 1; dest <= n; dest++) {
				if (start == dest) {
					dp[start][dest] = 0;
				} else {
					dp[start][dest] = Integer.MAX_VALUE;
				}
			}
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			int start = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			if (dp[start][dest] > weight) {
				dp[start][dest] = weight;
				dp[dest][start] = dp[start][dest];
				way[start][dest] = dest;
				way[dest][start] = start;
			}
		}

		for (int share = 1; share <= n; share++) {
			for (int start = 1; start <= n; start++) {
				if (share == start) {
					continue;
				}

				for (int dest = 1; dest <= n; dest++) {
					if (dest == start || dest == share) {
						continue;
					}

					if (dp[start][share] == Integer.MAX_VALUE || dp[share][dest] == Integer.MAX_VALUE) {
						continue;
					}

					int w = dp[start][share] + dp[share][dest];

					if (w < dp[start][dest]) {
						dp[start][dest] = w;
						way[start][dest] = way[start][share];
					}
				}
			}
		}

		for (int start = 1; start <= n; start++) {
			for (int dest = 1; dest <= n; dest++) {
				if (way[start][dest] != 0) {
					bw.write(way[start][dest] + " ");
				} else {
					bw.write("- ");
				}
			}

			bw.newLine();
		}

		br.close();
		bw.close();
	}
}