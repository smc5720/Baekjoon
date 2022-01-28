import java.io.*;
import java.util.*;

public class Main {
	public static int N, M;
	public static int[][] map;
	public static ArrayList<House> chickenHouse;
	public static ArrayList<House> house;
	public static int[] perm;
	public static int answer;

	public static class House {
		int y, x, val;

		public House(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		perm = new int[M];

		chickenHouse = new ArrayList<House>();
		house = new ArrayList<House>();

		answer = 1000000000;

		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < N; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());

				if (map[y][x] == 1) {
					house.add(new House(y, x));
				} else if (map[y][x] == 2) {
					House chs = new House(y, x);
					chs.val = chickenHouse.size();
					chickenHouse.add(chs);
				}
			}
		}

		comb(0, 0);

		bw.write(String.valueOf(answer));

		bw.flush();
		bw.close();
		br.close();
	}

	public static void printPerm() {
		for (int i = 0; i < M; i++) {
			System.out.printf("%d ", perm[i]);
		}
		System.out.println();
	}

	public static void comb(int start, int depth) {
		if (depth == M) {
			// printPerm();
			answer = Math.min(answer, getChickenRoad());
			return;
		}

		int size = chickenHouse.size() - M + depth;

		for (int i = start; i <= size; i++) {
			perm[depth] = i;
			comb(i + 1, depth + 1);
		}
	}

	public static int getChickenRoad() {
		int result = 0;

		for (int i = 0; i < house.size(); i++) {
			int cr = 1000;
			House hs = house.get(i);

			for (int j = 0; j < M; j++) {
				House chs = chickenHouse.get(perm[j]);
				cr = Math.min(cr, getMhtDistance(hs, chs));
			}

			result += cr;
		}

		return result;
	}

	public static int getMhtDistance(House h1, House h2) {
		return Math.abs((h1.y - h2.y)) + Math.abs((h1.x - h2.x));
	}
}