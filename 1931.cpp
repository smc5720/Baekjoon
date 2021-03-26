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

// https://www.acmicpc.net/problem/1931

struct cmp
{
	bool operator()(pair <int, int> a, pair <int, int> b)
	{
		if (a.second == b.second)
		{
			return a.first > b.first;
		}

		return a.second > b.second;
	}
};

int N;
priority_queue <pair<int, int>, vector<pair<int, int>>, cmp> pq;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 0; i < N; i++)
	{
		pair<int, int> p;
		cin >> p.first >> p.second;
		pq.push(p);
	}

	// cout << pq.top().first << " " << pq.top().second << "\n";

	int endTime, cnt;
	cnt = 1;
	endTime = pq.top().second;
	pq.pop();

	while (!pq.empty())
	{
		// cout << pq.top().first << " " << pq.top().second << "\n";

		if (endTime <= pq.top().first)
		{
			endTime = pq.top().second;
			cnt += 1;
		}

		pq.pop();
	}

	cout << cnt << "\n";

	return 0;
}