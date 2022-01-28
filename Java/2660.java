import java.io.*;
import java.util.*;

public class Main {
	public static int N;
	public static Node[] node;

	public static class Node {
		int v, depth;
		boolean visited;
		ArrayList<Node> friends;

		public Node(int v) {
			this.v = v;
			this.depth = 0;
			visited = false;
			friends = new ArrayList<Node>();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		node = new Node[N + 1];

		for (int i = 1; i <= N; i++) {
			node[i] = new Node(i);
		}

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		while (a != -1 || b != -1) {
			node[a].friends.add(node[b]);
			node[b].friends.add(node[a]);

			st = new StringTokenizer(br.readLine(), " ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
		}

		ArrayList<Integer> answer = new ArrayList<Integer>();
		int minScore = N + 1;

		for (int i = 1; i <= N; i++) {
			int nowScore = BFS(i);

			if (minScore > nowScore) {
				minScore = nowScore;
				answer = new ArrayList<Integer>();
				answer.add(i);
			} else if (minScore == nowScore) {
				answer.add(i);
			}
		}

		bw.write(minScore + " " + answer.size());
		bw.newLine();

		for (int i = 0; i < answer.size(); i++) {
			bw.write(answer.get(i) + " ");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static int BFS(int start) {
		int result = 0;

		for (int i = 1; i <= N; i++) {
			node[i].visited = false;
			node[i].depth = 0;
		}

		Queue<Node> queue = new LinkedList<Node>();

		node[start].visited = true;
		queue.add(node[start]);

		while (!queue.isEmpty()) {
			Node nd = queue.poll();
			result = nd.depth;

			for (Node friend : nd.friends) {
				if (!friend.visited) {
					friend.visited = true;
					friend.depth = nd.depth + 1;
					queue.add(friend);
				}
			}
		}

		return result;
	}
}