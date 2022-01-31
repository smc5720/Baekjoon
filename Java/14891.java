import java.io.*;
import java.util.*;

public class Main {
	public static int K;
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static Gear[] gear;
	public static final boolean showConsole = false;

	public static class Gear {
		int[] v;
		int left, right, top, dir;
		boolean shouldRotate;

		public Gear() {
			this.v = new int[8];
			this.left = 6;
			this.right = 2;
			this.top = 0;
			this.shouldRotate = false;
		}

		public void setGear(int v, int val) {
			this.v[v] = val;
		}

		public void rotate(int dir) {
			this.left += dir * (-1) + 8;
			this.left %= 8;
			this.right += dir * (-1) + 8;
			this.right %= 8;
			this.top += dir * (-1) + 8;
			this.top %= 8;
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		gear = new Gear[5];

		for (int i = 1; i <= 4; i++) {
			gear[i] = new Gear();
			String str = br.readLine();
			for (int j = 0; j < 8; j++) {
				gear[i].v[j] = str.charAt(j) - '0';
			}
		}

		K = Integer.parseInt(br.readLine());

		StringTokenizer st;

		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());

			// System.out.println("\n↓ 현재 톱니바퀴 상태입니다 ↓");
			printGear(n, dir);
			checkGear(n, dir, -1, 0);
			rotateGear();
			// System.out.printf("\n\nCommand %d 완료\n\n\n", k + 1);
		}

		bw.write(String.valueOf(calScore()));

		bw.flush();
		bw.close();
		br.close();
	}

	// n: 톱니바퀴의 번호
	// dir: 톱니바퀴가 돌아가는 방향(시계 = 1, 반시계 = -1)
	// same: 자신이 같게 맞춰야할 값(N = 0, S = 1)
	// next: 자신이 나아가야할 방향(왼쪽 = -1, 오른쪽 = 1)
	public static void checkGear(int n, int dir, int same, int next) {
		if (n == 0 || n > 4) {
			return;
		}

		int left = gear[n].v[gear[n].left];
		int right = gear[n].v[gear[n].right];

		// 시작하는 톱니바퀴
		if (same == -1) {
			gear[n].shouldRotate = true;
			gear[n].dir = dir;

			checkGear(n - 1, dir * (-1), left, -1);
			checkGear(n + 1, dir * (-1), right, 1);
		}

		// 연계되는 톱니바퀴
		else {
			// 나아가는 방향이 왼쪽이라면
			if (next == -1) {
				// same과 나의 오른쪽 톱니를 비교한다.
				if (same != right) {
					gear[n].shouldRotate = true;
					gear[n].dir = dir;
					checkGear(n - 1, dir * (-1), left, -1);
				}
			}
			// 나아가는 방향이 오른쪽이라면
			else {
				// same과 나의 왼쪽 톱니를 비교한다.
				if (same != left) {
					gear[n].shouldRotate = true;
					gear[n].dir = dir;
					checkGear(n + 1, dir * (-1), right, 1);
				}
			}
		}
	}

	public static void rotateGear() {
		for (int i = 1; i <= 4; i++) {
			if (gear[i].shouldRotate) {
				gear[i].rotate(gear[i].dir);
				gear[i].shouldRotate = false;
				printGear(i, gear[i].dir);
			}
		}
	}

	public static int calScore() {
		int sum = 0;

		for (int i = 1; i <= 4; i++) {
			int top = gear[i].v[gear[i].top];

			if (top == 1) {
				int score = (int) Math.pow(2, i - 1);
				sum += score;
			}
		}

		return sum;
	}

	public static void printGear(int n, int dir) {
		if (!showConsole) {
			return;
		}

		System.out.println();

		if (dir == -1) {
			System.out.printf("\n\n%d번 톱니바퀴, 반시계 방향으로 회전\n\n", n);
		} else {
			System.out.printf("\n\n%d번 톱니바퀴, 시계 방향으로 회전\n\n", n);
		}

		for (int i = 1; i <= 4; i++) {
			System.out.printf("    %d     ", gear[i].v[gear[i].top]);
		}

		System.out.println();

		for (int i = 1; i <= 4; i++) {
			System.out.printf("  %d   %d   ", gear[i].v[(gear[i].left + 9) % 8], gear[i].v[(gear[i].top + 9) % 8]);
		}

		System.out.println();

		for (int i = 1; i <= 4; i++) {
			System.out.printf("%d       %d ", gear[i].v[(gear[i].left)], gear[i].v[(gear[i].right)]);
		}

		System.out.println();

		for (int i = 1; i <= 4; i++) {
			System.out.printf("  %d   %d   ", gear[i].v[(gear[i].left + 7) % 8], gear[i].v[(gear[i].right + 9) % 8]);
		}

		System.out.println();

		for (int i = 1; i <= 4; i++) {
			System.out.printf("    %d     ", gear[i].v[(gear[i].right + 10) % 8]);
		}
	}
}