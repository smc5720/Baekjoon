import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N, M, W;

	public static class Road {
		int start, end, time;
		boolean isWormHole;

		public Road(int start, int end, int time, boolean isWormHole) {
			this.start = start;
			this.end = end;
			this.time = time;
			this.isWormHole = isWormHole;
		}
	}

	public static void main(String[] args) throws IOException {

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < TC; tc++) {

			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			int[] minTime = new int[N + 1];
			Road[] roads = new Road[M + W];

			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine(), " ");
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				roads[m] = new Road(s, e, t, false);
			}

			for (int w = 0; w < W; w++) {
				st = new StringTokenizer(br.readLine(), " ");
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				roads[M + w] = new Road(s, e, t * (-1), true);
			}

			if (bellmanFord(minTime, roads)) {
				bw.write("YES");
			} else {
				bw.write("NO");
			}

			bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static boolean bellmanFord(int[] minTime, Road[] roads) {
		// 한 군데를 제외하고 전부 최대 시간으로 설정한다.
		Arrays.fill(minTime, 1000000000);
		minTime[1] = 0;

		// state가 true이면 값이 갱신되었음을 의미한다.
		boolean state = false;

		// edge relaxation는 (노드의 개수 - 1)번 실행한다.
		for (int n = 0; n < N - 1; n++) {
			// 모든 그래프를 방문하며 최단 시간을 초기화한다.
			for (int g = 0; g < M + W; g++) {
				Road road = roads[g];

				if (minTime[road.end] > minTime[road.start] + road.time) {
					minTime[road.end] = minTime[road.start] + road.time;
					state = true;
				}

				// 웜홀이 아니라면 반대의 경우도 계산을 해줘야 한다.
				if (!road.isWormHole) {
					if (minTime[road.start] > minTime[road.end] + road.time) {
						minTime[road.start] = minTime[road.end] + road.time;
						state = true;
					}
				}
			}

			// 값이 갱신되지 않았다면 더 이상의 edge relaxation는 의미가 없다.
			if (state == false) {
				break;
			}

			state = false;
		}

		// 마지막 한 번 더 해본다. (값이 꾸준히 감소하는지 확인하기 위해)
		for (int g = 0; g < M + W; g++) {
			Road road = roads[g];

			if (minTime[road.end] > minTime[road.start] + road.time) {
				minTime[road.end] = minTime[road.start] + road.time;
				state = true;
			}

			if (!road.isWormHole) {
				if (minTime[road.start] > minTime[road.end] + road.time) {
					minTime[road.start] = minTime[road.end] + road.time;
					state = true;
				}
			}
		}

		// 값이 갱신되지 않았다면 음의 싸이클이 존재하지 않는다.
		if (state == false) {
			return false;
		}

		return true;
	}
}