import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int N, M, H, answer;
	public static boolean[][] line;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		line = new boolean[H + 1][N + 1];
		answer = -1;

		for (int h = 0; h <= H; h++) {
			for (int n = 0; n <= N; n++) {
				line[h][n] = false;
			}
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			line[a][b] = true;
		}

		// printLine();

		if (startGame()) {
			bw.write("0");
		} else {
			DFS(0);
			bw.write(String.valueOf(answer));
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static void DFS(int depth) {
		if (depth == 3) {
			return;
		}

		for (int h = 1; h <= H; h++) {
			for (int n = 1; n < N; n++) {
				if (!line[h][n] && installable(h, n)) {
					// System.out.printf("%d %d 사다리 설치: 현재 사다리 %d개\n", h, n, depth + 1);
					line[h][n] = true;
					// printLine();
					if (startGame()) {
						// System.out.printf("현재 %d개의 사다리가 추가되었습니다.\n", depth + 1);
						setAnswer(depth + 1);
						line[h][n] = false;
						return;
					}
					DFS(depth + 1);
					// System.out.printf("%d %d 사다리 제거: 현재 사다리 %d개\n", h, n, depth);
					line[h][n] = false;
					// printLine();
				}
			}
		}
	}

	public static void setAnswer(int n) {
		if (answer == -1) {
			answer = n;
		} else {
			answer = Math.min(answer, n);
		}
	}

	public static boolean installable(int h, int n) {
		if (n - 1 > 0 && line[h][n - 1]) {
			return false;
		} else if (line[h][n + 1]) {
			return false;
		}

		return true;
	}

	// i번 세로선의 결과가 i번이 나오는지 확인한다.
	public static boolean startGame() {
		for (int n = 1; n <= N; n++) {
			if (n != check(n)) {
				return false;
			}
		}

		return true;
	}

	public static int check(int start) {
		int now = start;

		for (int i = 1; i <= H; i++) {
			// 왼쪽으로 갈 수 있는가?
			if (now - 1 > 0 && line[i][now - 1]) {
				now -= 1;
				// System.out.printf("%d번 가로선에서 L 방향으로 이동: %d → %d\n", i, now + 1, now);
				continue;
			}
			// 오른쪽으로 갈 수 있는가?
			if (line[i][now]) {
				now += 1;
				// System.out.printf("%d번 가로선에서 R 방향으로 이동: %d → %d\n", i, now - 1, now);
				continue;
			}
		}

		// System.out.printf("%d번 출발 → %d번 도착\n\n", start, now);
		return now;
	}

	public static void printLine() {
		System.out.println();

		for (int h = 0; h <= H; h++) {
			for (int n = 0; n <= N; n++) {
				if (h == 0) {
					if (n == 0) {
						System.out.printf("   ", n);
					} else {
						System.out.printf("%d  ", n);
					}
					continue;
				}

				if (n == 0) {
					System.out.printf("%d  ", h);
					continue;
				}

				System.out.printf("|");

				if (line[h][n]) {
					System.out.printf("--");
				} else {
					System.out.printf("  ");
				}
			}

			System.out.println();
		}

		System.out.println();
	}
}