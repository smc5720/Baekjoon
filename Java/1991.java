import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int N;
	public static HashMap<String, Node> node;

	public static class Node {
		String val;
		Node left, right;

		public Node(String val) {
			this.val = val;
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		node = new HashMap<String, Node>();
		StringTokenizer st;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			String name = st.nextToken();
			String left = st.nextToken();
			String right = st.nextToken();

			Node nd, lnd, rnd;

			if (!node.containsKey(name)) {
				nd = new Node(name);
				node.put(name, nd);
			} else {
				nd = node.get(name);
			}

			if (!node.containsKey(left)) {
				lnd = new Node(left);
				node.put(left, lnd);
			} else {
				lnd = node.get(left);
			}

			if (!node.containsKey(right)) {
				rnd = new Node(right);
				node.put(right, rnd);
			} else {
				rnd = node.get(right);
			}

			if (!left.equals(".")) {
				nd.left = lnd;
			}

			if (!right.equals(".")) {
				nd.right = rnd;
			}
		}

		preorder("A");
		System.out.println();
		inorder("A");
		System.out.println();
		postorder("A");

		bw.flush();
		br.close();
		bw.close();
	}

	public static void preorder(String val) {
		Node nd = node.get(val);

		System.out.printf("%s", val);

		if (nd.left != null) {
			preorder(nd.left.val);
		}

		if (nd.right != null) {
			preorder(nd.right.val);
		}
	}

	public static void inorder(String val) {
		Node nd = node.get(val);

		if (nd.left != null) {
			inorder(nd.left.val);
		}

		System.out.printf("%s", val);

		if (nd.right != null) {
			inorder(nd.right.val);
		}
	}

	public static void postorder(String val) {
		Node nd = node.get(val);

		if (nd.left != null) {
			postorder(nd.left.val);
		}

		if (nd.right != null) {
			postorder(nd.right.val);
		}

		System.out.printf("%s", val);
	}
}