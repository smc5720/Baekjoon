import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int num = N;
		int cnt;

		do {
			cnt = 0;
			int bottles = num;

			while (bottles > 0) {
				if (bottles % 2 == 1) {
					cnt += 1;
				}

				bottles /= 2;
			}

			num += 1;
		} while (cnt > K);

		bw.write(String.valueOf(num - N - 1));
		bw.flush();
		bw.close();
		br.close();
	}
}