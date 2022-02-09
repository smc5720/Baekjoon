import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int r, c, k;
	public static int[][] A;
	public static int maxR, maxC;

	public static class Number implements Comparable<Number> {
		int val, cnt;

		public Number(int val, int cnt) {
			this.val = val;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Number n) {
			if (this.cnt == n.cnt) {
				return Integer.compare(this.val, n.val);
			}

			return Integer.compare(this.cnt, n.cnt);
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		A = new int[101][101];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		for (int y = 1; y <= 100; y++) {
			for (int x = 1; x <= 100; x++) {
				A[y][x] = 0;
			}
		}

		maxR = 3;
		maxC = 3;

		for (int y = 1; y <= 3; y++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int x = 1; x <= 3; x++) {
				A[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = 0;

		while (A[r][c] != k && answer <= 100) {
			answer += 1;
			operation();
		}

		if (A[r][c] != k) {
			bw.write("-1");
		} else {
			bw.write(String.valueOf(answer));
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static void operation() {
		int maxY = maxR;
		int maxX = maxC;

		if (maxY >= maxX) {
			// R 연산 적용: 모든 행에 대해서 정렬을 수행한다.
			for (int y = 1; y <= maxY; y++) {
				HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
				ArrayList<Number> list = new ArrayList<Number>();

				for (int x = 1; x <= maxX; x++) {
					if (A[y][x] != 0) {
						if (!hashMap.containsKey(A[y][x])) {
							hashMap.put(A[y][x], 0);
						}
						hashMap.put(A[y][x], hashMap.get(A[y][x]) + 1);
					}
				}

				for (int key : hashMap.keySet()) {
					int val = hashMap.get(key);
					list.add(new Number(key, val));
				}

				Collections.sort(list);

				int size = Math.min(100, list.size() * 2);
				maxC = Math.max(size, maxC);

				for (int i = 1; i <= maxC; i++) {
					if (i <= size / 2) {
						A[y][i * 2 - 1] = list.get(i - 1).val;
						A[y][i * 2] = list.get(i - 1).cnt;
					} else {
						A[y][i * 2 - 1] = 0;
						A[y][i * 2] = 0;
					}
				}
			}
		}

		else {
			// C 연산 적용: 모든 열에 대해서 정렬을 수행한다.
			for (int x = 1; x <= maxX; x++) {
				HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
				ArrayList<Number> list = new ArrayList<Number>();

				for (int y = 1; y <= maxY; y++) {
					if (A[y][x] != 0) {
						if (!hashMap.containsKey(A[y][x])) {
							hashMap.put(A[y][x], 0);
						}
						hashMap.put(A[y][x], hashMap.get(A[y][x]) + 1);
					}
				}

				for (int key : hashMap.keySet()) {
					int val = hashMap.get(key);
					list.add(new Number(key, val));
				}

				Collections.sort(list);

				int size = Math.min(100, list.size() * 2);
				maxR = Math.max(size, maxR);

				for (int i = 1; i <= maxR; i++) {
					if (i <= size / 2) {
						A[i * 2 - 1][x] = list.get(i - 1).val;
						A[i * 2][x] = list.get(i - 1).cnt;
					} else {
						A[i * 2 - 1][x] = 0;
						A[i * 2][x] = 0;
					}
				}
			}
		}

		// printMap();
	}

	public static void printMap() {
		System.out.println();

		for (int y = 1; y <= maxR; y++) {
			for (int x = 1; x <= maxC; x++) {
				System.out.printf("%d ", A[y][x]);
			}
			System.out.println();
		}

		System.out.println();
	}
}