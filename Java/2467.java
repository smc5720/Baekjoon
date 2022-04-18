import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringTokenizer st;
	public static int left, right, N;
	public static int[] val;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		val = new int[N];

		left = 0;
		right = N - 1;

		st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < N; i++) {
			val[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(val);
		int abs = 2000000000;

		int l = left;
		int r = right;

		while (l < r) {
			int a = val[l] + val[r];
			int v = Math.abs(a);

			if (v <= abs) {
				left = l;
				right = r;
				abs = v;

				if (v == 0) {
					break;
				}
			}

			if (a > 0) {
				r -= 1;
			} else {
				l += 1;
			}
		}

		bw.write(val[left] + " " + val[right]);

		br.close();
		bw.close();
	}
}