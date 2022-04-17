import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringTokenizer st;
	public static int R, C, answer;
	public static char[][] map;
	public static int[] dr = { 0, 0, -1, 1 };
	public static int[] dc = { -1, 1, 0, 0 };
	public static HashMap<Character, Boolean> hashMap;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		answer = 1;
		hashMap = new HashMap<Character, Boolean>();
		map = new char[R][C];

		for (int i = 65; i <= 90; i++) {
			char ch = (char) i;
			hashMap.put(ch, false);
		}

		for (int r = 0; r < R; r++) {
			String str = br.readLine();

			for (int c = 0; c < C; c++) {
				map[r][c] = str.charAt(c);
			}
		}

		hashMap.put(map[0][0], true);
		DFS(0, 0, 1);

		bw.write(String.valueOf(answer));

		br.close();
		bw.close();
	}

	public static void DFS(int sy, int sx, int depth) {
		for (int i = 0; i < 4; i++) {
			int ty = sy + dr[i];
			int tx = sx + dc[i];

			if (visitable(ty, tx) && !hashMap.get(map[ty][tx])) {
				hashMap.put(map[ty][tx], true);
				DFS(ty, tx, depth + 1);
				hashMap.put(map[ty][tx], false);
			}
		}

		answer = Math.max(depth, answer);
	}

	public static boolean visitable(int y, int x) {
		return 0 <= y && y < R && 0 <= x && x < C;
	}
}