import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int N;
	public static int maxDest, maxTotal;
	public static boolean[] visited;
	public static Node[] node;
	public static HashMap<Point, Integer> hashMap;

	public static class Node {
		int val;
		ArrayList<Integer> friends;

		public Node(int val) {
			this.val = val;
			friends = new ArrayList<Integer>();
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		if (N == 1) {
			bw.write("0");
		} else {

			node = new Node[N + 1];
			visited = new boolean[N + 1];

			hashMap = new HashMap<Point, Integer>();
			maxTotal = 0;

			for (int i = 1; i <= N; i++) {
				node[i] = new Node(i);
			}

			for (int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(br.readLine(), " ");

				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());

				node[a].friends.add(b);
				node[b].friends.add(a);

				hashMap.put(new Point(a, b), c);
				hashMap.put(new Point(b, a), c);
			}

			initVisited();
			DFS(1, 0);
			// System.out.printf("루트에서 가장 먼 노드: %d(거리: %d)\n", maxDest, maxTotal);

			initVisited();
			DFS(maxDest, 0);
			// System.out.printf("이전 노드에서 가장 먼 노드: %d(거리: %d)\n", maxDest, maxTotal);

			bw.write(String.valueOf(maxTotal));
		}

		bw.flush();
		br.close();
		bw.close();
	}

	public static void initVisited() {
		for (int i = 1; i <= N; i++) {
			visited[i] = false;
		}
	}

	public static void DFS(int start, int weight) {
		if (weight > maxTotal) {
			maxTotal = weight;
			maxDest = start;
		}

		visited[start] = true;

		for (int dest : node[start].friends) {
			if (visited[dest]) {
				continue;
			}

			DFS(dest, weight + hashMap.get(new Point(start, dest)));
		}
	}
}