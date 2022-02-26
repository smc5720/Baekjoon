import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int R, C, M;
	public static int[][] map;
	public static Shark[] shark;

	public static class Shark {
		int r, c, speed, dir, size;
		boolean alive;

		public Shark(int r, int c, int speed, int dir, int size) {
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
			this.alive = true;
		}

		public void setPos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		shark = new Shark[M + 1];
		map = new int[R + 1][C + 1];

		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				map[r][c] = 0;
			}
		}

		for (int i = 1; i < M + 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			shark[i] = new Shark(r, c, s, d, z);
			map[r][c] = i;
		}

		int answer = 0;

		for (int x = 1; x <= C; x++) {
			answer += fishing(x);
			moveShark();
		}

		bw.write(String.valueOf(answer));

		bw.flush();
		bw.close();
		br.close();
	}

	public static int fishing(int c) {
		for (int y = 1; y <= R; y++) {
			if (map[y][c] != 0 && shark[map[y][c]].alive) {
				int size = shark[map[y][c]].size;
				shark[map[y][c]].alive = false;
				map[y][c] = 0;

				return size;
			}
		}

		return 0;
	}

	public static void moveShark() {
		for (int i = 1; i <= M; i++) {
			if (!shark[i].alive) {
				continue;
			}

			if (map[shark[i].r][shark[i].c] == i) {
				map[shark[i].r][shark[i].c] = 0;
			}

			int r = 0;
			int c = 0;
			int dir = 0;

			if (shark[i].dir < 3) {
				int sp = shark[i].speed % ((R - 1) * 2);
				Point p = getNewPos(shark[i].dir, sp, shark[i].r);
				r = p.x;
				c = shark[i].c;
				dir = p.y;
			} else {
				int sp = shark[i].speed % ((C - 1) * 2);
				Point p = getNewPos(shark[i].dir, sp, shark[i].c);
				r = shark[i].r;
				c = p.x;
				dir = p.y;
			}

			shark[i].setPos(r, c);
			shark[i].dir = dir;

			if (map[r][c] == 0) {
				map[r][c] = i;
			} else {
				if (i > map[r][c]) {
					if (shark[i].size > shark[map[r][c]].size) {
						shark[map[r][c]].alive = false;
						map[r][c] = i;
					} else {
						shark[i].alive = false;
					}
				} else {
					map[r][c] = i;
				}
			}
		}
	}

	public static Point getNewPos(int dir, int speed, int n) {
		if (dir < 3) {
			int r = n;

			for (int sp = 0; sp < speed; sp++) {
				if (dir == 1) {
					if (r == 1) {
						dir = 2;
						r += 1;
						continue;
					}
					r -= 1;
				} else {
					if (r == R) {
						dir = 1;
						r -= 1;
						continue;
					}
					r += 1;
				}
			}

			return new Point(r, dir);
		} else {
			int c = n;

			for (int sp = 0; sp < speed; sp++) {
				if (dir == 4) {
					if (c == 1) {
						dir = 3;
						c += 1;
						continue;
					}
					c -= 1;
				} else {
					if (c == C) {
						dir = 4;
						c -= 1;
						continue;
					}
					c += 1;
				}
			}

			return new Point(c, dir);
		}
	}
}