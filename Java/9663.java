import java.io.*;
import java.util.*;

public class Main {
	public static int N, result;
	public static int[] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		map = new int[N];

		result = 0;

		DFS(0);

		bw.write(String.valueOf(result));

		bw.flush();
		bw.close();
		br.close();
	}

	public static void DFS(int y) {
		if (y == N) {
			result += 1;
			return;
		}

		for (int x = 0; x < N; x++) {
			map[y] = x;
			if (check(y)) {
				DFS(y + 1);
			}
		}
	}

	public static boolean check(int depth) {
		// map[y] = x

		for (int y = 0; y < depth; y++) {
			if (map[y] == map[depth]) {
				return false;
			}

			if (y - map[y] == depth - map[depth] || y + map[y] == depth + map[depth]) {
				return false;
			}
		}

		return true;
	}
}