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

// https://www.acmicpc.net/problem/11724

int N, M;
bool visited[1001];
set <int> s[1001];
int cnt;

void DFS(int n, int depth)
{
	if (visited[n])
	{
		return;
	}

	visited[n] = true;

	if (depth == 0)
	{
		cnt += 1;
	}

	for (auto iter = s[n].begin(); iter != s[n].end(); iter++)
	{
		int i;
		i = *iter;
		DFS(i, depth+1);
	}
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> M;
	cnt = 0;

	for (int i = 1; i <= N; i++)
	{
		visited[i] = false;
	}

	for (int i = 1; i <= M; i++)
	{
		int a, b;
		cin >> a >> b;

		s[a].insert(b);
		s[b].insert(a);
	}

	for (int i = 1; i <= N; i++)
	{
		DFS(i, 0);
	}

	cout << cnt << "\n";

	return 0;
}