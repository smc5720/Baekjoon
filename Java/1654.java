import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		int[] lan = new int[K];

		for (int i = 0; i < K; i++) {
			lan[i] = Integer.parseInt(br.readLine());
		}
		
		bw.write(String.valueOf(binarySearch(lan, N)));

		bw.flush();
		bw.close();
		br.close();
	}

	public static long binarySearch(int[] lan, int N) {
		long left = 0;
		long right = 2147483647;
		long result = 0;

		while (left <= right) {
			long mid = (left + right) / 2;
			// 원하던 양보다 많거나 같은 경우 left 값을 변경
			if (N <= func(lan, mid)) {
				left = mid + 1;
				result = mid;
			}
			// 원하던 양보다 적을 경우 right 값을 변경
			else {
				right = mid - 1;
			}
		}

		return result;
	}

	// 길이를 x로 줄 경우 랜선을 최대 몇개 구할 수 있는가?
	public static long func(int[] lan, long x) {
		long result = 0;

		for (int i = 0; i < lan.length; i++) {
			result += lan[i] / x;
		}

		return result;
	}
}
