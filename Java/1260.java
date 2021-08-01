import java.io.*;
import java.util.*;

public class Main {
	public static Node[] node1; 
	public static Node[] node2;
	public static ArrayList<Integer> result;

	public static class Node implements Comparable<Node> {
		int v;
		boolean visited;
		PriorityQueue<Node> friends;

		public Node(int v) {
			this.v = v;
			this.visited = false;
			friends = new PriorityQueue<Node>();
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.v, o.v);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());

		node1 = new Node[N + 1];
		node2 = new Node[N + 1];

		for (int n = 1; n <= N; n++) {
			node1[n] = new Node(n);
			node2[n] = new Node(n);
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			node1[a].friends.add(node1[b]);
			node1[b].friends.add(node1[a]);
			node2[a].friends.add(node2[b]);
			node2[b].friends.add(node2[a]);
		}

		result = new ArrayList<Integer>();
		node1[start].visited = true;

		DFS(start);

		for (int i = 0; i < result.size(); i++) {
			System.out.printf("%d ", result.get(i));
		}

		System.out.println();

		result = new ArrayList<Integer>();
		node2[start].visited = true;

		BFS(start);

		for (int i = 0; i < result.size(); i++) {
			System.out.printf("%d ", result.get(i));
		}

		System.out.println();

		bw.flush();
		bw.close();
		br.close();
	}

	public static void DFS(int start) {
		result.add(start);

		while (!node1[start].friends.isEmpty()) {
			Node nd = node1[start].friends.poll();

			if (!nd.visited) {
				node1[nd.v].visited = true;
				DFS(nd.v);
			}
		}
	}

	public static void BFS(int start) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(node2[start]);

		while (!queue.isEmpty()) {
			Node nd = queue.poll();
			result.add(nd.v);

			while (!nd.friends.isEmpty()) {
				Node fn = nd.friends.poll();

				if (!fn.visited) {
					node2[fn.v].visited = true;
					queue.add(fn);
				}
			}
		}
	}
}