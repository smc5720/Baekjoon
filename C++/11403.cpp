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

// https://www.acmicpc.net/problem/11403

int N;
int G[101][101];
bool visited[101];

void initState()
{
	for (int i = 1; i <= N; i++)
	{
		visited[i] = false;
	}
}

void DFS(int p, int c)
{
	for (int i = 1; i <= N; i++)
	{
		if (G[c][i] == 1 && !visited[i])
		{
			visited[i] = true;
			G[p][i] = 1;
			DFS(p, i);
		}
	}
}

int main(void)
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N;

	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j <= N; j++)
		{
			cin >> G[i][j];
		}
	}

	for (int i = 1; i <= N; i++)
	{
		initState();
		DFS(i, i);
	}

	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j <= N; j++)
		{
			cout << G[i][j] << " ";
		}

		cout << "\n";
	}

	return 0;
}