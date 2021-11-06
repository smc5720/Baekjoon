import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N;

	public static class Time {
		int start, end;

		public Time(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		Set<String> students = new HashSet<String>();
		Set<String> placeSet = new HashSet<String>();
		HashMap<String, PriorityQueue<Time>> places = new HashMap<String, PriorityQueue<Time>>();
		HashMap<String, Time> map = new HashMap<String, Time>();

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			String place = st.nextToken();
			Time time = new Time(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			if (!students.contains(name)) {
				students.add(name);
				if (places.containsKey(place)) {
					places.get(place).add(time);
				} else {
					places.put(place, new PriorityQueue<Time>(new Comparator<Time>() {
						@Override
						public int compare(Time o1, Time o2) {
							if (o1.start == o2.start) {
								return Integer.compare(o1.end, o2.end);
							}
							return Integer.compare(o1.start, o2.start);
						}
					}));
					places.get(place).add(time);
				}
				placeSet.add(place);
			}
		}

		List<String> placeList = new ArrayList<String>(placeSet);
		Collections.sort(placeList);

		int maxVal = 0;
		String maxPlace = "";

		for (String p : placeList) {
			int x = getFrequencyTerm(p, places.get(p), map);
			if (maxVal < x) {
				maxVal = x;
				maxPlace = p;
			}
		}

		System.out.printf("%s %d %d\n", maxPlace, map.get(maxPlace).start, map.get(maxPlace).end);

		bw.flush();
		bw.close();
		br.close();
	}

	// 가장 많이 중복되는 구간의 개수를 리턴한다.
	public static int getFrequencyTerm(String place, PriorityQueue<Time> pq, HashMap<String, Time> map) {
		int[] times = new int[50001];
		int result = 0;
		int start = 0;
		int end = 0;
		boolean state = false;

		for (int i = 0; i <= 50000; i++) {
			times[i] = 0;
		}

		while (!pq.isEmpty()) {
			Time time = pq.poll();
			for (int i = time.start; i < time.end; i++) {
				times[i] += 1;
			}
		}

		for (int i = 0; i <= 50000; i++) {
			result = Math.max(times[i], result);
		}

		for (int i = 0; i <= 50000; i++) {
			if (times[i] == result) {
				start = i;
				for (int j = i; j <= 50000; j++) {
					if (times[j] != result) {
						end = j;
						state = true;
						break;
					}
				}
			}

			if (state) {
				break;
			}
		}

		map.put(place, new Time(start, end));

		return result;
	}
}