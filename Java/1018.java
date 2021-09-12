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

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		// N이 행의 개수, M이 열의 개수
		Integer N = Integer.parseInt(st.nextToken());
		Integer M = Integer.parseInt(st.nextToken());

		Integer answer = N * M;

		// 체스판을 저장할 2차원 배열 map
		char[][] map = new char[N][M];

		for (int y = 0; y < N; y++) {
			String tmp = new String(br.readLine());
			for (int x = 0; x < M; x++) {
				map[y][x] = tmp.charAt(x);
			}
		}

		for (int y = 0; y <= N - 8; y++) {
			for (int x = 0; x <= M - 8; x++) {
				answer = Math.min(check(y, x, map), answer);
			}
		}

		bw.write(answer.toString());

		bw.flush();
		br.close();
		bw.close();
	}

	public static int check(int startY, int startX, char[][] map) {
		// 오류 개수
		int val = 0;

		// 흰색으로 시작하는 체스판
		// map[y][x]에서 (y + x) % 2 == 0가 흰색
		for (int y = startY; y < startY + 8; y++) {
			for (int x = startX; x < startX + 8; x++) {
				if ((y + x) % 2 == 0) {
					// 흰색이여야 함
					if (map[y][x] != 'W') {
						val += 1;
					}
				} else {
					// 검은색이여야 함
					if (map[y][x] != 'B') {
						val += 1;
					}
				}
			}
		}

		// 반환 값
		int result = val;
		val = 0;

		// 검은색으로 시작하는 체스판
		// map[y][x]에서 (y + x) % 2 == 0가 검은색
		for (int y = startY; y < startY + 8; y++) {
			for (int x = startX; x < startX + 8; x++) {
				if ((y + x) % 2 == 0) {
					// 검은색이여야 함
					if (map[y][x] != 'B') {
						val += 1;
					}
				} else {
					// 흰색이여야 함
					if (map[y][x] != 'W') {
						val += 1;
					}
				}
			}
		}

		return Math.min(val, result);
	}
}
