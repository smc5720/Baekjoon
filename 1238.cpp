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

// https://www.acmicpc.net/problem/1238

#define MAXVAL 987654321

int N, M, X;
vector <pair <int, int>> v1[1001];
vector <pair <int, int>> v2[1001];

int dist1[1001];
int dist2[1001];

int dijkstra(int start, int end)
{
	dist1[start] = 0;
	dist2[start] = 0;

	priority_queue <pair <int, int>, vector <pair <int, int>>, greater <pair <int, int>>> pq;
	pq.push({ 0, start });

	while (!pq.empty())
	{
		int idx, cost;

		idx = pq.top().second;
		cost = pq.top().first;

		pq.pop();

		if (dist1[idx] != cost) continue;

		int size;
		size = v1[idx].size();

		for (int i = 0; i < size; i++)
		{
			int next = v1[idx][i].first;
			int w = v1[idx][i].second;

			if (dist1[next] > dist1[idx] + w)
			{
				dist1[next] = dist1[idx] + w;
				pq.push({ dist1[next], next });
			}
		}
	}

	pq.push({ 0, start });

	while (!pq.empty())
	{
		int idx, cost;

		idx = pq.top().second;
		cost = pq.top().first;

		pq.pop();

		if (dist2[idx] != cost) continue;

		int size;
		size = v2[idx].size();

		for (int i = 0; i < size; i++)
		{
			int next = v2[idx][i].first;
			int w = v2[idx][i].second;

			if (dist2[next] > dist2[idx] + w)
			{
				dist2[next] = dist2[idx] + w;
				pq.push({ dist2[next], next });
			}
		}
	}

	return dist1[end] + dist2[end];
}

int main(void)
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> M >> X;

	for (int i = 0; i < M; i++)
	{
		int s, e, cost;
		cin >> s >> e >> cost;
		v1[s].push_back({ e, cost });
		v2[e].push_back({ s, cost });
	}

	for (int i = 1; i <= N; i++)
	{
		dist1[i] = MAXVAL;
		dist2[i] = MAXVAL;
	}

	int maxVal = 0;

	for (int i = 1; i <= N; i++)
	{
		if (i != X)
		{
			maxVal = max(maxVal, dijkstra(X, i));
		}
	}

	cout << maxVal << "\n";

	return 0;
}