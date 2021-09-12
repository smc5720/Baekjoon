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

// https://www.acmicpc.net/problem/13975

int T;

struct cmp {
	bool operator()(long long a, long long b) {
		return a > b;
	}
};

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> T;

	for (int t = 0; t < T; t++)
	{
		long long ans;
		priority_queue <long long, vector<long long>, cmp> pq;
		int K;
		cin >> K;

		ans = 0;

		for (int i = 0; i < K; i++)
		{
			int n;
			cin >> n;
			pq.push(n);
		}

		while (pq.size() > 1)
		{
			long long a, b;
			a = pq.top();
			pq.pop();
			b = pq.top();
			pq.pop();
			pq.push(a + b);
			ans += a + b;
		}

		cout << ans << "\n";
	}

	return 0;
}