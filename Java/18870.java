import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());

		int[] numberArray = new int[N];
		Set<Integer> numbers = new HashSet<Integer>();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < N; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			numbers.add(tmp);
			numberArray[i] = tmp;
		}

		List<Integer> numberList = new ArrayList<Integer>(numbers);
		Collections.sort(numberList);

		for (int i = 0; i < numberList.size(); i++) {
			map.put(numberList.get(i), i);
		}

		for (int i : numberArray) {
			bw.write(String.valueOf(map.get(i)) + " ");
		}

		bw.flush();
		bw.close();
		br.close();
	}
}