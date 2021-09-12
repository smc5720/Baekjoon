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

// https://www.acmicpc.net/problem/1715

struct cmp {
	bool operator()(long long a, long long b)
	{
		return a > b;
	}
};

int N;
priority_queue <long long, vector<long long>, cmp> pq;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 0; i < N; i++)
	{
		long long c;
		cin >> c;
		pq.push(c);
	}

	long long ans;
	ans = 0;

	for (int i = 0; i < N - 1; i++)
	{
		long long a, b;

		a = pq.top();
		pq.pop();
		b = pq.top();
		pq.pop();

		ans += a + b;
		pq.push(a + b);
	}

	cout << ans << "\n";

	return 0;
}