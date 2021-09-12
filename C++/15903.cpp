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

// https://www.acmicpc.net/problem/15903

struct cmp {
	bool operator()(long long a, long long b)
	{
		return a > b;
	}
};

int n, m;
priority_queue <long long, vector<long long>, cmp> pq;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> n >> m;

	for (int i = 0; i < n; i++)
	{
		int c;
		cin >> c;
		pq.push(c);
	}

	for (int i = 0; i < m; i++)
	{
		long long a, b;
		a = pq.top();
		pq.pop();
		b = pq.top();
		pq.pop();

		pq.push(a + b);
		pq.push(a + b);
	}

	long long ans;
	ans = 0;

	for (int i = 0; i < n; i++)
	{
		ans += pq.top();
		pq.pop();
	}

	cout << ans << "\n";

	return 0;
}