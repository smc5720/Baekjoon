import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		int answer = 1000000000;

		int[] sour = new int[N];
		int[] bitter = new int[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			sour[i] = Integer.parseInt(st.nextToken());
			bitter[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i < 1 << N; i++) {
			int S = 1;
			int B = 0;

			for (int j = 0; j < N; j++) {
				if ((i & 1 << j) > 0) {
					S *= sour[j];
					B += bitter[j];
				}
			}

			answer = Math.min(answer, Math.abs(S - B));
		}

		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}
}