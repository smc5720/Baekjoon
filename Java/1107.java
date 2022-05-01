import java.io.*;
import java.util.*;

public class Main {
	public static int N, M;
	public static boolean[] enabled = new boolean[10];
	public static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		StringTokenizer st;

		if (M > 0) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int i = 0; i < 10; i++) {
				enabled[i] = true;
			}

			for (int i = 0; i < M; i++) {
				int n = Integer.parseInt(st.nextToken());
				enabled[n] = false;
			}

			result = Math.min(getNearest(N), Math.abs(100 - N));
			bw.write(String.valueOf(result));
		}

		else {
			if (N < 10) {
				bw.write("1");
			} else {
				int n = (int) (Math.floor(Math.log10(N))) + 1;
				result = Math.min(n, Math.abs(100 - N));
				bw.write(String.valueOf(result));
			} 
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static int getNearest(int n) {
		int small = 500001;
		int big = 500001;

		for (int i = n; i >= 0; i--) {
			if (check(i)) {
				if (i < 10) {
					small = Math.abs(N - i) + 1;
				} else {
					small = Math.abs(N - i) + (int) (Math.floor(Math.log10(i))) + 1;
				}
				break;
			}
		}

		for (int i = n; i <= 9999999; i++) {
			if (Math.abs(N - i) >= small) {
				break;
			}

			if (check(i)) {
				if (i < 10) {
					big = Math.abs(N - i) + 1;
				} else {
					big = Math.abs(N - i) + (int) (Math.floor(Math.log10(i))) + 1;
				}
				break;
			}
		}

		int result = Math.min(small, big);
		return result;
	}

	// 숫자 n을 만들 수 있는지 확인한다.
	public static boolean check(int n) {
		int num = n;

		if (n < 10) {
			return enabled[n];
		}

		while (num > 0) {
			int c = num % 10;

			if (!enabled[c]) {
				return false;
			}

			num /= 10;
		}

		return true;
	}
}