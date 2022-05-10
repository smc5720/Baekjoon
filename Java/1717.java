import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int n, m;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken()); 
		m = Integer.parseInt(st.nextToken());

		parent = new int[n + 1];

		for (int i = 0; i <= n; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int cmd = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (cmd == 0) {
				union(a, b);
			} else {
				if (find(a, b)) {
					bw.write("YES");
				} else {
					bw.write("NO");
				}
				bw.newLine();
			}
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static int getParent(int a) {
		if (parent[a] == a) {
			return a;
		}

		return parent[a] = getParent(parent[a]);
	}

	public static void union(int a, int b) {
		a = getParent(a);
		b = getParent(b);

		if (a < b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}

	public static boolean find(int a, int b) {
		return getParent(a) == getParent(b);
	}
}