import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static HashMap<Character, Integer> map;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String st = br.readLine();

		StringBuilder answer = new StringBuilder();
		Stack<Character> operator = new Stack<Character>();

		map = new HashMap<Character, Integer>();

		map.put('+', 0);
		map.put('-', 0);
		map.put('*', 1);
		map.put('/', 1);

		for (int i = 0; i < st.length(); i++) {
			char c = st.charAt(i);

			if ('A' <= c && c <= 'Z') {
				answer.append(c);
			} else {
				if (c == '(') {
					operator.push(c);
					continue;
				} else if (c == ')') {
					while (operator.peek() != '(') {
						answer.append(operator.pop());
					}

					operator.pop();
					continue;
				}

				if (operator.isEmpty() || operator.peek() == '(') {
					operator.push(c);
					continue;
				}

				while (!operator.isEmpty() && operator.peek() != '(' && map.get(c) <= map.get(operator.peek())) {
					answer.append(operator.pop());
				}

				operator.push(c);
			}
		}

		while (!operator.isEmpty()) {
			answer.append(operator.pop());
		}

		bw.write(answer.toString());

		bw.flush();
		br.close();
		bw.close();
	}
}