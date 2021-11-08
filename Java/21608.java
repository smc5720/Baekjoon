import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N;
	static int[][] map;
	static Student[] students;

	public static class Student {
		int no;
		int[] favor = new int[4];

		public Student(int no, int[] std) {
			this.no = no;

			for (int i = 0; i < 4; i++) {
				this.favor[i] = std[i];
			}
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1];
		students = new Student[N * N + 1];

		for (int y = 1; y <= N; y++) {
			for (int x = 1; x <= N; x++) {
				map[y][x] = 0;
			}
		}

		for (int n = 0; n < N * N; n++) {
			st = new StringTokenizer(br.readLine());
			int[] std = new int[4];
			int no = Integer.parseInt(st.nextToken());

			for (int i = 0; i < 4; i++) {
				std[i] = Integer.parseInt(st.nextToken());
			}

			students[no] = new Student(no, std);
			Point pt = getFavorPos(students[no]);
			map[pt.y][pt.x] = no;
		}

		// printMap();

		int score = 0;

		for (int y = 1; y <= N; y++) {
			for (int x = 1; x <= N; x++) {
				int no = map[y][x];
				score += getScore(students[no], y, x);
			}
		}

		bw.write(String.valueOf(score));

		bw.flush();
		bw.close();
		br.close();
	}

	// 좋아하는 학생이 가장 많이 인접한 빈 칸을 구한다.
	public static Point getFavorPos(Student std) {
		int[] dirY = { 0, 0, -1, 1 };
		int[] dirX = { -1, 1, 0, 0 };

		Point pt = new Point(0, 0);

		int maxFavor = 0;
		int maxSpace = -1;

		for (int y = 1; y <= N; y++) {
			for (int x = 1; x <= N; x++) {
				// 해당 칸이 빈 칸이면
				if (map[y][x] == 0) {
					int favor = 0;
					int space = 0;

					for (int i = 0; i < 4; i++) {
						int yPos = y + dirY[i];
						int xPos = x + dirX[i];

						if (visitable(yPos, xPos)) {
							if (map[yPos][xPos] == 0) {
								space += 1;
							} else if (isFavor(map[yPos][xPos], std)) {
								favor += 1;
							}
						}
					}

					if (maxFavor < favor) {
						maxFavor = favor;
						maxSpace = space;
						pt.y = y;
						pt.x = x;
					} else if (maxFavor == favor) {
						if (maxSpace < space) {
							maxSpace = space;
							pt.y = y;
							pt.x = x;
						}
					}
				}
			}
		}

		return pt;
	}

	public static int getScore(Student std, int y, int x) {
		int favor = 0;

		int[] dirY = { 0, 0, -1, 1 };
		int[] dirX = { -1, 1, 0, 0 };

		for (int i = 0; i < 4; i++) {
			int posY = y + dirY[i];
			int posX = x + dirX[i];

			if (visitable(posY, posX)) {
				if (isFavor(map[posY][posX], std)) {
					favor += 1;
				}
			}
		}

		if (favor == 0) {
			return 0;
		} else if (favor == 1) {
			return 1;
		} else if (favor == 2) {
			return 10;
		} else if (favor == 3) {
			return 100;
		}

		return 1000;
	}

	public static boolean visitable(int y, int x) {
		return (1 <= y) && (y <= N) && (1 <= x) && (x <= N);
	}

	public static boolean isFavor(int no, Student std) {
		for (int i = 0; i < 4; i++) {
			if (no == std.favor[i]) {
				return true;
			}
		}
		return false;
	}

	public static void printMap() {
		System.out.println();

		for (int y = 1; y <= N; y++) {
			for (int x = 1; x <= N; x++) {
				System.out.printf("%d ", map[y][x]);
			}
			System.out.println();
		}
	}
}