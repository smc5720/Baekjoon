import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int[] comb, cmd, knight;
	public static int[] bVal = { 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 13, 16,
			19, 25, 26, 27, 28, 22, 24, 30, 35, 0 };
	public static Board[] map;
	public static int score, answer;

	public static class Board {
		int val, idx;
		boolean blue;
		Board next, bNext;

		public Board(int idx, int val) {
			this.idx = idx;
			this.val = val;

			if (idx == 5 || idx == 10 || idx == 15) {
				this.blue = true;
			} else {
				this.blue = false;
			}
		}
	}

	public static class StateVO {
		boolean state;
		int idx;

		public StateVO(boolean state, int idx) {
			this.state = state;
			this.idx = idx;
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		map = new Board[33];
		comb = new int[10];
		cmd = new int[10];
		knight = new int[4];

		score = 0;
		answer = 0;

		initBoard();

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < 4; i++) {
			knight[i] = 0;
		}

		for (int i = 0; i < 10; i++) {
			cmd[i] = Integer.parseInt(st.nextToken());
		}

		DFS(0);

		bw.write(String.valueOf(answer));

		bw.flush();
		bw.close();
		br.close();
	}

	public static StateVO movable(int k, int move) {
		int now = knight[k];

		if (now == 32) {
			return new StateVO(false, now);
		}

		// 현재 칸이 파란색 칸이라면
		if (map[now].blue) {
			now = map[now].bNext.idx;

			for (int i = 0; i < move - 1; i++) {
				now = map[now].next.idx;

				if (now == 32) {
					return new StateVO(true, now);
				}
			}
		}

		// 현재 칸이 파란색 칸이 아니라면
		else {
			for (int i = 0; i < move; i++) {
				now = map[now].next.idx;

				if (now == 32) {
					return new StateVO(true, now);
				}
			}
		}

		for (int i = 0; i < 4; i++) {
			if (now != 32 && i != k && knight[i] == now) {
				// System.out.printf("%d번 말이 %d로 이동하려 했으나, %d번 말(%d)과 겹쳐 실패했습니다.\n", k, now, i,
				// knight[i]);
				return new StateVO(false, knight[k]);
			}
		}

		return new StateVO(true, now);
	}

	public static void initBoard() {
		for (int i = 0; i < 33; i++) {
			map[i] = new Board(i, bVal[i]);
		}

		for (int i = 0; i <= 19; i++) {
			map[i].next = map[i + 1];
		}

		map[20].next = map[32];

		for (int i = 21; i <= 23; i++) {
			map[i].next = map[i + 1];
		}

		map[24].next = map[30];

		for (int i = 27; i >= 25; i--) {
			map[i].next = map[i - 1];
		}

		map[28].next = map[29];
		map[29].next = map[24];
		map[30].next = map[31];
		map[31].next = map[20];

		map[5].bNext = map[21];
		map[10].bNext = map[28];
		map[15].bNext = map[27];

		// printBoard();
	}

	public static void printBoard() {
		for (int i = 0; i < 32; i++) {
			System.out.printf("%d: %d의 다음은 %d\n", map[i].idx, map[i].val, map[i].next.val);
			if (map[i].blue) {
				System.out.printf("    %d의 blue는 %d\n", map[i].val, map[i].bNext.val);
			}
		}
	}

	public static void DFS(int depth) {
		if (depth == 10) {
			// printComb();
			answer = Math.max(answer, score);
			return;
		}

		for (int i = 0; i < 4; i++) {
			comb[depth] = i;
			StateVO stv = movable(i, cmd[depth]);

			if (stv.state) {
				int tmp = knight[i];
				knight[i] = stv.idx;
				score += map[knight[i]].val;
				// System.out.printf("#%d %d번 말 %d칸 이동 %d(%d점) -> %d(%d점)\t점수: %d점 -> %d점\n",
				// depth, i, cmd[depth], tmp, map[tmp].val, knight[i], map[knight[i]].val, score
				// - map[knight[i]].val, score);
				DFS(depth + 1);
				score -= map[knight[i]].val;
				knight[i] = tmp;
			}
		}
	}

	public static void printComb() {
		System.out.println();

		for (int i = 0; i < 10; i++) {
			System.out.printf("%d ", comb[i]);
		}

		System.out.println();
	}
}