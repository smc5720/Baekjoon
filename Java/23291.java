import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringTokenizer st;
	public static int N, K;
	public static int[][] map;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < N; i++) {
			map[0][i] = Integer.parseInt(st.nextToken());
		}

		int answer = 0;

		while (getFishAmount() > K) {
			answer += 1;
			// System.out.printf("%d번째 정리\n\n", answer);

			sortBowl();
		}

		bw.write(String.valueOf(answer));

		br.close();
		bw.close();
	}

	public static void sortBowl() {
		int min = Integer.MAX_VALUE;

		// System.out.println("물고기의 수가 가장 적은 어항에 물고기를 한 마리 넣는다.");
		// System.out.println("만약, 그러한 어항이 여러개라면 물고기의 수가 최소인 어항 모두에 한 마리씩 넣는다.");

		for (int y = N - 1; y >= 0; y--) {
			for (int x = N - 1; x >= 0; x--) {
				if (map[y][x] > 0) {
					min = Math.min(min, map[y][x]);
				}
			}
		}

		for (int y = N - 1; y >= 0; y--) {
			for (int x = N - 1; x >= 0; x--) {
				if (map[y][x] == min) {
					map[y][x] += 1;
				}
			}
		}

		// printMap();

		// System.out.println("먼저, 가장 왼쪽에 있는 어항을 그 어항의 오른쪽에 있는 어항의 위에 올려 놓는다.");

		map[1][0] = map[0][0];
		pushBack(1, N - 1, 0);

		// System.out.println("이제, 2개 이상 쌓여있는 어항을 모두 공중 부양시킨 다음, 전체를 시계방향으로 90도
		// 회전시킨다.");
		// System.out.println("이후 공중 부양시킨 어항을 바닥에 있는 어항의 위에 올려놓는다.");

		int maxY = 1;
		int maxX = 0;
		int lastIdx = getLastIdx();

		while (maxY + 1 <= lastIdx - maxX) {
			int[][] tmp = new int[N][N];

			for (int y = maxY; y >= 0; y--) {
				for (int x = 0; x <= maxX; x++) {
					tmp[y][x] = map[y][x];
					map[y][x] = 0;
				}
			}

			// printMap();

			for (int y = maxX + 1; y >= 1; y--) {
				for (int x = 0; x <= maxY; x++) {
					map[y][x] = tmp[x][maxX + 1 - y];
				}
			}

			// printMap();

			pushBack(maxX + 1, lastIdx, 0);
			// printMap();

			for (int y = 0; y < N; y++) {
				if (map[y][0] > 0) {
					maxY = y;
				}
			}

			for (int x = 0; x < N; x++) {
				if (map[maxY][x] > 0) {
					maxX = x;
				}
			}

			lastIdx = getLastIdx();
			// printMap();
		}

		// printMap();

		// System.out.println("어항에 있는 물고기의 수를 조절한다.");

		divideFish();
		// printMap();

		// System.out.println("다시 어항을 바닥에 일렬로 놓아야 한다.");

		int idx = 0;
		int[] tmp = new int[N];

		for (int x = 0; x <= maxX; x++) {
			for (int y = 0; y <= maxY; y++) {
				tmp[idx] = map[y][x];
				idx += 1;
			}
		}

		for (int x = maxX + 1; x <= lastIdx; x++) {
			tmp[idx] = map[0][x];
			idx += 1;
		}

		for (int y = N - 1; y >= 0; y--) {
			for (int x = 0; x < N; x++) {
				map[y][x] = 0;
			}
		}

		for (int i = 0; i < idx; i++) {
			map[0][i] = tmp[i];
		}

		// printMap();

		// System.out.println("가운데를 중심으로 왼쪽 N/2개를 공중 부양시켜 전체를 시계 방향으로 180도 회전 시킨 다음, 오른쪽
		// N/2개의 위에 놓는다.");

		int totalLen = N / 2;
		tmp = new int[totalLen];

		for (int x = totalLen - 1; x >= 0; x--) {
			tmp[totalLen - 1 - x] = map[0][x];
		}

		for (int x = 0; x < totalLen; x++) {
			map[1][x] = tmp[x];
		}

		pushBack(totalLen, N - 1, 0);
		// printMap();

		// System.out.println("이 작업은 두 번 반복해야 한다.");

		totalLen = N / 4;
		int[][] tmps = new int[2][totalLen];

		for (int y = 0; y < 2; y++) {
			for (int x = totalLen - 1; x >= 0; x--) {
				tmps[y][totalLen - 1 - x] = map[y][x];
			}
		}

		for (int y = 3; y >= 2; y--) {
			for (int x = 0; x < totalLen; x++) {
				map[y][x] = tmps[3 - y][x];
			}
		}

		pushBack(totalLen, N / 2 - 1, 0);
		pushBack(totalLen, N / 2 - 1, 1);
		// printMap();

		// System.out.println("여기서 다시 위에서 한 물고기 조절 작업을 수행하고,");

		divideFish();
		// printMap();

		// System.out.println("바닥에 일렬로 놓는 작업을 수행한다.");

		tmp = new int[N];
		idx = 0;

		for (int x = 0; x < N / 4; x++) {
			for (int y = 0; y < 4; y++) {
				tmp[idx] = map[y][x];
				idx += 1;
			}
		}

		for (int y = N - 1; y >= 0; y--) {
			for (int x = 0; x < N; x++) {
				map[y][x] = 0;
			}
		}

		for (int i = 0; i < idx; i++) {
			map[0][i] = tmp[i];
		}

		// printMap();
	}

	public static void divideFish() {
		boolean[][] visited = new boolean[N][N];
		int[][] tmp = new int[N][N];

		int[] dy = { 0, 0, -1, 1 };
		int[] dx = { -1, 1, 0, 0 };

		for (int y = N - 1; y >= 0; y--) {
			for (int x = 0; x < N; x++) {
				if (map[y][x] == 0) {
					continue;
				}

				visited[y][x] = true;
				int nowFish = map[y][x];

				for (int i = 0; i < 4; i++) {
					int ty = y + dy[i];
					int tx = x + dx[i];

					if ((0 <= ty && ty < N && 0 <= tx && tx < N) && !visited[ty][tx] && map[ty][tx] > 0) {
						int d = Math.abs(nowFish - map[ty][tx]) / 5;

						if (d == 0) {
							continue;
						}

						if (nowFish > map[ty][tx]) {
							tmp[y][x] -= d;
							tmp[ty][tx] += d;
						} else {
							tmp[y][x] += d;
							tmp[ty][tx] -= d;
						}
					}
				}
			}
		}

		for (int y = N - 1; y >= 0; y--) {
			for (int x = 0; x < N; x++) {
				map[y][x] += tmp[y][x];
			}
		}
	}

	public static void pushBack(int space, int lastIdx, int y) {
		for (int i = space; i <= lastIdx; i++) {
			map[y][i - space] = map[y][i];
			map[y][i] = 0;
		}
	}

	public static int getLastIdx() {
		int idx = 0;

		for (int x = 0; x < N; x++) {
			if (map[0][x] > 0) {
				idx = x;
			}
		}

		return idx;
	}

	public static int getFishAmount() {
		int max = 0;
		int min = Integer.MAX_VALUE;

		for (int y = N - 1; y >= 0; y--) {
			for (int x = 0; x < N; x++) {
				if (map[y][x] > 0) {
					max = Math.max(max, map[y][x]);
					min = Math.min(min, map[y][x]);
				}
			}
		}

		return max - min;
	}

	public static void printMap() {
		System.out.println();

		for (int y = N - 1; y >= 0; y--) {
			for (int x = 0; x < N; x++) {
				if (map[y][x] > 0) {
					System.out.printf("%d\t", map[y][x]);
				} else {
					System.out.printf("-\t", map[y][x]);
				}
			}

			System.out.println();
		}

		System.out.println();
	}
}