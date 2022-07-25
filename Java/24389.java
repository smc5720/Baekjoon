import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		int bN = ~N + 1;
		int cnt = 0;

		for (int i = 0; i < 32; i++) {
			if (((1 << i) & N) != ((1 << i) & bN)) {
				cnt += 1;
			}
		}

		bw.write(String.valueOf(cnt));
		bw.flush();
		bw.close();
		br.close();
	}
}