import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Integer num = Integer.parseInt(br.readLine());
		// 현재 몇번째 숫자인지 저장한다.
		int count = 0;
		// 검사할 숫자를 저장한다.
		int n = 665;

		while (count != num) {
			n += 1;

			if (check(n)) {
				count += 1;
			}
		}
		
		System.out.println(n);
		br.close();
	}

	public static boolean check(Integer n) {
		String s = n.toString();

		if (s.contains("666")) {
			return true;
		}

		return false;
	}
}