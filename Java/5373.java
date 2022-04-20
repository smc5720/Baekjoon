import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringTokenizer st;
	public static int t;
	public static int[][][] cube;
	public static String[] color = { "w", "y", "r", "o", "g", "b" };
	public static String[] dir = { "윗 면", "아랫 면", "앞 면", "뒷 면", "왼쪽 면", "오른쪽 면" };
	public static final int U = 0;
	public static final int D = 1;
	public static final int F = 2;
	public static final int B = 3;
	public static final int L = 4;
	public static final int R = 5;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		t = Integer.parseInt(br.readLine());
		cube = new int[6][3][3];

		for (int T = 0; T < t; T++) {
			// 큐브 초기화
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					for (int k = 0; k < 3; k++) {
						cube[i][j][k] = i;
					}
				}
			}

			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");

			for (int i = 0; i < n; i++) {
				String cmd = st.nextToken();

				if (cmd.charAt(1) == '-') {
					for (int j = 0; j < 3; j++) {
						rotateClockWise(cmd.charAt(0));
					}
				}

				else {
					rotateClockWise(cmd.charAt(0));
				}

				// printCube();
			}

			printUpPhase();
		}

		br.close();
		bw.close();
	}

	public static void rotateClockWise(char m) throws IOException {
		int[] tmp = new int[3];

		// U 회전 시 이동하는 칸
		// R : (2, 0) (1, 0) (0, 0)
		// B : (2, 2) (2, 1) (2, 0)
		// L : (0, 2) (1, 2) (2, 2)
		// F : (0, 0) (0, 1) (0, 2)
		if (m == 'U') {
			rotatePhase(U);

			// R → TMP
			tmp[0] = cube[R][2][0];
			tmp[1] = cube[R][1][0];
			tmp[2] = cube[R][0][0];

			// B → R
			cube[R][2][0] = cube[B][2][2];
			cube[R][1][0] = cube[B][2][1];
			cube[R][0][0] = cube[B][2][0];

			// L → B
			cube[B][2][2] = cube[L][0][2];
			cube[B][2][1] = cube[L][1][2];
			cube[B][2][0] = cube[L][2][2];

			// F → L
			cube[L][0][2] = cube[F][0][0];
			cube[L][1][2] = cube[F][0][1];
			cube[L][2][2] = cube[F][0][2];

			// TMP → F
			cube[F][0][0] = tmp[0];
			cube[F][0][1] = tmp[1];
			cube[F][0][2] = tmp[2];
		}

		// D 회전 시 이동하는 칸
		// R : (0, 2) (1, 2) (2, 2)
		// F : (2, 2) (2, 1) (2, 0)
		// L : (2, 0) (1, 0) (0, 0)
		// B : (0, 0) (0, 1) (0, 2)
		if (m == 'D') {
			rotatePhase(D);

			// R → TMP
			tmp[0] = cube[R][0][2];
			tmp[1] = cube[R][1][2];
			tmp[2] = cube[R][2][2];

			// F → R
			cube[R][0][2] = cube[F][2][2];
			cube[R][1][2] = cube[F][2][1];
			cube[R][2][2] = cube[F][2][0];

			// L → F
			cube[F][2][2] = cube[L][2][0];
			cube[F][2][1] = cube[L][1][0];
			cube[F][2][0] = cube[L][0][0];

			// B → L
			cube[L][2][0] = cube[B][0][0];
			cube[L][1][0] = cube[B][0][1];
			cube[L][0][0] = cube[B][0][2];

			// TMP → B
			cube[B][0][0] = tmp[0];
			cube[B][0][1] = tmp[1];
			cube[B][0][2] = tmp[2];
		}

		// F 회전 시 이동하는 칸
		// R : (2, 2) (2, 1) (2, 0)
		// U : (2, 2) (2, 1) (2, 0)
		// L : (2, 2) (2, 1) (2, 0)
		// D : (0, 0) (0, 1) (0, 2)
		if (m == 'F') {
			rotatePhase(F);

			// R → TMP
			tmp[0] = cube[R][2][2];
			tmp[1] = cube[R][2][1];
			tmp[2] = cube[R][2][0];

			// U → R
			cube[R][2][2] = cube[U][2][2];
			cube[R][2][1] = cube[U][2][1];
			cube[R][2][0] = cube[U][2][0];

			// L → U
			cube[U][2][2] = cube[L][2][2];
			cube[U][2][1] = cube[L][2][1];
			cube[U][2][0] = cube[L][2][0];

			// D → L
			cube[L][2][2] = cube[D][0][0];
			cube[L][2][1] = cube[D][0][1];
			cube[L][2][0] = cube[D][0][2];

			// TMP → D
			cube[D][0][0] = tmp[0];
			cube[D][0][1] = tmp[1];
			cube[D][0][2] = tmp[2];
		}

		// B 회전 시 이동하는 칸
		// R : (0, 0) (0, 1) (0, 2)
		// D : (2, 2) (2, 1) (2, 0)
		// L : (0, 0) (0, 1) (0, 2)
		// U : (0, 0) (0, 1) (0, 2)
		if (m == 'B') {
			rotatePhase(B);

			// R → TMP
			tmp[0] = cube[R][0][0];
			tmp[1] = cube[R][0][1];
			tmp[2] = cube[R][0][2];

			// D → R
			cube[R][0][0] = cube[D][2][2];
			cube[R][0][1] = cube[D][2][1];
			cube[R][0][2] = cube[D][2][0];

			// L → D
			cube[D][2][2] = cube[L][0][0];
			cube[D][2][1] = cube[L][0][1];
			cube[D][2][0] = cube[L][0][2];

			// U → L
			cube[L][0][0] = cube[U][0][0];
			cube[L][0][1] = cube[U][0][1];
			cube[L][0][2] = cube[U][0][2];

			// TMP → U
			cube[U][0][0] = tmp[0];
			cube[U][0][1] = tmp[1];
			cube[U][0][2] = tmp[2];
		}

		// L 회전 시 이동하는 칸
		// U : (2, 0) (1, 0) (0, 0)
		// B : (2, 0) (1, 0) (0, 0)
		// D : (2, 0) (1, 0) (0, 0)
		// F : (2, 0) (1, 0) (0, 0)
		if (m == 'L') {
			rotatePhase(L);

			// U → TMP
			tmp[0] = cube[U][2][0];
			tmp[1] = cube[U][1][0];
			tmp[2] = cube[U][0][0];

			// B → U
			cube[U][2][0] = cube[B][2][0];
			cube[U][1][0] = cube[B][1][0];
			cube[U][0][0] = cube[B][0][0];

			// D → B
			cube[B][2][0] = cube[D][2][0];
			cube[B][1][0] = cube[D][1][0];
			cube[B][0][0] = cube[D][0][0];

			// F → D
			cube[D][2][0] = cube[F][2][0];
			cube[D][1][0] = cube[F][1][0];
			cube[D][0][0] = cube[F][0][0];

			// TMP → F
			cube[F][2][0] = tmp[0];
			cube[F][1][0] = tmp[1];
			cube[F][0][0] = tmp[2];
		}

		// R 회전 시 이동하는 칸
		// U : (0, 2) (1, 2) (2, 2)
		// F : (0, 2) (1, 2) (2, 2)
		// D : (0, 2) (1, 2) (2, 2)
		// B : (0, 2) (1, 2) (2, 2)
		if (m == 'R') {
			rotatePhase(R);

			// U → TMP
			tmp[0] = cube[U][0][2];
			tmp[1] = cube[U][1][2];
			tmp[2] = cube[U][2][2];

			// F → U
			cube[U][0][2] = cube[F][0][2];
			cube[U][1][2] = cube[F][1][2];
			cube[U][2][2] = cube[F][2][2];

			// D → F
			cube[F][0][2] = cube[D][0][2];
			cube[F][1][2] = cube[D][1][2];
			cube[F][2][2] = cube[D][2][2];

			// B → D
			cube[D][0][2] = cube[B][0][2];
			cube[D][1][2] = cube[B][1][2];
			cube[D][2][2] = cube[B][2][2];

			// TMP → B
			cube[B][0][2] = tmp[0];
			cube[B][1][2] = tmp[1];
			cube[B][2][2] = tmp[2];
		}
	}

	public static void rotatePhase(int m) {
		int[] tmp = new int[3];

		// (0, 2) (0, 1) (0, 0) → TMP
		tmp[0] = cube[m][0][2];
		tmp[1] = cube[m][0][1];
		tmp[2] = cube[m][0][0];

		// (0, 0) (1, 0) (2, 0) → (0, 2) (0, 1) (0, 0)
		cube[m][0][2] = cube[m][0][0];
		cube[m][0][1] = cube[m][1][0];
		cube[m][0][0] = cube[m][2][0];

		// (2, 0) (2, 1) (2, 2) → (0, 0) (1, 0) (2, 0)
		cube[m][0][0] = cube[m][2][0];
		cube[m][1][0] = cube[m][2][1];
		cube[m][2][0] = cube[m][2][2];

		// (2, 2) (1, 2) (0, 2) → (2, 0) (2, 1) (2, 2)
		cube[m][2][0] = cube[m][2][2];
		cube[m][2][1] = cube[m][1][2];
		cube[m][2][2] = cube[m][0][2];

		// TMP → (2, 2) (1, 2) (0, 2)
		cube[m][2][2] = tmp[0];
		cube[m][1][2] = tmp[1];
		cube[m][0][2] = tmp[2];
	}

	public static void printCube() {
		System.out.println();

		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 9; j++) {
				if (3 <= j && j < 6) {
					if (0 <= i && i < 3) {
						System.out.printf("%s", color[cube[B][i][j - 3]]);
						continue;
					}

					if (3 <= i && i < 6) {
						System.out.printf("%s", color[cube[U][i - 3][j - 3]]);
						continue;
					}

					if (6 <= i && i < 9) {
						System.out.printf("%s", color[cube[F][i - 6][j - 3]]);
						continue;
					}

					if (9 <= i && i < 12) {
						System.out.printf("%s", color[cube[D][i - 9][j - 3]]);
						continue;
					}
				}

				if (3 <= i && i < 6) {
					if (0 <= j && j < 3) {
						System.out.printf("%s", color[cube[L][i - 3][j]]);
						continue;
					}

					if (6 <= j && j < 9) {
						System.out.printf("%s", color[cube[R][i - 3][j - 6]]);
						continue;
					}
				}

				System.out.printf(" ");
			}

			System.out.println();
		}

		System.out.println();
	}

	public static void printUpPhase() throws IOException {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				bw.write(color[cube[U][i][j]]);
			}

			bw.newLine();
		}
	}
}