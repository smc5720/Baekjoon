import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		Integer N = Integer.parseInt(br.readLine());
		Integer answer = 0;

		for (int i = 1; i <= N; i++) {
			if (check(i)) {
				answer += 1;
			}
		}

		bw.write(answer.toString());
		
		bw.flush();
		br.close();
		bw.close();
	}

	public static boolean check(Integer num) {
		String s = num.toString();

		// 길이가 1이면 무조건 true
		if (s.length() == 1) {
			return true;
		}

		int num1 = s.charAt(1) - '0';
		int num0 = s.charAt(0) - '0';

		// 1번째 수에서 0번째 수를 빼고 그 차를 저장한다.
		int gap = num1 - num0;

		for (int i = 1; i < s.length(); i++) {
			num1 = s.charAt(i) - '0';
			num0 = s.charAt(i - 1) - '0';

			// 차가 다른 곳이 존재한다면 등차수열이 아님을 의미한다.
			if (gap != num1 - num0) {
				return false;
			}
		}

		return true;
	}
}