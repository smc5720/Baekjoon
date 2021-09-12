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

// https://www.acmicpc.net/problem/1260

int N, M, V;
set <int> node[1001];
bool visited[1001];
queue <int> q;

void refresh()
{
	for (int i = 1; i <= N; i++)
	{
		visited[i] = false;
	}
}

void DFS(int v)
{
	cout << v << " ";
	visited[v] = true;

	set <int>::iterator iter;

	for (iter = node[v].begin(); iter != node[v].end(); iter++)
	{
		int n;
		n = *iter;

		if (visited[n] == false)
		{
			DFS(n);
		}
	}
}

void BFS()
{
	if (q.empty())
	{
		return;
	}

	while (!q.empty())
	{
		int v;
		v = q.front();

		if (visited[v] == false)
		{
			cout << v << " ";
			visited[v] = true;
		}

		set <int>::iterator iter;

		for (iter = node[v].begin(); iter != node[v].end(); iter++)
		{
			int n;
			n = *iter;

			if (visited[n] == false)
			{
				q.push(n);
			}
		}

		q.pop();
	}

	BFS();
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> M >> V;

	refresh();

	for (int i = 0; i < M; i++)
	{
		int a, b;
		cin >> a >> b;

		node[a].insert(b);
		node[b].insert(a);
	}

	DFS(V);
	cout << "\n";

	refresh();

	q.push(V);
	BFS();
	cout << "\n";

	return 0;
}