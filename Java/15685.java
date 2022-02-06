import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int N;
	public static ArrayList<Pos> dragonCurve;
	public static boolean[][] map;

	public static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		map = new boolean[101][101];

		for (int y = 0; y <= 100; y++) {
			for (int x = 0; x <= 100; x++) {
				map[y][x] = false;
			}
		}

		StringTokenizer st;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());

			dragonCurve = new ArrayList<Pos>();
			dragonCurve.add(new Pos(x, y));

			if (d == 0) {
				dragonCurve.add(new Pos(x + 1, y));
			} else if (d == 1) {
				dragonCurve.add(new Pos(x, y - 1));
			} else if (d == 2) {
				dragonCurve.add(new Pos(x - 1, y));
			} else if (d == 3) {
				dragonCurve.add(new Pos(x, y + 1));
			}

			rotate(0, g, d);
		}

		bw.write(String.valueOf(countSquare()));

		bw.flush();
		bw.close();
		br.close();
	}

	public static void rotate(int gen, int g, int d) {
		if (gen == g) {
			// printCurve();
			marking();
			return;
		}

		int size = dragonCurve.size();
		Pos pivot = dragonCurve.get(size - 1);

		for (int i = size - 2; i >= 0; i--) {
			Pos p = dragonCurve.get(i);

			int x, y;
			x = p.x - pivot.x;
			y = p.y - pivot.y;

			int tmp = x;
			x = y * (-1);
			y = tmp;

			dragonCurve.add(new Pos(x + pivot.x, y + pivot.y));
		}

		rotate(gen + 1, g, d);
	}

	public static void marking() {
		for (int i = 0; i < dragonCurve.size(); i++) {
			int x = dragonCurve.get(i).x;
			int y = dragonCurve.get(i).y;

			map[y][x] = true;
		}
	}

	public static int countSquare() {
		int sum = 0;

		for (int y = 0; y < 100; y++) {
			for (int x = 0; x < 100; x++) {
				if (map[y][x] && checkSquare(y, x)) {
					sum += 1;
				}
			}
		}

		return sum;
	}

	public static boolean checkSquare(int y, int x) {
		return (map[y][x + 1]) && (map[y + 1][x]) && (map[y + 1][x + 1]);
	}

	public static void printCurve() {
		System.out.println();
		for (int i = 0; i < dragonCurve.size(); i++) {
			System.out.printf("(%d, %d)\n", dragonCurve.get(i).x, dragonCurve.get(i).y);
		}
		System.out.println();
	}
}