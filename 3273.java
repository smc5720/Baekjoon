import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);
		int x = Integer.parseInt(br.readLine());

		int left = 0;
		int right = n - 1;

		int result = 0;

		while (left < right) {
			if (arr[left] + arr[right] < x) {
				// 원하는 값보다 작다면 left를 늘린다.
				left += 1;
			} else if (arr[left] + arr[right] > x) {
				// 원하는 값보다 크다면 right를 줄인다.
				right -= 1;
			} else {
				// 원하는 값이라면 result를 증가시킨다.
				result += 1;
				left += 1;
			}
		}

		bw.write(String.valueOf(result));

		bw.flush();
		bw.close();
		br.close();
	}
}