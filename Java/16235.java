import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringTokenizer st;
    public static final int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    public static final int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
    public static int N, M, K;
    public static Tile[][] tile;
    public static ArrayList<Tree> tmpList;

    public static class Tree implements Comparable<Tree> {
        int r, c, age;
        boolean alive;

        public Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
            alive = true;
        }

        public void feed() {
            if (tile[r][c].feed >= age) {
                tile[r][c].feed -= age;
                age += 1;
            } else {
                alive = false;
            }
        }

        public void kill() {
            if (!alive) {
                int tmp = age / 2;
                tile[r][c].feed += tmp;
            }
        }

        public void breed() {
            if (age % 5 == 0) {
                for (int i = 0; i < 8; i++) {
                    int tr = r + dr[i];
                    int tc = c + dc[i];

                    if (visitable(tr, tc)) {
                        tile[tr][tc].addTree(new Tree(tr, tc, 1));
                    }
                }
            }
        }

        @Override
        public int compareTo(Tree t) {
            return Integer.compare(this.age, t.age);
        }
    }

    public static class Tile {
        int r, c, feed, A;
        ArrayList<Tree> trees;

        public Tile(int r, int c, int A) {
            this.r = r;
            this.c = c;
            this.A = A;
            feed = 5;
            trees = new ArrayList<>();
        }

        public void addTree(Tree tree) {
            trees.add(tree);
        }

        public void feedTrees() {
            tmpList = new ArrayList<>();
            Collections.sort(trees);

            for (int i = 0; i < trees.size(); i++) {
                Tree tr = trees.get(i);
                tr.feed();
                tmpList.add(tr);
            }

            trees = tmpList;
        }

        public void killTrees() {
            tmpList = new ArrayList<>();
            Collections.sort(trees);

            for (int i = 0; i < trees.size(); i++) {
                Tree tr = trees.get(i);
                tr.kill();

                if (tr.alive) {
                    tmpList.add(tr);
                }
            }

            trees = tmpList;
        }

        public void breedTrees() {
            tmpList = new ArrayList<>();
            Collections.sort(trees);

            for (int i = 0; i < trees.size(); i++) {
                Tree tr = trees.get(i);
                tr.breed();
                tmpList.add(tr);
            }

            trees = tmpList;
        }

        public void addFeed() {
            this.feed += this.A;
        }

        public int getTreeCount() {
            int sum = 0;

            for (int i = 0; i < trees.size(); i++) {
                sum += trees.get(i).alive ? 1 : 0;
            }

            return sum;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        tile = new Tile[N + 1][N + 1];

        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine(), " ");

            for (int c = 1; c <= N; c++) {
                int A = Integer.parseInt(st.nextToken());
                tile[r][c] = new Tile(r, c, A);
            }
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine(), " ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            Tree tree = new Tree(x, y, z);
            tile[x][y].addTree(tree);
        }

        for (int i = 0; i < K; i++) {
            spring();
            summer();
            fall();
            winter();
        }

        bw.write(String.valueOf(totalAliveTrees()));
        bw.flush();
        bw.close();
        br.close();
    }

    /**
     * 봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다.
     * 각각의 나무는 나무가 있는 1×1 크기의 칸에 있는 양분만 먹을 수 있다.
     * 하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다.
     * 만약, 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.
     */
    public static void spring() {
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                tile[r][c].feedTrees();
            }
        }
    }

    /**
     * 여름에는 봄에 죽은 나무가 양분으로 변하게 된다.
     * 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다.
     * 소수점 아래는 버린다.
     */
    public static void summer() {
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                tile[r][c].killTrees();
            }
        }
    }

    /**
     * 가을에는 나무가 번식한다.
     * 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
     * 어떤 칸 (r, c)와 인접한 칸은 (r-1, c-1), (r-1, c), (r-1, c+1), (r, c-1), (r, c+1), (r+1, c-1), (r+1, c), (r+1, c+1) 이다.
     * 상도의 땅을 벗어나는 칸에는 나무가 생기지 않는다.
     */
    public static void fall() {
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                tile[r][c].breedTrees();
            }
        }
    }

    /**
     * 겨울에는 S2D2가 땅을 돌아다니면서 땅에 양분을 추가한다.
     * 각 칸에 추가되는 양분의 양은 A[r][c]이고, 입력으로 주어진다.
     */
    public static void winter() {
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                tile[r][c].addFeed();
            }
        }
    }

    public static boolean visitable(int tr, int tc) {
        return (1 <= tr) && (tr <= N) && (1 <= tc) && (tc <= N);
    }

    public static int totalAliveTrees() {
        int sum = 0;

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                sum += tile[r][c].getTreeCount();
            }
        }

        return sum;
    }
}