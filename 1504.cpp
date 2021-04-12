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

// https://www.acmicpc.net/problem/1504

#define MAXVAL 987654321

int N, E;
vector <pair <int, int>> v[1001];

int dijkstra(int start, int end)
{
	int dist[1001];

	for (int i = 1; i <= N; i++)
	{
		dist[i] = MAXVAL;
	}

	dist[start] = 0;

	priority_queue <pair <int, int>, vector <pair <int, int>>, greater <pair <int, int>>> pq;
	pq.push({ 0, start });

	while (!pq.empty())
	{
		int idx, cost;

		idx = pq.top().second;
		cost = pq.top().first;

		pq.pop();

		if (dist[idx] != cost) continue;

		int size;
		size = v[idx].size();

		for (int i = 0; i < size; i++)
		{
			int next = v[idx][i].first;
			int w = v[idx][i].second;

			if (dist[next] > dist[idx] + w)
			{
				dist[next] = dist[idx] + w;
				pq.push({ dist[next], next });
			}
		}
	}

	return dist[end];
}

int main(void)
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> E;

	for (int i = 0; i < E; i++)
	{
		int s, e, cost;
		cin >> s >> e >> cost;
		v[s].push_back({ e, cost });
		v[e].push_back({ s, cost });
	}

	int v1, v2;
	cin >> v1 >> v2;

	int minVal;
	minVal = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N);
	minVal = min(minVal, dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N));

	if (minVal >= MAXVAL || minVal < 0)
	{
		cout << -1 << "\n";
	}

	else
	{
		cout << minVal << "\n";
	}

	return 0;
}