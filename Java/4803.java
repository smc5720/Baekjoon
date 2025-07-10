import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int n, m;
    private static Node[] nodes;
    private static StringTokenizer st;
    private static StringBuilder sb;
    private static BufferedReader br;
    private static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int caseNum = 0;

        while (n != 0 || m != 0) {
            caseNum += 1;
            init();
            int treeCnt = countTrees();

            if (treeCnt == 0) {
                sb.append("Case ").append(caseNum).append(": No trees.\n");
            } else if (treeCnt == 1) {
                sb.append("Case ").append(caseNum).append(": There is one tree.\n");
            } else {
                sb.append("Case ").append(caseNum).append(": A forest of ").append(treeCnt).append(" trees.\n");
            }

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void init() throws IOException {
        nodes = new Node[n + 1];

        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            if (n1 != n2) {
                nodes[n1].add(nodes[n2]);
                nodes[n2].add(nodes[n1]);
            }
        }
    }

    public static int countTrees() {
        int cnt = 0;

        for (int i = 1; i <= n; i++) {
            if (!nodes[i].visited) {
                boolean isTree = dfs(i, 0);

                if (isTree) {
                    cnt += 1;
                }
            }
        }

        return cnt;
    }

    public static boolean dfs(int current, int parent) {
        nodes[current].visited = true;
        boolean result = true;

        for (Node child : nodes[current].children) {
            if (child.value == parent) {
                continue;
            }

            if (child.visited) {
                result = false;
            } else {
                if (!dfs(child.value, current)) {
                    result = false;
                }
            }
        }

        return result;
    }

    public static class Node {
        public int value;
        public List<Node> children;
        public boolean visited;

        public Node(int value) {
            this.value = value;
            this.children = new ArrayList<>();
            this.visited = false;
        }

        public void add(Node node) {
            this.children.add(node);
        }
    }
}