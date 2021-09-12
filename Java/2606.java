import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int result = 0;

	public static class Node {
		private ArrayList<Integer> link = new ArrayList<Integer>();
		private boolean visited = false;

		public Integer[] getLink() {
			return link.toArray(new Integer[link.size()]);
		}

		public void addLink(int n) {
			this.link.add(n);
		}

		public boolean isVisited() {
			return visited;
		}

		public void visit() {
			this.visited = true;
		}
	}

	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		int T = Integer.parseInt(br.readLine());

		Node[] node = new Node[N + 1];

		for (int i = 1; i <= N; i++) {
			node[i] = new Node();
		}

		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			node[a].addLink(b);
			node[b].addLink(a);
		}
		
		DFS(node, 1);
		
		bw.write(String.valueOf(result));

		bw.flush();
		bw.close();
		br.close();
	}

	public static void DFS(Node[] node, int start) {
		node[start].visit();
		Integer[] nodeLink = node[start].getLink();

		for (int i = 0; i < nodeLink.length; i++) {
			int idx = nodeLink[i];

			if (!node[idx].isVisited()) {
				result += 1;
				DFS(node, idx);
			}
		}
	}
}