import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int N, M, isExist;
	public static Human[] human;

	public static class Human {
		int n;
		boolean visited;
		ArrayList<Integer> freind;

		public Human(int n) {
			this.n = n;
			this.visited = false;
			freind = new ArrayList<Integer>();
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		isExist = 0;

		human = new Human[N];

		for (int i = 0; i < N; i++) {
			human[i] = new Human(i);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			human[a].freind.add(b);
			human[b].freind.add(a);
		}

		for (int i = 0; i < N; i++) {
			if (isExist == 1) {
				break;
			}

			DFS(i, 0);
		}

		bw.write(String.valueOf(isExist));

		br.close();
		bw.close();
	}

	public static void DFS(int start, int depth) {
		if (depth == 4) {
			isExist = 1;
			return;
		}

		Human hm = human[start];
		hm.visited = true;

		for (int fr : hm.freind) {
			if (!human[fr].visited) {
				DFS(fr, depth + 1);
			}
		}

		hm.visited = false;
	}
}