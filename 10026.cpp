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

// https://www.acmicpc.net/problem/10026

int N;
char grid[100][100];
char dGrid[100][100];

void DFS(int a, int b)
{
	char c;
	c = grid[a][b];
	grid[a][b] = 'C';

	int xPos[4] = { -1, 1, 0, 0 };
	int yPos[4] = { 0, 0, -1, 1 };

	for (int i = 0; i < 4; i++)
	{
		int x, y;
		x = a + xPos[i];
		y = b + yPos[i];

		if (x < 0 || x >= N || y < 0 || y >= N)
		{
			continue;
		}

		if (c == grid[x][y])
		{
			DFS(x, y);
		}
	}
}

void dDFS(int a, int b)
{
	char c;
	c = dGrid[a][b];
	dGrid[a][b] = 'C';

	int xPos[4] = { -1, 1, 0, 0 };
	int yPos[4] = { 0, 0, -1, 1 };

	for (int i = 0; i < 4; i++)
	{
		int x, y;
		x = a + xPos[i];
		y = b + yPos[i];

		if (x < 0 || x >= N || y < 0 || y >= N)
		{
			continue;
		}

		if (c == dGrid[x][y])
		{
			dDFS(x, y);
		}
	}
}

void printGrid()
{
	cout << "\n-----Grid-----\n";

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			cout << grid[i][j] << " ";
		}
		cout << "\n";
	}

	cout << "\n";
	cout << "\n-----dGrid-----\n";
	
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			cout << dGrid[i][j] << " ";
		}
		cout << "\n";
	}

	cout << "\n";
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			cin >> grid[i][j];

			if (grid[i][j] == 'G')
			{
				dGrid[i][j] = 'R';
			}

			else
			{
				dGrid[i][j] = grid[i][j];
			}
		}
	}

	int cnt, dCnt;
	cnt = 0;
	dCnt = 0;

	// printGrid();

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			if (grid[i][j] != 'C')
			{
				DFS(i, j);
				cnt += 1;
			}
		}
	}

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			if (dGrid[i][j] != 'C')
			{
				dDFS(i, j);
				dCnt += 1;
			}
		}
	}

	cout << cnt << " " << dCnt << "\n";

	return 0;
}