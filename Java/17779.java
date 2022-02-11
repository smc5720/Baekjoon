import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int N;
	public static Area[][] area;
	public static boolean[][] visited;
	public static int[] people;

	public static class Area {
		int number, people;
		boolean isEdge;

		public Area(int number, int people) {
			this.number = number;
			this.people = people;
			this.isEdge = false;
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());

		area = new Area[N + 1][N + 1];
		visited = new boolean[N + 1][N + 1];
		people = new int[N + 1];

		for (int x = 1; x <= N; x++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int y = 1; y <= N; y++) {
				area[x][y] = new Area(0, Integer.parseInt(st.nextToken()));
			}
		}

		int answer = -1;

		for (int y = 1; y <= N; y++) {
			for (int x = 1; x <= N; x++) {
				for (int d1 = 1; d1 <= N; d1++) {
					for (int d2 = 1; d2 <= N; d2++) {
						if (check(y, x, d1, d2)) {
							// System.out.printf("(x, y, d1, d2) = (%d, %d, %d, %d)\n ", x, y, d1, d2);
							initVisited();
							buildFifthArea(y, x, d1, d2);
							buildArea(y, x, d1, d2);
							// printArea();

							if (answer == -1) {
								answer = compareArea();
							} else {
								answer = Math.min(answer, compareArea());
							}
						}
					}
				}
			}
		}

		bw.write(String.valueOf(answer));

		bw.flush();
		bw.close();
		br.close();
	}

	// 해당 방식으로 구역을 나누는게 가능한지를 판단한다.
	public static boolean check(int y, int x, int d1, int d2) {
		return (x < x + d1 + d2) && (x + d1 + d2 <= N) && (1 <= y - d1) && (y - d1 < y) && (y < y + d2)
				&& (y + d2 <= N);
	}

	// 방문 여부를 초기화한다.
	public static void initVisited() {
		for (int y = 1; y <= N; y++) {
			for (int x = 1; x <= N; x++) {
				visited[x][y] = false;
				area[x][y].isEdge = false;
			}
		}
	}

	// 5번 구역을 설정한다.
	public static void buildFifthArea(int y, int x, int d1, int d2) {
		for (int i = 0; i <= d1; i++) {
			// 1번 경계선
			if (visitable(y - i, x + i)) {
				area[x + i][y - i].number = 5;
				area[x + i][y - i].isEdge = true;
				visited[x + i][y - i] = true;
			}

			// 4번 경계선
			if (visitable(y + d2 - i, x + d2 + i)) {
				area[x + d2 + i][y + d2 - i].number = 5;
				area[x + d2 + i][y + d2 - i].isEdge = true;
				visited[x + d2 + i][y + d2 - i] = true;
			}
		}

		for (int j = 0; j <= d2; j++) {
			// 2번 경계선
			if (visitable(y + j, x + j)) {
				area[x + j][y + j].number = 5;
				area[x + j][y + j].isEdge = true;
				visited[x + j][y + j] = true;
			}

			// 3번 경계선
			if (visitable(y - d1 + j, x + d1 + j)) {
				area[x + d1 + j][y - d1 + j].number = 5;
				area[x + d1 + j][y - d1 + j].isEdge = true;
				visited[x + d1 + j][y - d1 + j] = true;
			}
		}

		for (int r = 1; r <= N; r++) {
			int cnt = 0;

			for (int c = 1; c <= N; c++) {
				if (visited[r][c]) {
					cnt += 1;
				}
			}

			if (cnt <= 1) {
				continue;
			}

			boolean state = false;

			for (int c = 1; c <= N; c++) {
				if (!state) {
					if (visited[r][c]) {
						state = true;
					}
				} else {
					if (visited[r][c]) {
						state = false;
					} else {
						area[r][c].number = 5;
						visited[r][c] = true;
					}
				}
			}
		}
	}

	// 배열 범위 밖인지 확인한다.
	public static boolean visitable(int y, int x) {
		return (1 <= y) && (y <= N) && (1 <= x) && (x <= N);
	}

	// 1 ~ 4번 구역을 지정한다.
	public static int buildArea(int y, int x, int d1, int d2) {
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if (!visited[r][c]) {
					area[r][c].number = sortArea(y, x, r, c, d1, d2);
					visited[r][c] = true;
				}
			}
		}

		return 0;
	}

	// 입력한 좌표가 어떤 구역에 해당되는지 반환한다.
	public static int sortArea(int y, int x, int r, int c, int d1, int d2) {
		if (1 <= r && r < x + d1 && 1 <= c && c <= y) {
			return 1;
		}

		if (1 <= r && r <= x + d2 && y < c && c <= N) {
			return 2;
		}

		if (x + d1 <= r && r <= N && 1 <= c && c < y - d1 + d2) {
			return 3;
		}

		if (x + d2 < r && r <= N && y - d1 + d2 <= c && c <= N) {
			return 4;
		}

		return 0;
	}

	// 인구가 가장 많은 선거구와 가장 적은 선거구의 인구 차이
	public static int compareArea() {
		for (int i = 1; i <= 5; i++) {
			people[i] = 0;
		}

		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				int n = area[r][c].number;
				people[n] += area[r][c].people;
			}
		}

		int maxVal = people[1];
		int minVal = people[1];

		for (int i = 1; i <= 5; i++) {
			// System.out.printf("%d번 지역: %d명\n", i, people[i]);
			maxVal = Math.max(maxVal, people[i]);
			minVal = Math.min(minVal, people[i]);
		}

		// System.out.printf("차이: %d명\n", maxVal - minVal);
		return maxVal - minVal;
	}

	public static void printArea() {
		System.out.println();

		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if (area[r][c].isEdge) {
					System.out.printf("*");
				} else {
					System.out.printf("%d", area[r][c].number);
				}

				System.out.printf("(%d) ", area[r][c].people);
			}
			System.out.println();
		}

		System.out.println();
	}
}