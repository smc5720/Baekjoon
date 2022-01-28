import java.io.*;
import java.util.*;

public class Main {
	public static int N;
	public static PriorityQueue<Integer> maxQueue;
	public static PriorityQueue<Integer> minQueue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		minQueue = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o1, o2);
			}
		});

		maxQueue = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o2, o1);
			}
		});

		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine());

			if (i == 0) {
				maxQueue.add(n);
			} else {
				if (maxQueue.peek() < n) {
					minQueue.add(n);
				} else {
					maxQueue.add(n);
				}

				if (minQueue.size() > maxQueue.size()) {
					maxQueue.add(minQueue.poll());
				} else if (minQueue.size() + 1 < maxQueue.size()) {
					minQueue.add(maxQueue.poll());
				}
			}

			bw.write(String.valueOf(maxQueue.peek()) + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}
}