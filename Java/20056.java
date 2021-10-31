import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N, M, K;
	static int[][] map;
	static HashMap<Point, Queue<Fireball>> fireballs;
	static Queue<Fireball> queue;

	public static class Fireball {
		int r, c, m, d, s;

		public Fireball(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		fireballs = new HashMap<>();
		queue = new LinkedList<Fireball>();

		map = new int[N + 1][N + 1];

		for (int y = 1; y <= N; y++) {
			for (int x = 1; x <= N; x++) {
				Point pt = new Point(x, y);
				fireballs.put(pt, new LinkedList<Fireball>());
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			Point pt = new Point(c, r);
			fireballs.get(pt).add(new Fireball(r, c, m, s, d));
		}

		// printMap();

		for (int i = 0; i < K; i++) {
			for (int y = 1; y <= N; y++) {
				for (int x = 1; x <= N; x++) {
					Point pt = new Point(x, y);
					int size = fireballs.get(pt).size();

					for (int k = 0; k < size; k++) {
						moveFireball(fireballs.get(pt).poll());
					}
				}
			}

			while (!queue.isEmpty()) {
				Fireball f = queue.poll();
				Point pt = new Point(f.c, f.r);
				fireballs.get(pt).add(f);
			}

			for (int y = 1; y <= N; y++) {
				for (int x = 1; x <= N; x++) {
					Point pt = new Point(x, y);
					Queue<Fireball> q = fireballs.get(pt);
					int size = q.size();
					if (size > 1) {
						// 파이어볼 질량의 합
						int m = 0;
						// 파이어볼 속력의 합
						int s = 0;
						// 첫 번째 파이어볼의 방향
						int d = q.peek().d;
						// 모두 방향이 홀수이거나 짝수이면 true
						boolean state = true;

						while (!q.isEmpty()) {
							Fireball f = q.poll();
							m += f.m;
							s += f.s;
							if (d % 2 != f.d % 2) {
								state = false;
							}
						}

						m /= 5;
						s /= size;

						if (m > 0) {
							int[] dir = new int[4];

							if (state) {
								for (int k = 0; k < 4; k++) {
									dir[k] = k * 2;
								}
							} else {
								for (int k = 0; k < 4; k++) {
									dir[k] = k * 2 + 1;
								}
							}

							for (int k = 0; k < 4; k++) {
								q.add(new Fireball(y, x, m, s, dir[k]));
							}
						}
					}
				}
			}

			// printMap();
		}

		int answer = 0;

		for (int y = 1; y <= N; y++) {
			for (int x = 1; x <= N; x++) {
				Point pt = new Point(x, y);
				Queue<Fireball> q = fireballs.get(pt);

				while (!q.isEmpty()) {
					answer += q.poll().m;
				}
			}
		}

		bw.write(String.valueOf(answer));

		bw.flush();
		bw.close();
		br.close();
	}

	public static void moveFireball(Fireball fireball) {
		int[] dirY = { -1, -1, 0, 1, 1, 1, 0, -1 };
		int[] dirX = { 0, 1, 1, 1, 0, -1, -1, -1 };

		// System.out.printf("(%d, %d) -> ", fireball.r, fireball.c);
		// System.out.printf("(%d, %d) -> ", dirY[fireball.d] * fireball.s,
		// dirX[fireball.d] * fireball.s);

		fireball.r += dirY[fireball.d] * fireball.s;
		fireball.c += dirX[fireball.d] * fireball.s;

		fireball.r = computePos(fireball.r);
		fireball.c = computePos(fireball.c);

		queue.add(fireball);

		// System.out.printf("(%d, %d)\n", fireball.r, fireball.c);
	}

	public static int computePos(int pos) {
		if (pos > N) {
			while (pos > N) {
				pos -= N;
			}
			return pos;
		} else if (pos < 1) {
			while (pos < 1) {
				pos += N;
			}
			return pos;
		} else {
			return pos;
		}
	}

	public static void printMap() {
		for (int y = 1; y <= N; y++) {
			for (int x = 1; x <= N; x++) {
				Point pt = new Point(x, y);
				System.out.printf("%d\t", fireballs.get(pt).size());
			}
			System.out.println();
		}
	}
}