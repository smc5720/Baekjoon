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

// https://www.acmicpc.net/problem/16928

int N, M;
bool visited[101];
queue <int> q;
int moving[101];
int cnt;

void BFS()
{
	cnt += 1;

	int size;
	size = q.size();

	for (int i = 0; i < size; i++)
	{
		int n;
		n = q.front();
		q.pop();

		for (int j = 1; j <= 6; j++)
		{
			if (n + j > 100)
			{
				break;
			}

			if (moving[n + j] == 100)
			{
				cout << cnt << "\n";
				exit(0);
			}

			if (!visited[n + j])
			{
				visited[n + j] = true;
				q.push(moving[n + j]);
			}
		}
	}

	BFS();
}

int main(void)
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> M;

	for (int i = 1; i <= 100; i++)
	{
		visited[i] = false;
		moving[i] = i;
	}

	for (int i = 0; i < N + M; i++)
	{
		int x, y;
		cin >> x >> y;
		moving[x] = y;
	}

	cnt = 0;
	visited[1] = true;
	q.push(1);
	BFS();

	return 0;
}