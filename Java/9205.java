import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static final int DISTANCE_LIMIT = 1000;
    private static int t, n;
    private static Place start, dest;
    private static Place[] store;
    private static boolean reachable = false;

    public static int getDistance(Place p1, Place p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    public static boolean visitable(Place p1, Place p2) {
        return !p2.visited && getDistance(p1, p2) <= DISTANCE_LIMIT;
    }

    public static void dfs(Place currentPlace) {
        currentPlace.visited = true;

        if (visitable(currentPlace, dest)) {
            reachable = true;
            return;
        }

        for (Place p : store) {
            if (!reachable && visitable(currentPlace, p)) {
                dfs(p);
                p.visited = false;
            }
        }
    }

    public static void bfs() {
        Queue<Place> places = new LinkedList<Place>();
        places.offer(start);
        start.visited = true;

        while (!places.isEmpty()) {
            Place currentPlace = places.poll();

            if (visitable(currentPlace, dest)) {
                reachable = true;
                break;
            }

            for (Place p : store) {
                if (visitable(currentPlace, p)) {
                    places.offer(p);
                    p.visited = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        for (int testCase = 0; testCase < t; testCase++) {
            reachable = false;
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            store = new Place[n];

            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            start = new Place(x, y);

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                store[i] = new Place(x, y);
            }

            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            dest = new Place(x, y);

            // dfs(start);
            bfs();
            
            if (reachable) {
                sb.append("happy\n");
            } else {
                sb.append("sad\n");
            }
        }

        bw.write(sb + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static class Place {
        int x, y;
        boolean visited;

        public Place(int x, int y) {
            this.x = x;
            this.y = y;
            this.visited = false;
        }
    }
}