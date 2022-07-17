import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int flag = 0;

		int[] wordBits = new int[N];

		for (int n = 0; n < N; n++) {
			String word = br.readLine();
			wordBits[n] = 0;

			for (int i = 0; i < word.length(); i++) {
				wordBits[n] |= (1 << (word.charAt(i) - 'a'));
			}
		}

		if (K < 5 || K == 26) {
			bw.write(String.valueOf(K < 5 ? 0 : N));
		} else {
			int answer = 0;
			char[] ch = { 'a', 'c', 'i', 'n', 't' };

			for (int i = 0; i < 5; i++) {
				flag |= (1 << (ch[i] - 'a'));
			}

			for (int i = 1; i < (1 << 26); i++) {
				if ((flag & i) != flag) {
					continue;
				}

				// 배운 알파벳의 수
				int cnt = 0;

				for (int j = 0; j < 26; j++) {
					if ((i & (1 << j)) == (1 << j)) {
						cnt += 1;
					}
				}

				if (cnt != K) {
					continue;
				}

				// 해당 알파벳 조합으로 읽을 수 있는 단어의 수
				cnt = 0;

				for (int j = 0; j < N; j++) {
					if ((i & wordBits[j]) == wordBits[j]) {
						cnt += 1;
					}
				}

				answer = Math.max(answer, cnt);
			}

			bw.write(String.valueOf(answer));
		}

		bw.flush();
		bw.close();
		br.close();
	}
}