import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int N, K;
	public static boolean[] visited;

	public static class Position implements Comparable<Position> {
		int pos, cnt;

		public Position(int pos, int cnt) {
			this.pos = pos;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Position o) {
			return Integer.compare(this.cnt, o.cnt);
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		visited = new boolean[200000];

		PriorityQueue<Position> queue = new PriorityQueue<Position>();

		queue.add(new Position(N, 0));
		visited[N] = true;

		while (!queue.isEmpty()) {
			Position p = queue.poll();

			int pos = p.pos;
			int cnt = p.cnt;

			visited[pos] = true;

			if (pos == K) {
				bw.write(String.valueOf(cnt));
				break;
			}

			if (visitable(pos * 2) && !visited[pos * 2]) {
				queue.add(new Position(pos * 2, cnt));
			}

			if (visitable(pos + 1) && !visited[pos + 1]) {
				queue.add(new Position(pos + 1, cnt + 1));
			}

			if (visitable(pos - 1) && !visited[pos - 1]) {
				queue.add(new Position(pos - 1, cnt + 1));
			}
		}

		bw.flush();
		br.close();
		bw.close();
	}

	public static boolean visitable(int n) {
		return 0 <= n && n < 200000;
	}
}