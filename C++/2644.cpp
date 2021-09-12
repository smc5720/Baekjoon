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

// https://www.acmicpc.net/problem/2644

int n, m;
int a, b;
int ans;
map <int, set <int>> p;
bool visited[101];

void DFS(int k, int depth)
{
	if (p[k].count(b) > 0)
	{
		cout << depth << "\n";
		exit(0);
	}

	set<int>::iterator iter;
	for (iter = p[k].begin(); iter != p[k].end(); iter++)
	{
		int x;
		x = *iter;

		if (!visited[x])
		{
			visited[x] = true;
			DFS(x, depth + 1);
		}
	}
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> n >> a >> b;
	cin >> m;

	for (int i = 1; i <= 100; i++)
	{
		visited[i] = false;
	}

	ans = -1;

	for (int i = 0; i < m; i++)
	{
		int x, y;
		cin >> x >> y;

		p[x].insert(y);
		p[y].insert(x);
	}

	if (a == b)
	{
		cout << "0\n";
	}

	visited[a] = true;
	DFS(a, 1);

	cout << "-1\n";

	return 0;
}