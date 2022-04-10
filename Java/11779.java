import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringTokenizer st;
	public static int N, M;
	public static int[][] city;
	public static final int inf = 100000001;
	public static Path[] path;

	public static class Path {
		int n;
		ArrayList<Integer> pathList;

		public Path(int n) {
			this.n = n;
			pathList = new ArrayList<Integer>();
			pathList.add(n);
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		city = new int[N + 1][N + 1];
		path = new Path[N + 1];

		for (int a = 1; a <= N; a++) {
			path[a] = new Path(a);

			for (int b = 1; b <= N; b++) {
				if (a == b) {
					city[a][b] = 0;
				} else {
					city[a][b] = inf;
				}
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			city[a][b] = Math.min(w, city[a][b]);
		}

		st = new StringTokenizer(br.readLine(), " ");

		int start = Integer.parseInt(st.nextToken());
		int dest = Integer.parseInt(st.nextToken());

		int minVal = dijkstra(start, dest);

		bw.write(String.valueOf(minVal));
		bw.newLine();

		bw.write(String.valueOf(path[dest].pathList.size()));
		bw.newLine();

		StringBuilder sb = new StringBuilder();

		for (int p : path[dest].pathList) {
			sb.append(p + " ");
		}

		bw.write(sb.toString());

		br.close();
		bw.close();
	}

	public static int dijkstra(int start, int dest) {
		boolean[] visited = new boolean[N + 1];
		int[] distance = new int[N + 1];

		visited[start] = true;
		distance[start] = 0;

		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				distance[i] = city[start][i];

				ArrayList<Integer> list = new ArrayList<Integer>();
				list.add(start);
				list.add(i);

				path[i].pathList = list;
			}
		}

		for (int i = 0; i < N - 1; i++) {
			int min = inf;
			int minIdx = -1;

			// 최소값 찾기
			for (int n = 1; n <= N; n++) {
				if (!visited[n] && min >= distance[n]) {
					min = distance[n];
					minIdx = n;
				}
			}

			visited[minIdx] = true;

			for (int n = 1; n <= N; n++) {
				if (!visited[n]) {
					if (distance[n] > distance[minIdx] + city[minIdx][n]) {
						distance[n] = distance[minIdx] + city[minIdx][n];

						ArrayList<Integer> list = new ArrayList<Integer>();

						for (int p : path[minIdx].pathList) {
							list.add(p);
						}

						list.add(n);
						path[n].pathList = list;
					}
				}
			}
		}

		return distance[dest];
	}

	public static void printCity() {
		System.out.println();

		for (int a = 0; a <= N; a++) {
			for (int b = 0; b <= N; b++) {
				if (a == 0 && b == 0) {
					System.out.printf(" \t", b);
					continue;
				}

				if (a == 0) {
					System.out.printf("%d\t", b);
					continue;
				}

				if (b == 0) {
					System.out.printf("%d\t", a);
					continue;
				}

				if (city[a][b] == inf) {
					System.out.printf("inf\t");
				} else {
					System.out.printf("%d\t", city[a][b]);
				}
			}

			System.out.println();
		}

		System.out.println();
	}
}