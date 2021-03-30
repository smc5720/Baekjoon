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

// https://www.acmicpc.net/problem/2606

int N, M;
set <int> s[101];
bool visited[101];

void DFS(int n)
{
	if (!visited[n])
	{
		visited[n] = true;

		for (auto iter = s[n].begin(); iter != s[n].end(); iter++)
		{
			DFS(*iter);
		}
	}
}

int main(void)
{
	cin >> N >> M;

	for (int i = 1; i <= N; i++)
	{
		visited[i] = false;
	}

	for (int i = 0; i < M; i++)
	{
		int a, b;
		cin >> a >> b;

		s[a].insert(b);
		s[b].insert(a);
	}

	DFS(1);

	int cnt;
	cnt = -1;

	for (int i = 1; i <= N; i++)
	{
		if (visited[i])
		{
			cnt += 1;
		}
	}

	cout << cnt << "\n";

	return 0;
}