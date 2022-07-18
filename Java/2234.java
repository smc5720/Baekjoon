import java.io.*;
import java.util.*;

public class Main {
	public static int N, M, cnt;
	public static int[][] map, no;
	public static ArrayList<Integer> roomSize;

	// 서, 북, 동, 남
	public static int[] dy = { 0, -1, 0, 1 };
	public static int[] dx = { -1, 0, 1, 0 };

	public static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[M][N];
		no = new int[M][N];
		visited = new boolean[M][N];

		roomSize = new ArrayList<Integer>();
		roomSize.add(0);

		for (int y = 0; y < M; y++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int x = 0; x < N; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
				no[y][x] = 0;
			}
		}

		int roomNo = 1;
		int roomCnt = 0;
		int maxRoomSize = 0;
		int maxRoomSizeAfterBoom = 0;

		for (int y = 0; y < M; y++) {
			for (int x = 0; x < N; x++) {
				if (no[y][x] == 0) {
					cnt = 0;
					DFSRoomSearch(roomNo, y, x);
					roomSize.add(cnt);
					maxRoomSize = Math.max(maxRoomSize, cnt);
					roomCnt = roomNo;
					roomNo += 1;
				}
			}
		}

		for (int y = 0; y < M; y++) {
			for (int x = 0; x < N; x++) {
				visited[y][x] = true;

				for (int i = 0; i < 4; i++) {
					if ((map[y][x] & (1 << i)) > 0) {
						int ty = y + dy[i];
						int tx = x + dx[i];

						if (visitable(ty, tx) && !visited[ty][tx]) {
							// System.out.printf("%d, %d = %d's room\n", y, x, no[y][x]);
							// System.out.printf("%d, %d = %d's room\n", ty, tx, no[ty][tx]);

							if (no[y][x] != no[ty][tx]) {
								maxRoomSizeAfterBoom = Math.max(maxRoomSizeAfterBoom,
										roomSize.get(no[y][x]) + roomSize.get(no[ty][tx]));
							}
						}
					}
				}
			}
		}

		// printMap();

		bw.write(String.valueOf(roomCnt));
		bw.newLine();

		bw.write(String.valueOf(maxRoomSize));
		bw.newLine();

		bw.write(String.valueOf(maxRoomSizeAfterBoom));

		bw.flush();
		bw.close();
		br.close();
	}

	public static void DFSRoomSearch(int roomNo, int y, int x) {
		no[y][x] = roomNo;
		cnt += 1;

		for (int i = 0; i < 4; i++) {
			if ((map[y][x] & (1 << i)) == 0) {
				int ty = y + dy[i];
				int tx = x + dx[i];

				if (visitable(ty, tx) && (no[ty][tx] == 0)) {
					DFSRoomSearch(roomNo, ty, tx);
				}
			}
		}
	}

	public static boolean visitable(int y, int x) {
		return (0 <= y) && (y < M) && (0 <= x) && (x < N);
	}

	public static void printMap() {
		System.out.println();

		for (int y = 0; y < M; y++) {
			for (int x = 0; x < N; x++) {
				System.out.printf("%d ", no[y][x]);
			}

			System.out.println();
		}
	}
}