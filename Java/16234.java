import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int N, L, R, day;
	public static Country[][] country;
	public static boolean[][] visited;
	public static boolean state;

	public static class Country {
		int r, c, people;

		public Country(int r, int c, int people) {
			this.r = r;
			this.c = c;
			this.people = people;
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		country = new Country[N][N];
		visited = new boolean[N][N];

		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int x = 0; x < N; x++) {
				country[y][x] = new Country(y, x, Integer.parseInt(st.nextToken()));
			}
		}

		day = -1;
		state = true;

		while (!check() && state) {
			// printMap();
			initVisited();
			state = false;

			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					if (!visited[y][x]) {
						BFS(y, x);
					}
				}
			}

			day += 1;
		}

		bw.write(String.valueOf(day));

		bw.flush();
		bw.close();
		br.close();
	}

	public static boolean check() {
		int n = country[0][0].people;

		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (country[y][x].people != n) {
					return false;
				}
			}
		}

		day += 1;
		return true;
	}

	public static void initVisited() {
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				visited[y][x] = false;
			}
		}
	}

	public static void BFS(int r, int c) {
		ArrayList<Country> list = new ArrayList<Country>();
		list.add(country[r][c]);

		Queue<Country> queue = new LinkedList<Country>();
		queue.add(country[r][c]);

		visited[r][c] = true;

		int[] dirR = { 0, 0, -1, 1 };
		int[] dirC = { -1, 1, 0, 0 };

		while (!queue.isEmpty()) {
			Country ctr = queue.poll();

			for (int i = 0; i < 4; i++) {
				int tmpR = ctr.r + dirR[i];
				int tmpC = ctr.c + dirC[i];

				if (visitable(tmpR, tmpC)) {
					int p = Math.abs(ctr.people - country[tmpR][tmpC].people);

					if (L <= p && p <= R) {
						visited[tmpR][tmpC] = true;
						queue.add(country[tmpR][tmpC]);
						list.add(country[tmpR][tmpC]);
					}
				}
			}
		}

		if (list.size() > 1) {
			movePeople(list);
		}
	}

	public static boolean visitable(int r, int c) {
		return (0 <= r) && (r < N) && (0 <= c) && (c < N) && (!visited[r][c]);
	}

	public static void movePeople(ArrayList<Country> list) {
		int sum = 0;

		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i).people;
		}

		int p = sum / list.size();

		for (int i = 0; i < list.size(); i++) {
			int r = list.get(i).r;
			int c = list.get(i).c;

			country[r][c].people = p;
		}

		state = true;
	}

	public static void printMap() {
		System.out.println();

		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				System.out.printf("%d ", country[y][x].people);
			}
			System.out.println();
		}

		System.out.println();
	}
}