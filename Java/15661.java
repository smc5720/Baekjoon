import java.io.*;
import java.util.*;

public class Main {
	public static int N;
	public static int[][] S;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		S = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			for (int j = 0; j < N; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = Integer.MAX_VALUE;

		for (int i = 1; i < (2 << N) - 1; i++) {
			int team1 = 0;
			int team2 = 0;

			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if (k != j) {
						// 팀1
						if (((1 << j) & i) == (1 << j)) {
							if (((1 << k) & i) == (1 << k)) {
								team1 += S[j][k];
							}
						}

						// 팀2
						else {
							if (((1 << k) & i) != (1 << k)) {
								team2 += S[j][k];
							}
						}
					}
				}
			}

			answer = Math.min(Math.abs(team1 - team2), answer);
		}

		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}

	public static boolean isSame(int n) {
		int cnt = 0;

		for (int i = 0; i < N; i++) {
			if (((1 << i) & n) == (1 << i)) {
				cnt += 1;
			}
		}

		return (cnt / 2 == N / 2) && (cnt % 2 == 0);
	}
}