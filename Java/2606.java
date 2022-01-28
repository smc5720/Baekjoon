import java.io.*;
import java.util.*;

public class Main {
	public static class Computer {
		int num;
		boolean visited;
		ArrayList<Computer> friends;

		public Computer(int num) {
			this.num = num;
			visited = false;
			friends = new ArrayList<Computer>();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int e = Integer.parseInt(br.readLine());

		Computer[] computer = new Computer[n + 1];

		for (int i = 1; i <= n; i++) {
			computer[i] = new Computer(i);
		}

		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			computer[a].friends.add(computer[b]);
			computer[b].friends.add(computer[a]);
		}

		bw.write(String.valueOf(BFS(computer, 1)));

		bw.flush();
		bw.close();
		br.close();
	}

	public static int BFS(Computer[] computer, int start) {
		Queue<Computer> queue = new LinkedList<Computer>();
		int result = -1;

		queue.add(computer[start]);
		computer[start].visited = true;

		while (!queue.isEmpty()) {
			result += 1;
			Computer com = queue.poll();

			for (Computer c : com.friends) {
				if (!c.visited) {
					c.visited = true;
					queue.add(c);
				}
			}
		}

		return result;
	}
}