import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Student> pq = new PriorityQueue<>(new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				if (o1.kor == o2.kor) {
					if (o1.eng == o2.eng) {
						if (o1.math == o2.math) {
							return o1.name.compareTo(o2.name);
						}
						return Integer.compare(o2.math, o1.math);
					}
					return Integer.compare(o1.eng, o2.eng);
				}
				return Integer.compare(o2.kor, o1.kor);
			}
		});

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			String name = st.nextToken();
			int kor = Integer.parseInt(st.nextToken());
			int eng = Integer.parseInt(st.nextToken());
			int math = Integer.parseInt(st.nextToken());

			Student tmp = new Student(name, kor, eng, math);
			pq.add(tmp);
		}

		while (!pq.isEmpty()) {
			bw.write(pq.poll().name);
			bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();
	}

	static class Student {
		public String name;
		public int kor;
		public int eng;
		public int math;

		public Student(String name, Integer kor, Integer eng, Integer math) {
			this.name = name;
			this.kor = kor;
			this.eng = eng;
			this.math = math;
		}
	}
}