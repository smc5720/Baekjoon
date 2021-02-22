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

// https://www.acmicpc.net/problem/2573

int N, M;
int field[300][300];
int coField[300][300];
int years;

void meltIce()
{
	queue <pair <int, int>> q;

	int xPos[4] = { -1, 1, 0, 0 };
	int yPos[4] = { 0, 0, -1, 1 };

	for (int k = 0; k < N; k++)
	{
		for (int j = 0; j < M; j++)
		{
			if (field[k][j] == 0)
			{
				for (int i = 0; i < 4; i++)
				{
					int x, y;
					x = k + xPos[i];
					y = j + yPos[i];

					if (x < 0 || x >= N || y < 0 || y >= M)
					{
						continue;
					}

					q.push(make_pair(x, y));
				}
			}
		}
	}

	years += 1;

	while (!q.empty())
	{
		int x, y;
		x = q.front().first;
		y = q.front().second;

		if (field[x][y] > 0)
		{
			field[x][y] -= 1;
		}

		q.pop();
	}
}

void coDFS(int a, int b)
{
	int xPos[4] = { -1, 1, 0, 0 };
	int yPos[4] = { 0, 0, -1, 1 };

	coField[a][b] = 0;

	for (int i = 0; i < 4; i++)
	{
		int x, y;
		x = a + xPos[i];
		y = b + yPos[i];

		if (x < 0 || x >= N || y < 0 || y >= M)
		{
			continue;
		}

		if (coField[x][y] > 0)
		{
			coDFS(x, y);
		}
	}
}

int checkIce()
{
	int cnt;
	cnt = 0;

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			coField[i][j] = field[i][j];
		}
	}

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			if (coField[i][j] > 0)
			{
				coDFS(i, j);
				cnt += 1;
			}
		}
	}

	return cnt;
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> M;
	years = 0;

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			cin >> field[i][j];
		}
	}

	int c;
	c = checkIce();

	while (c > 0 && c < 2)
	{
		meltIce();
		c = checkIce();
	}

	if (c > 0)
	{
		cout << years << "\n";
	}

	else
	{
		cout << 0 << "\n";
	}

	return 0;
}