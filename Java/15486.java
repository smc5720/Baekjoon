import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringTokenizer st;
	public static int N;
	public static int[] T, P, DP;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		T = new int[N + 1];
		P = new int[N + 1];
		DP = new int[N + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}

		int maxVal = 0;

		for (int i = 0; i <= N; i++) {
			maxVal = Math.max(maxVal, DP[i]);

			if (visitable(i + T[i])) {
				DP[i + T[i]] = Math.max(DP[i + T[i]], maxVal + P[i]);
			}

			// printMap();
		}

		bw.write(String.valueOf(maxVal));

		br.close();
		bw.close();
	}

	public static boolean visitable(int d) {
		return 0 <= d && d <= N;
	}

	public static void printMap() {
		for (int i = 0; i < N; i++) {
			System.out.printf("%d ", T[i]);
		}

		System.out.println();

		for (int i = 0; i < N; i++) {
			System.out.printf("%d ", DP[i]);
		}

		System.out.println("\n");
	}
}