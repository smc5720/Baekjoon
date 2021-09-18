import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());

		Coordinate[] c = new Coordinate[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			c[i] = new Coordinate(x, y);
		}

		Arrays.sort(c);

		for (int i = 0; i < N; i++) {
			String tmp = String.valueOf(c[i].x) + " " + String.valueOf(c[i].y);
			bw.write(tmp);
			bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();
	}

	static class Coordinate implements Comparable<Coordinate> {

		public int x, y;

		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Coordinate o) {
			if (o.x == this.x) {
				return Integer.compare(this.y, o.y);
			}
			return Integer.compare(this.x, o.x);
		}
	}
}