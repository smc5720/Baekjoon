import java.io.*;
import java.util.*;

public class Main {
	public static class Node {
		int num; 
		boolean color, visited;
		ArrayList<Node> friends;

		public Node(int num, boolean visited) {
			this.num = num;
			this.visited = visited;
			this.friends = new ArrayList<Node>();
		}

		public void addFriend(Node n) {
			this.friends.add(n);
		}

		public void setColor(boolean color) {
			this.color = color;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			Node[] node = new Node[V + 1];

			for (int v = 1; v <= V; v++) {
				node[v] = new Node(v, false);
			}

			for (int e = 0; e < E; e++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				node[a].addFriend(node[b]);
				node[b].addFriend(node[a]);
			}

			boolean state = false;

			// 모든 정점이 연결된 그래프가 아닐 경우를 대비
			for (int i = 1; i <= V; i++) {
				if (!BFS(node, i)) {
					bw.write("NO\n");
					state = true;
				}

				if (state) {
					break;
				}
			}

			if (state) {
				continue;
			}

			bw.write("YES\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static boolean BFS(Node[] node, int start) {
		if (node[start].visited == true) {
			return true;
		}

		Queue<Node> queue = new LinkedList<Node>();
		queue.add(node[start]);
		node[start].visited = true;
		node[start].setColor(true);

		while (!queue.isEmpty()) {
			Node nowNode = queue.poll();

			for (Node n : nowNode.friends) {
				if (n.visited == false) {
					n.visited = true;
					n.setColor(!nowNode.color);
					queue.add(n);
				} else {
					if (nowNode.color == n.color) {
						return false;
					}
				}
			}
		}

		return true;
	}
}