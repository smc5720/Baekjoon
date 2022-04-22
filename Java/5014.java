import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringTokenizer st;
	public static int F, S, G, U, D;
	public static boolean[] visited;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine(), " ");

		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		visited = new boolean[F + 1];

		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(S, 0));
		visited[S] = true;

		boolean state = false;

		while (!queue.isEmpty()) {
			Point p = queue.poll();

			int floor = p.x;
			int cost = p.y;

			if (floor == G) {
				bw.write(String.valueOf(cost));
				state = true;
				break;
			}

			if (visitable(floor + U)) {
				visited[floor + U] = true;
				queue.add(new Point(floor + U, cost + 1));
			}

			if (visitable(floor - D)) {
				visited[floor - D] = true;
				queue.add(new Point(floor - D, cost + 1));
			}
		}

		if (!state) {
			bw.write("use the stairs");
		}

		br.close();
		bw.close();
	}

	public static boolean visitable(int d) {
		return 1 <= d && d <= F && !visited[d];
	}
}