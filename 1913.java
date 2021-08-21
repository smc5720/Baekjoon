import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		Integer N = Integer.parseInt(br.readLine());
		Integer findNum = Integer.parseInt(br.readLine());

		int[][] map = new int[N][N];
		int val = 1;

		int posX = N / 2;
		int posY = N / 2;

		map[posY][posX] = val;
		val += 1;

		String dir = "Up";

		// 현재 방향으로 이동한 거리
		int moved = 0;
		// 현재 방향으로 이동해야 하는 거리
		int moveLimit = 1;

		while (val <= N * N) {
			if (dir.equals("Up")) {
				posY -= 1;
				map[posY][posX] = val;
				moved += 1;

				if (moved == moveLimit) {
					dir = "Right";
					moved = 0;
				}
			} else if (dir.equals("Right")) {
				posX += 1;
				map[posY][posX] = val;
				moved += 1;

				if (moved == moveLimit) {
					dir = "Down";
					moved = 0;
					moveLimit += 1;
				}
			} else if (dir.equals("Down")) {
				posY += 1;
				map[posY][posX] = val;
				moved += 1;

				if (moved == moveLimit) {
					dir = "Left";
					moved = 0;
				}
			} else if (dir.equals("Left")) {
				posX -= 1;
				map[posY][posX] = val;
				moved += 1;

				if (moved == moveLimit) {
					dir = "Up";
					moved = 0;
					moveLimit += 1;
				}
			}

			val += 1;
		}

		int answerX = 0;
		int answerY = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				bw.write(map[i][j] + " ");
				if (map[i][j] == findNum) {
					answerY = i + 1;
					answerX = j + 1;
				}
			}
			bw.newLine();
		}
		
		bw.write(answerY + " " + answerX);
		
		bw.flush();
		bw.close();
		br.close();
	}
}