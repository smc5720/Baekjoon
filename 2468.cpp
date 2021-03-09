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

// https://www.acmicpc.net/problem/2468

int N;
int field[100][100];
bool flooded[100][100];
int minH, maxH;
int ans;

void refresh(int height)
{
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			if (height >= field[i][j])
			{
				flooded[i][j] = false;
			}

			else
			{
				flooded[i][j] = true;
			}
		}
	}
}

void DFS(int x, int y)
{
	flooded[x][y] = false;

	int xPos[4] = { -1, 1, 0, 0 };
	int yPos[4] = { 0, 0, -1, 1 };

	for (int i = 0; i < 4; i++)
	{
		int idX, idY;

		idX = x + xPos[i];
		idY = y + yPos[i];

		if (idX < 0 || idX >= N || idY < 0 || idY >= N)
		{
			continue;
		}

		if (flooded[idX][idY])
		{
			DFS(idX, idY);
		}
	}
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	minH = 100;
	maxH = 0;
	ans = 0;

	for (int n = 0; n < N; n++)
	{
		for (int i = 0; i < N; i++)
		{
			cin >> field[n][i];

			if (minH > field[n][i])
			{
				minH = field[n][i] - 1;
			}

			if (maxH < field[n][i])
			{
				maxH = field[n][i];
			}
		}
	}

	for (int h = minH; h <= maxH; h++)
	{
		refresh(h);

		int cnt;
		cnt = 0;

		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				if (flooded[i][j])
				{
					DFS(i, j);
					cnt += 1;
				}
			}
		}

		if (ans < cnt)
		{
			ans = cnt;
		}
	}

	cout << ans << "\n";

	return 0;
}