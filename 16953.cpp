#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>
#include <stack>
#include <set>

using namespace std;

// https://www.acmicpc.net/problem/16953

long long A, B;
queue <long long> q;
map <long long, bool> mp;

int main(void)
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> A >> B;

	int cnt;
	cnt = 1;

	q.push(A);

	while (!q.empty())
	{
		int size;
		size = q.size();

		for (int i = 0; i < size; i++)
		{
			long long n, m;
			n = q.front() * 2;
			m = q.front() * 10 + 1;

			if (n == B || m == B)
			{
				cout << cnt + 1 << "\n";
				exit(0);
			}

			if (n <= 1000000000 && n >= 1 && mp.count(n) == 0)
			{
				mp.insert(make_pair(n, true));
				q.push(n);
			}

			if (m <= 1000000000 && m >= 1 && mp.count(m) == 0)
			{
				mp.insert(make_pair(m, true));
				q.push(m);
			}

			q.pop();
		}

		cnt += 1;
	}

	cout << "-1\n";

	return 0;
}