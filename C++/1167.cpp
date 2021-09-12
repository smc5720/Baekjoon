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

// https://www.acmicpc.net/problem/1167

int V, maxVal, maxId;
vector <pair <int, int>> vp[100001];
bool visited[100001];

void DFS(int v, int length)
{
	if (maxVal < length)
	{
		maxVal = length;
		maxId = v;
	}

	int size;
	size = vp[v].size();

	for (int i = 0; i < size; i++)
	{
		int a, b;
		a = vp[v][i].first;
		b = vp[v][i].second;

		if (!visited[a])
		{
			visited[a] = true;
			DFS(a, length + b);
			visited[a] = false;
		}
	}
}

int main(void)
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> V;
	maxVal = 0;

	for (int i = 0; i < V; i++)
	{
		int id, a, b;
		cin >> id >> a;
		visited[id] = false;

		while (a != -1)
		{
			cin >> b;
			vp[id].push_back(make_pair(a, b));
			cin >> a;
		}
	}

	visited[1] = true;
	DFS(1, 0);
	visited[1] = false;

	visited[maxId] = true;
	DFS(maxId, 0);

	cout << maxVal << "\n";

	return 0;
}