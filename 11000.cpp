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

// https://www.acmicpc.net/problem/11000

int N;
pair <int, int> p[200000];

bool ascendingOrderByStartTime(const pair<int, int>& a, const pair<int, int>& b) {
	if (a.first == b.first) {
		return a.second < b.second;
	}
	return a.first < b.first;
}

struct ascendingOrderByEndTime {
	bool operator()(const pair<int, int>& a, const pair<int, int>& b) {
		if (a.second == b.second) {
			return a.first > b.first;
		}
		return a.second > b.second;
	}
};

priority_queue <pair<int, int>, vector<pair<int, int>>, ascendingOrderByEndTime> pq;

void printPair()
{
	cout << "\nprint Pair\n";

	for (int i = 0; i < N; i++)
	{
		cout << p[i].first << " " << p[i].second << "\n";
	}

	cout << "\n";
}

void printPQ()
{
	for (int i = 0; i < N; i++)
	{
		pq.push(p[i]);
	}

	cout << "\nprint PQ\n";

	for (int i = 0; i < N; i++)
	{
		cout << pq.top().first << " " << pq.top().second << "\n";
		pq.pop();
	}

	cout << "\n";
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 0; i < N; i++)
	{
		cin >> p[i].first >> p[i].second;
	}

	sort(p, p + N, ascendingOrderByStartTime);

	int cnt;
	cnt = 0;

	for (int i = 0; i < N; i++)
	{
		if (pq.empty())
		{
			cnt += 1;
			pq.push(p[i]);
		}

		else
		{
			if (pq.top().second <= p[i].first)
			{
				pq.pop();
				pq.push(p[i]);
			}

			else
			{
				cnt += 1;
				pq.push(p[i]);
			}
		}
	}

	cout << cnt << "\n";

	return 0;
}