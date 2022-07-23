import java.io.*;
import java.util.*;

public class Main {
	public static int N, M;
	public static int[] train;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		train = new int[N + 1];

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");

			int cmd = Integer.parseInt(st.nextToken());
			int i = Integer.parseInt(st.nextToken());

			// i번째 기차에(1 ≤ i ≤ N) x번째 좌석에(1 ≤ x ≤ 20) 사람을 태워라.
			// 이미 사람이 타있다면, 아무런 행동을 하지 않는다.
			if (cmd == 1) {
				int x = Integer.parseInt(st.nextToken());
				train[i] |= (1 << x);
			}

			// i번째 기차에 x번째 좌석에 앉은 사람은 하차한다.
			// 만약 아무도 그자리에 앉아있지 않았다면, 아무런 행동을 하지 않는다.
			else if (cmd == 2) {
				int x = Integer.parseInt(st.nextToken());
				train[i] &= ~(1 << x);
			}

			// i번째 기차에 앉아있는 승객들이 모두 한칸씩 뒤로간다.
			// k번째 앉은 사람은 k+1번째로 이동하여 앉는다.
			// 만약 20번째 자리에 사람이 앉아있었다면, 그 사람은 이 명령 후에 하차한다.
			else if (cmd == 3) {
				train[i] <<= 1;
				train[i] &= ~(1 << 21);
			}

			// i번째 기차에 앉아있는 승객들이 모두 한칸씩 앞으로간다.
			// k번째 앉은 사람은 k-1 번째 자리로 이동하여 앉는다.
			// 만약 1번째 자리에 사람이 앉아있었다면, 그 사람은 이 명령 후에 하차한다.
			else if (cmd == 4) {
				train[i] >>= 1;
				train[i] &= ~1;
			}

			// printTrain();
		}

		Set<Integer> trainList = new HashSet<Integer>();

		for (int i = 1; i <= N; i++) {
			trainList.add(train[i]);
		}

		bw.write(String.valueOf(trainList.size()));
		bw.flush();
		bw.close();
		br.close();
	}

	public static void printTrain() {
		for (int i = 1; i <= N; i++) {
			System.out.printf("%d's train: ", i);

			for (int j = 1; j <= 20; j++) {
				if ((train[i] & (1 << j)) > 0) {
					System.out.printf("1 ");
				} else {
					System.out.printf("0 ");
				}
			}

			System.out.println();
		}

		System.out.println();
	}
}