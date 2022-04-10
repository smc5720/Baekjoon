import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;

	public static class Node {
		int n;
		Node left, right;

		public Node(int n) {
			this.n = n;
		}

		public void insert(int n) {
			if (this.n < n) {
				if (right == null) {
					right = new Node(n);
				} else {
					right.insert(n);
				}
			} else {
				if (left == null) {
					left = new Node(n);
				} else {
					left.insert(n);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		Node root = new Node(Integer.parseInt(br.readLine()));
		String input = br.readLine();

		while (input != null && !input.equals("")) {
			root.insert(Integer.parseInt(input));
			input = br.readLine();
		}

		postOrder(root);

		br.close();
		bw.close();
	}

	public static void postOrder(Node node) {
		if (node == null) {
			return;
		}

		postOrder(node.left);
		postOrder(node.right);
		System.out.println(node.n);
	}
}