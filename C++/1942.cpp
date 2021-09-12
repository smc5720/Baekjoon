#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>
#include <stack>

using namespace std;

// https://www.acmicpc.net/problem/1942

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	for (int l = 0; l < 3; l++)
	{
		int h1, m1, s1;
		int h2, m2, s2;

		string time1, time2;
		cin >> time1 >> time2;

		h1 = (time1[0] - 48) * 10 + (time1[1] - 48);
		m1 = (time1[3] - 48) * 10 + (time1[4] - 48);
		s1 = (time1[6] - 48) * 10 + (time1[7] - 48);

		h2 = (time2[0] - 48) * 10 + (time2[1] - 48);
		m2 = (time2[3] - 48) * 10 + (time2[4] - 48);
		s2 = (time2[6] - 48) * 10 + (time2[7] - 48);

		int ans;
		ans = 0;

		while (h1 != h2 || m1 != m2 || s1 != s2)
		{
			int num;
			num = h1 * 10000 + m1 * 100 + s1;

			if (num % 3 == 0)
			{
				ans += 1;
			}

			s1 += 1;

			if (s1 == 60)
			{
				s1 = 0;
				m1 += 1;
			}

			if (m1 == 60)
			{
				m1 = 0;
				h1 += 1;
			}

			if (h1 == 24)
			{
				h1 = 0;
			}
		}

		int num;
		num = h2 * 10000 + m2 * 100 + s2;

		if (num % 3 == 0)
		{
			ans += 1;
		}

		cout << ans << "\n";
	}

	return 0;
}