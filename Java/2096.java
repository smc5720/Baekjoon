import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int N;
	public static int[][] maxDp, minDp;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		maxDp = new int[N][3];
		minDp = new int[N][3];

		StringTokenizer st;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int j = 0; j < 3; j++) {
				maxDp[i][j] = Integer.parseInt(st.nextToken());
				minDp[i][j] = maxDp[i][j];
			}
		}

		for (int i = 1; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				int max = 0;
				int min = 9000000;

				for (int k = -1; k <= 1; k++) {
					if (0 <= j + k && j + k < 3) {
						max = Math.max(max, maxDp[i - 1][j + k] + maxDp[i][j]);
						min = Math.min(min, minDp[i - 1][j + k] + minDp[i][j]);
					}
				}

				maxDp[i][j] = max;
				minDp[i][j] = min;
			}
		}

		int max = Math.max(maxDp[N - 1][0], Math.max(maxDp[N - 1][1], maxDp[N - 1][2]));
		int min = Math.min(minDp[N - 1][0], Math.min(minDp[N - 1][1], minDp[N - 1][2]));

		String answer = max + " " + min;
		bw.write(answer);

		br.close();
		bw.close();
	}

	public static void printDp() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.printf("%d\t", maxDp[i][j]);
			}

			System.out.printf("\t\t");

			for (int j = 0; j < 3; j++) {
				System.out.printf("%d\t", minDp[i][j]);
			}
			
			System.out.println();
		}
	}
}