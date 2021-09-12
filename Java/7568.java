import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;

		Person[] p = new Person[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());

			p[i] = new Person(w, h);
		}

		for (int i = 0; i < N; i++) {
			int rank = 1;
			
			for (int j = 0; j < N; j++) {
				if (i == j) {
					continue;
				}
				
				if (p[i].weight < p[j].weight && p[i].height < p[j].height) {
					rank += 1;
				}
			}
			p[i].setRank(rank);
			
			bw.write(p[i].rank + " ");
		}

		bw.flush();
		bw.close();
		br.close();
	}
}

class Person {
	public int weight, height, rank;

	public Person(int weight, int height) {
		this.weight = weight;
		this.height = height;
	}

	public void setRank(int n) {
		this.rank = n;
	}
}