import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int N, M;
	public static int[][] map;
	public static boolean[][] visited;
	public static Passenger[] passenger;
	public static int[] dirY = { 0, 0, -1, 1 };
	public static int[] dirX = { -1, 1, 0, 0 };
	public static Taxi mainTaxi;

	public static class Passenger implements Comparable<Passenger> {
		int startY, startX, destY, destX, remain, moved;
		boolean isArrived;

		public Passenger(int startY, int startX, int destY, int destX) {
			this.startY = startY;
			this.startX = startX;
			this.destY = destY;
			this.destX = destX;
			this.isArrived = false;
		}

		@Override
		public int compareTo(Passenger p) {
			if (this.moved == p.moved) {
				if (this.startY == p.startY) {
					return Integer.compare(this.startX, p.startX);
				}

				return Integer.compare(this.startY, p.startY);
			}
			return Integer.compare(this.moved, p.moved);
		}
	}

	public static class Taxi {
		int y, x, remainFuel, usedFuel;
		boolean isRide, isRetired;
		int destY, destX, pNum;

		public Taxi(int y, int x, int remainFuel, int usedFuel) {
			this.y = y;
			this.x = x;
			this.remainFuel = remainFuel;
			this.usedFuel = usedFuel;
			this.isRetired = false;
			this.isRide = false;
		}

		public void move(int y, int x, int remainFuel, int usedFuel) {
			this.y = y;
			this.x = x;
			this.remainFuel = remainFuel;
			this.usedFuel = usedFuel;
		}

		public void ridePassenger(int i) {
			this.pNum = i;
			this.destY = passenger[i].destY;
			this.destX = passenger[i].destX;
			this.usedFuel = 0;
			this.isRide = true;
		}

		public void retire() {
			this.isRetired = true;
		}

		public void chargeFuel() {
			remainFuel += 2 * usedFuel;
			usedFuel = 0;
		}

		@Override
		public String toString() {
			if (isRide) {
				return "택시(" + pNum + "번 탑승 중)\n남은 연료: " + remainFuel + "\n사용 연료: " + usedFuel + "\n";
			}

			return "택시\n남은 연료: " + remainFuel + "\n사용 연료: " + usedFuel + "\n";
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int fuel = Integer.parseInt(st.nextToken());

		passenger = new Passenger[M + 1];
		map = new int[N + 1][N + 1];
		visited = new boolean[N + 1][N + 1];

		for (int y = 1; y <= N; y++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int x = 1; x <= N; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());

				if (map[y][x] == 1) {
					map[y][x] *= -1;
				}
			}
		}

		int startY, startX;

		st = new StringTokenizer(br.readLine(), " ");

		startY = Integer.parseInt(st.nextToken());
		startX = Integer.parseInt(st.nextToken());

		mainTaxi = new Taxi(startY, startX, fuel, 0);

		for (int i = 1; i <= M; i++) {
			int sy, sx, dy, dx;
			st = new StringTokenizer(br.readLine(), " ");

			sy = Integer.parseInt(st.nextToken());
			sx = Integer.parseInt(st.nextToken());
			dy = Integer.parseInt(st.nextToken());
			dx = Integer.parseInt(st.nextToken());

			passenger[i] = new Passenger(sy, sx, dy, dx);
			map[sy][sx] = i;
		}

		while (!mainTaxi.isRetired) {
			// printMap();
			findPassenger();

			if (mainTaxi.isRetired) {
				break;
			}

			// printMap();
			moveDest();

			if (checkPassenger()) {
				bw.write(String.valueOf(mainTaxi.remainFuel));
				break;
			}
		}

		if ((!checkPassenger()) && mainTaxi.isRetired) {
			bw.write("-1");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static boolean checkPassenger() {
		for (int i = 1; i <= M; i++) {
			if (!passenger[i].isArrived) {
				return false;
			}
		}

		return true;
	}

	// 방문 여부를 초기화한다.
	public static void initVisited() {
		for (int y = 1; y <= N; y++) {
			for (int x = 1; x <= N; x++) {
				visited[y][x] = false;
			}
		}
	}

	// 택시에서 가장 가까운 손님을 태운다.
	// 태울 수 있는 손님이 없을 경우 택시가 은퇴(retire)한다.
	public static void findPassenger() {
		if (map[mainTaxi.y][mainTaxi.x] != 0) {
			int pn = map[mainTaxi.y][mainTaxi.x];
			mainTaxi.move(passenger[pn].startY, passenger[pn].startX, mainTaxi.remainFuel, 0);
			mainTaxi.ridePassenger(map[passenger[pn].startY][passenger[pn].startX]);
			map[passenger[pn].startY][passenger[pn].startX] = 0;

			return;
		}

		initVisited();

		Queue<Taxi> queue = new LinkedList<Taxi>();
		queue.add(new Taxi(mainTaxi.y, mainTaxi.x, mainTaxi.remainFuel, 0));
		visited[mainTaxi.y][mainTaxi.x] = true;

		// 같은 거리에 있는 손님의 우선 순위를 정하기 위해 사용한다.
		PriorityQueue<Passenger> pq = new PriorityQueue<Passenger>();

		boolean isFind = false;
		int minMoved = 0;

		while (!queue.isEmpty()) {
			Taxi taxi = queue.poll();

			// 이미 최단거리 손님을 찾은 경우, 더 이상 탐색할 필요가 없다.
			if (isFind && taxi.usedFuel == minMoved) {
				break;
			}

			// 택시에 남은 연료가 1 이상인 경우 이동이 가능하다.
			if (taxi.remainFuel >= 1) {
				for (int i = 0; i < 4; i++) {
					int y = taxi.y + dirY[i];
					int x = taxi.x + dirX[i];

					// 해당 좌표가 이동 가능한 좌표라면
					if (visitable(y, x)) {
						visited[y][x] = true;

						// 해당 좌표에 손님이 없다면
						if (map[y][x] == 0) {
							// 해당 좌표로 이동한다.
							queue.add(new Taxi(y, x, taxi.remainFuel - 1, taxi.usedFuel + 1));
						}

						// 해당 좌표에 손님이 있다면
						else {
							// 첫 손님이라면 최단거리를 등록한다.
							if (!isFind) {
								isFind = true;
								minMoved = taxi.usedFuel + 1;
							}

							passenger[map[y][x]].remain = taxi.remainFuel - 1;
							passenger[map[y][x]].moved = taxi.usedFuel + 1;

							// 손님 리스트에 손님을 추가한다.
							pq.add(passenger[map[y][x]]);
						}
					}
				}
			}
		}

		// 태울 수 있는 손님이 존재할 경우
		if (!pq.isEmpty()) {
			Passenger ps = pq.poll();
			mainTaxi.move(ps.startY, ps.startX, ps.remain, ps.moved);
			mainTaxi.ridePassenger(map[ps.startY][ps.startX]);
			map[ps.startY][ps.startX] = 0;
		}

		else {
			mainTaxi.retire();
		}
	}

	public static void moveDest() {
		initVisited();

		Queue<Taxi> queue = new LinkedList<Taxi>();
		queue.add(new Taxi(mainTaxi.y, mainTaxi.x, mainTaxi.remainFuel, 0));
		visited[mainTaxi.y][mainTaxi.x] = true;

		while (!queue.isEmpty()) {
			Taxi taxi = queue.poll();

			if (taxi.remainFuel >= 1) {
				for (int i = 0; i < 4; i++) {
					int y = taxi.y + dirY[i];
					int x = taxi.x + dirX[i];

					if (visitable(y, x)) {
						visited[y][x] = true;

						if (mainTaxi.destY == y && mainTaxi.destX == x) {
							mainTaxi.move(y, x, taxi.remainFuel - 1, taxi.usedFuel + 1);
							mainTaxi.chargeFuel();
							passenger[mainTaxi.pNum].isArrived = true;
							mainTaxi.isRide = false;
							return;
						}

						queue.add(new Taxi(y, x, taxi.remainFuel - 1, taxi.usedFuel + 1));
					}
				}
			}
		}

		mainTaxi.retire();
	}

	// 해당 좌표가 방문 가능한지 여부를 확인한다.
	public static boolean visitable(int y, int x) {
		return (1 <= y) && (y <= N) && (1 <= x) && (x <= N) && (!visited[y][x]) && (map[y][x] != -1);
	}

	public static void printMap() {
		System.out.println();
		System.out.printf("%s", mainTaxi.toString());

		if (!mainTaxi.isRide) {
			for (int y = 1; y <= N; y++) {
				for (int x = 1; x <= N; x++) {
					if (mainTaxi.y == y && mainTaxi.x == x) {
						System.out.printf("* ");
						continue;
					}

					if (map[y][x] == -1) {
						System.out.printf("  ");
					} else {
						System.out.printf("%d ", map[y][x]);
					}
				}
				System.out.println();
			}
		}

		else {
			for (int y = 1; y <= N; y++) {
				for (int x = 1; x <= N; x++) {
					if (mainTaxi.y == y && mainTaxi.x == x) {
						System.out.printf("* ");
						continue;
					}

					if (mainTaxi.destY == y && mainTaxi.destX == x) {
						System.out.printf("D ");
						continue;
					}

					if (map[y][x] == -1) {
						System.out.printf("  ");
						continue;
					}

					System.out.printf("0 ");
				}
				System.out.println();
			}
		}

		System.out.println();
	}
}