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

	public static class Node {
		private int parent = 0;
		private ArrayList<Integer> link = new ArrayList<Integer>();
		private boolean visited = false;

		public int getParent() {
			return parent;
		}

		public void setParent(int n) {
			parent = n;
		}

		public boolean isVisited() {
			return visited;
		}

		public void visit() {
			visited = true;
		}

		public void addLink(int n) {
			link.add(n);
		}

		public Integer[] getLink() {
			return link.toArray(new Integer[link.size()]);
		}
	}

	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		Node[] node = new Node[N + 1];

		for (int i = 1; i <= N; i++) {
			node[i] = new Node();
		}

		node[1].setParent(1);

		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			node[a].addLink(b);
			node[b].addLink(a);
		}

		DFS(1, node);

		for (int i = 2; i <= N; i++) {
			bw.write(String.valueOf(node[i].getParent()));
			bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static void DFS(int start, Node[] node) {
		Integer[] arr = node[start].getLink();
		node[start].visit();

		for (int i = 0; i < arr.length; i++) {
			// 방문하지 않았다면
			if (!node[arr[i]].isVisited()) {
				node[arr[i]].setParent(start);
				DFS(arr[i], node);
			}
		}
	}
}
