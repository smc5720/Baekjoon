import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int N, M, T;
	public static Num[][] map;
	public static boolean[][] visited;
	public static int[] dirN = { 0, 0, -1, 1 };
	public static int[] dirM = { -1, 1, 0, 0 };
	public static boolean changed;
	public static ArrayList<Num> list;

	public static class Num {
		int n, m, val, dir;
		boolean disabled;

		public Num(int n, int m, int val) {
			this.n = n;
			this.m = m;
			this.val = val;
			this.disabled = false;
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new Num[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1];

		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int m = 1; m <= M; m++) {
				map[n][m] = new Num(n, m, Integer.parseInt(st.nextToken()));
			}
		}

		// printMap();

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine(), " ");

			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			// System.out.printf("test %d\n\n", t + 1);
			initState();
			rotate(x, d, k);
			// System.out.printf("돌렸습니다.\n\n", t + 1);
			// printMap();

			for (int n = 1; n <= N; n++) {
				for (int m = 1; m <= M; m++) {
					if (!map[n][m].disabled) {
						BFS(n, m);
					}
				}
			}

			for (Num num : list) {
				num.disabled = true;
			}

			if (!changed) {
				double avg = 0;
				int cnt = 0;

				for (int n = 1; n <= N; n++) {
					for (int m = 1; m <= M; m++) {
						if (!map[n][m].disabled) {
							avg += map[n][m].val;
							cnt += 1;
						}
					}
				}

				if (cnt > 0) {
					avg /= cnt;
				}

				for (int n = 1; n <= N; n++) {
					for (int m = 1; m <= M; m++) {
						if (!map[n][m].disabled) {
							if (map[n][m].val > avg) {
								map[n][m].val -= 1;
							} else if (map[n][m].val < avg) {
								map[n][m].val += 1;
							}
						}
					}
				}
			}

			// printMap();
		}

		int answer = 0;

		for (int n = 1; n <= N; n++) {
			for (int m = 1; m <= M; m++) {
				if (!map[n][m].disabled) {
					answer += map[n][m].val;
				}
			}
		}

		bw.write(String.valueOf(answer));

		bw.flush();
		bw.close();
		br.close();
	}

	public static void initState() {
		changed = false;

		for (int n = 1; n <= N; n++) {
			for (int m = 1; m <= M; m++) {
				visited[n][m] = false;
			}
		}

		list = new ArrayList<Num>();
	}

	// x의 배수인 원판을 d 방향으로 k번 회전시킨다.
	public static void rotate(int x, int d, int k) {
		for (int j = x; j <= N; j += x) {
			for (int K = 0; K < k; K++) {
				// 시계 방향일 때
				if (d == 0) {
					int tmp = map[j][M].val;
					boolean dis = map[j][M].disabled;

					for (int i = M; i > 1; i--) {
						map[j][i].val = map[j][i - 1].val;
						map[j][i].disabled = map[j][i - 1].disabled;
					}

					map[j][1].val = tmp;
					map[j][1].disabled = dis;
				}

				// 반시계 방향일 때
				else {
					int tmp = map[j][1].val;
					boolean dis = map[j][1].disabled;

					for (int i = 1; i < M; i++) {
						map[j][i].val = map[j][i + 1].val;
						map[j][i].disabled = map[j][i + 1].disabled;
					}

					map[j][M].val = tmp;
					map[j][M].disabled = dis;
				}
			}
		}
	}

	// n번째가 d 방향으로 k번 회전했을 때의 index를 반환
	public static int getNewPos(int n, int d, int k) {
		int idx = 0;

		// 시계 방향일 때
		if (d == 0) {
			idx = n + k;

			if (idx > M) {
				idx -= M;
			}
		} else {
			idx = n - k;

			if (idx < 1) {
				idx += M;
			}
		}

		return idx;
	}

	public static void BFS(int n, int m) {
		Queue<Num> queue = new LinkedList<Num>();
		map[n][m].dir = 4;
		queue.add(map[n][m]);
		visited[n][m] = true;

		while (!queue.isEmpty()) {
			Num num = queue.poll();

			// printMap();
			// System.out.printf("안녕 난 %d, %d이야.\n", num.n, num.m);

			if (num.dir == 4) {
				for (int i = 0; i < 4; i++) {
					int tmpN = num.n + dirN[i];
					int tmpM = num.m + dirM[i];

					if (tmpN < 1) {
						continue;
					} else if (tmpN > N) {
						continue;
					}

					if (tmpM < 1) {
						tmpM += M;
					} else if (tmpM > M) {
						tmpM -= M;
					}

					if (map[tmpN][tmpM].val == num.val && !map[tmpN][tmpM].disabled) {
						if (!visited[tmpN][tmpM]) {
							changed = true;

							if (num.dir == 4) {
								list.add(num);
							}

							list.add(map[tmpN][tmpM]);
							visited[tmpN][tmpM] = true;
							map[tmpN][tmpM].dir = i;
							queue.add(map[tmpN][tmpM]);
							// System.out.printf("%d, %d에서 %d, %d 넣기\n", num.n, num.m, tmpN, tmpM);
						}

						else {
							list.add(num);
							// System.out.printf("%d, %d 보고 나(%d, %d)만 넣기\n", tmpN, tmpM, num.n, num.m);
						}
					}
				}
			} else {
				int i = num.dir;

				int tmpN = num.n + dirN[i];
				int tmpM = num.m + dirM[i];

				if (tmpN < 1) {
					continue;
				} else if (tmpN > N) {
					continue;
				}

				if (tmpM < 1) {
					tmpM += M;
				} else if (tmpM > M) {
					tmpM -= M;
				}

				if (map[tmpN][tmpM].val == num.val && !map[tmpN][tmpM].disabled) {
					if (!visited[tmpN][tmpM]) {
						changed = true;

						if (num.dir == 4) {
							list.add(num);
						}

						list.add(map[tmpN][tmpM]);
						visited[tmpN][tmpM] = true;
						map[tmpN][tmpM].dir = i;
						queue.add(map[tmpN][tmpM]);
						// System.out.printf("%d, %d에서 %d, %d 넣기\n", num.n, num.m, tmpN, tmpM);
					}

					else {
						list.add(num);
						// System.out.printf("%d, %d 보고 나(%d, %d)만 넣기\n", tmpN, tmpM, num.n, num.m);
					}
				}
			}
		}
	}

	public static void printMap() {
		System.out.println();

		for (int n = 1; n <= N; n++) {
			for (int m = 1; m <= M; m++) {
				if (map[n][m].disabled) {
					System.out.printf("x ");
					continue;
				}
				System.out.printf("%d ", map[n][m].val);
			}
			System.out.println();
		}
		System.out.println();
	}
}