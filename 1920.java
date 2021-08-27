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

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int[] A = new int[N];

		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(A);

		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < M; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			bw.write(binarySearch(A, tmp).toString());
			bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static Integer binarySearch(int[] A, int ans) {
		int left = 0;
		int right = A.length - 1;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (A[mid] == ans) {
				return 1;
			}
			// 찾으려는 값보다 크다면 right를 조절한다.
			else if (A[mid] > ans) {
				right = mid - 1;
			}
			// 찾으려는 값보다 작다면 left를 조절한다.
			else {
				left = mid + 1;
			}
		}

		return 0;
	}
}
