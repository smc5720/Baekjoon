import java.io.*;
import java.util.*;

public class Main {
	public static int N, C, left, right;
	public static int[] x;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		x = new int[N];

		for (int i = 0; i < N; i++) {
			x[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(x);

		left = 0;
		right = 1000000000;

		int answer = 0;

		while (left <= right) {
			int mid = (left + right) / 2;

			// 성공했다면 거리를 더 늘려본다.
			if (check(mid)) {
				answer = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		bw.write(String.valueOf(answer));

		bw.flush();
		bw.close();
		br.close();
	}

	public static boolean check(int k) {
		// 공유기가 가장 최근에 설치된 집의 좌표를 의미한다.
		int w = x[0];
		int cnt = 1;

		for (int i = 1; i < N; i++) {
			if (w + k <= x[i]) {
				cnt += 1;
				w = x[i];
			}
		}

		if (cnt < C) {
			return false;
		}

		return true;
	}
}