import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 해당 숫자를 방문했는지?
		Integer[] arr = new Integer[M];

		for (int i = 0; i <= N; i++) {
			if (i < M) {
				arr[i] = new Integer(0);
			}
		}

		func(arr, 0, N, M, bw, 1);

		bw.flush();
		bw.close();
		br.close();
	}

	public static void func(Integer[] arr, int depth, int N, int M, BufferedWriter bw, int start) throws IOException {
		if (depth == M) {
			for (int i = 0; i < arr.length; i++) {
				bw.write(arr[i].toString() + " ");
			}
			bw.newLine();
			return;
		}

		for (Integer i = start; i <= N; i++) {
			arr[depth] = i;
			func(arr, depth + 1, N, M, bw, i);
		}
	}
}