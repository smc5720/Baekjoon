import java.io.*;
import java.util.*;

public class Main {
	public static int N, M, Y, X, K;
	public static final int EAST = 1;
	public static final int WEST = 2;
	public static final int NORTH = 3;
	public static final int SOUTH = 4;
	public static int[] dice = new int[7];
	public static int top;
	public static int up;
	public static int right;
	public static int bottom;
	public static int down;
	public static int left;
	public static int[][] map;
	public static BufferedReader br;
	public static BufferedWriter bw;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		top = 1;
		up = 5;
		right = 3;
		bottom = 6;
		down = 2;
		left = 4;

		for (int i = 1; i <= 6; i++) {
			dice[i] = 0;
		}

		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int x = 0; x < M; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		for (int k = 0; k < K; k++) {
			int r = moveDice(Integer.parseInt(st.nextToken()));
			if (0 <= r) {
				bw.write(r + "\n");
			}
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static boolean visitable(int y, int x) {
		return (0 <= y) && (y < N) && (0 <= x) && (x < M);
	}

	public static int moveDice(int dir) {
		int[] dirY = { 0, 0, 0, -1, 1 };
		int[] dirX = { 0, 1, -1, 0, 0 };

		int tmpY = Y + dirY[dir];
		int tmpX = X + dirX[dir];

		if (visitable(tmpY, tmpX)) {
			Y = tmpY;
			X = tmpX;

			if (dir == NORTH) {
				int tmp = top;
				top = up;
				up = bottom;
				bottom = down;
				down = tmp;
			} else if (dir == SOUTH) {
				int tmp = top;
				top = down;
				down = bottom;
				bottom = up;
				up = tmp;
			} else if (dir == EAST) {
				int tmp = top;
				top = right;
				right = bottom;
				bottom = left;
				left = tmp;
			} else {
				int tmp = top;
				top = left;
				left = bottom;
				bottom = right;
				right = tmp;
			}

			// 이동한 칸에 쓰여 있는 수가 0이면
			if (map[Y][X] == 0) {
				// 주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다.
				map[Y][X] = dice[bottom];
			}

			// 0이 아닌 경우에는
			else {
				// 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며,
				dice[bottom] = map[Y][X];
				// 칸에 쓰여 있는 수는 0이 된다.
				map[Y][X] = 0;
			}

			return dice[top];
		}

		return -1;
	}
}