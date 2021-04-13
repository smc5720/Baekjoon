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

// https://www.acmicpc.net/problem/1753

#define MAXVAL 987654321

int V, E, K;
vector <pair <int, int>> v[20001];

int dist[20001];

int dijkstra(int start, int end)
{
	dist[start] = 0;

	priority_queue <pair <int, int>, vector <pair <int, int>>, greater <pair <int, int>>> pq;
	pq.push({ 0, start });

	while (!pq.empty())
	{
		int idx, cost;

		idx = pq.top().second;
		cost = pq.top().first;

		pq.pop();

		if (dist[idx] < cost) continue;

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

	cin >> V >> E >> K;

	for (int i = 1; i <= V; i++)
	{
		dist[i] = MAXVAL;
	}

	for (int i = 0; i < E; i++)
	{
		int s, e, cost;
		cin >> s >> e >> cost;
		v[s].push_back({ e, cost });
	}

	dijkstra(K, 1);

	for (int i = 1; i <= V; i++)
	{
		if (dist[i] == MAXVAL)
		{
			cout << "INF\n";
		}

		else
		{
			cout << dist[i] << "\n";
		}
	}

	return 0;
}