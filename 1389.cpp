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

// https://www.acmicpc.net/problem/1389

set <int> s[101];
int N, M;
int bacon[101];
bool visited[101];
queue <pair<int, int>> q;

void BFS(int a, int parent, int depth)
{
	//printf("i am %d, my parent is %d ¡æ %d\n", a, parent, depth);
	bacon[parent] += depth;

	for (set <int>::iterator it = s[a].begin(); it != s[a].end(); it++)
	{
		pair <int, int> n;
		n.first = *it;
		//cout << n.first << "\n";

		if (!visited[n.first])
		{
			visited[n.first] = true;
			n.second = depth + 1;
			q.push(n);
		}
	}

	while (!q.empty())
	{
		int b, dth;
		b = q.front().first;
		dth = q.front().second;
		q.pop();

		BFS(b, parent, dth);
	}
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> M;

	for (int i = 0; i < M; i++)
	{
		int a, b;
		cin >> a >> b;

		s[a].insert(b);
		s[b].insert(a);
	}

	for (int i = 1; i <= N; i++)
	{
		bacon[i] = 0;

		for (int j = 1; j <= N; j++)
		{
			visited[j] = false;
		}

		visited[i] = true;
		BFS(i, i, 0);
	}

	int ans, minVal;
	minVal = bacon[1];
	ans = 1;

	for (int i = 1; i <= N; i++)
	{
		if (minVal > bacon[i])
		{
			ans = i;
			minVal = bacon[i];
		}
	}

	cout << ans << "\n";

	return 0;
}