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

// https://www.acmicpc.net/problem/2178

int N, M;
int field[101][101];
queue <pair <int, int>> q;
int visited[101][101];

void BFS(int cnt)
{
	cnt += 1;

	int size;
	size = q.size();

	int xPos[4] = { -1, 1, 0, 0 };
	int yPos[4] = { 0, 0, -1, 1 };

	for (int i = 0; i < size; i++)
	{
		pair <int, int> p;
		p = q.front();
		q.pop();

		for (int j = 0; j < 4; j++)
		{
			int x, y;
			x = p.first + xPos[j];
			y = p.second + yPos[j];

			if (x <= 0 || x > N || y <= 0 || y > M)
			{
				continue;
			}

			if (!visited[x][y] && field[x][y] == 1)
			{
				q.push(make_pair(x, y));
				visited[x][y] = true;

				if (x == N && y == M)
				{
					cout << cnt << "\n";
					exit(0);
				}
			}
		}
	}

	if (!q.empty())
	{
		BFS(cnt);
	}
}

int main(void)
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> M;

	for (int i = 1; i <= N; i++)
	{
		string s;
		cin >> s;

		for (int j = 1; j <= M; j++)
		{
			field[i][j] = s[j - 1] - '0';
			visited[i][j] = false;
		}
	}

	q.push(make_pair(1, 1));
	visited[1][1] = true;
	BFS(1);

	return 0;
}