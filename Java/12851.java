import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringTokenizer st;
	public static int N, K;

	public static class Player implements Comparable<Player> {
		int w, pos;

		public Player(int w, int pos) { 
			this.w = w;
			this.pos = pos;
		}

		@Override
		public int compareTo(Player o) {
			return Integer.compare(this.w, o.w);
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		PriorityQueue<Player> pqueue = new PriorityQueue<Player>();
		boolean[] visited = new boolean[200001];
		int[] minTime = new int[200001];

		Player player = new Player(0, N);
		pqueue.add(player);

		int cnt = 0;
		int minW = 200000;

		while (!pqueue.isEmpty()) {
			Player p = pqueue.poll();

			if (minW < p.w) {
				break;
			}

			if (p.pos == K) {
				minW = p.w;
				cnt += 1;
				continue;
			}

			if (visited[p.pos] && (minTime[p.pos] != 0 && minTime[p.pos] < p.w)) {
				continue;
			}

			visited[p.pos] = true;
			minTime[p.pos] = p.w;

			if (visitable(p.pos - 1)) {
				pqueue.add(new Player(p.w + 1, p.pos - 1));
			}

			if (visitable(p.pos + 1)) {
				pqueue.add(new Player(p.w + 1, p.pos + 1));
			}

			if (visitable(p.pos * 2)) {
				pqueue.add(new Player(p.w + 1, p.pos * 2));
			}
		}

		bw.write(minW + "\n" + cnt);

		br.close();
		bw.close();
	}

	public static boolean visitable(int pos) {
		return 0 <= pos && pos <= 200000;
	}
}