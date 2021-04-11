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

// https://www.acmicpc.net/problem/1916

#define MAXVAL 100000001

int N, M;
int dist[1001];
vector <pair <int, int>> v[1001];

int main(void)
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> M;

	for (int i = 0; i < M; i++)
	{
		int s, e, cost;
		cin >> s >> e >> cost;
		v[s].push_back({ e, cost });
	}

	int start, end;
	cin >> start >> end;

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

	cout << dist[end] << "\n";

	return 0;
}