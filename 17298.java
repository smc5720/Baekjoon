import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int size = Integer.parseInt(br.readLine());
		// 수열을 저장할 배열
		int[] arr = new int[size];
		// 정답을 저장할 배열
		int[] result = new int[size];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < size; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Stack<Integer> stack = new Stack<Integer>();

		for (int i = size - 1; i >= 0; i--) {
			// stack이 비었거나, stack의 top이 현재 수보다 크면 정지
			while (!stack.empty() && stack.peek() <= arr[i]) {
				stack.pop();
			}
			
			if (stack.empty()) {
				result[i] = -1;
			} else {
				result[i] = stack.peek();
			}
			
			stack.push(arr[i]);
		}

		for (int i = 0; i < size; i++) {
			bw.write(String.valueOf(result[i]) + " ");
		}

		bw.flush();
		bw.close();
		br.close();
	}

}
