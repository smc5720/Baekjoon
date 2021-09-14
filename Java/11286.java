import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				int abs1 = Math.abs(o1);
				int abs2 = Math.abs(o2);

				if (abs1 == abs2) {
					return Integer.compare(o1, o2);
				}

				return Integer.compare(abs1, abs2);
			}
		});

		for (int i = 0; i < N; i++) {
			int tmp = Integer.parseInt(br.readLine());
			if (tmp == 0) {
				if (pq.isEmpty()) {
					bw.write("0");
				} else {
					bw.write(String.valueOf(pq.poll()));
				}
				bw.newLine();
			} else {
				pq.add(tmp);
			}
		}

		bw.flush();
		bw.close();
		br.close();
	}
}