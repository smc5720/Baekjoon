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

// https://www.acmicpc.net/problem/14502

int N, M;
int field[8][8];
int copyField[8][8];
bool visited[8][8];

void init()
{
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			visited[i][j] = false;
		}
	}
}

void copyArray()
{
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			copyField[i][j] = field[i][j];
		}
	}
}

void printField()
{
	cout << "\n";

	for (int n = 0; n < N; n++)
	{
		for (int m = 0; m < M; m++)
		{
			cout << copyField[n][m] << " ";
		}

		cout << "\n";
	}

	cout << "\n";
}

void DFS(int a, int b)
{
	copyField[a][b] = 2;

	int xPos[4] = { -1, 1, 0, 0 };
	int yPos[4] = { 0, 0, -1, 1 };

	for (int i = 0; i < 4; i++)
	{
		int x, y;
		x = a + xPos[i];
		y = b + yPos[i];

		if (x < 0 || x >= N || y < 0 || y >= M)
		{
			continue;
		}

		if (copyField[x][y] == 0)
		{
			DFS(x, y);
		}
	}
}

void virusFlood()
{
	for (int n = 0; n < N; n++)
	{
		for (int m = 0; m < M; m++)
		{
			if (copyField[n][m] == 2)
			{
				DFS(n, m);
			}
		}
	}
}

int countSpace()
{
	int cnt;
	cnt = 0;

	for (int n = 0; n < N; n++)
	{
		for (int m = 0; m < M; m++)
		{
			if (copyField[n][m] == 0)
			{
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

	int ans;
	ans = 0;

	init();

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			cin >> field[i][j];
		}
	}

	for (int i = 0; i < N * M - 2; i++)
	{
		if (field[i / M][i % M] == 0)
		{
			for (int j = i + 1; j < N * M - 1; j++)
			{
				if (field[j / M][j % M] == 0)
				{
					for (int k = j + 1; k < N * M; k++)
					{
						if (field[k / M][k % M] == 0)
						{
							field[i / M][i % M] = 1;
							field[j / M][j % M] = 1;
							field[k / M][k % M] = 1;

							copyArray();
							virusFlood();

							int cnt;
							cnt = countSpace();

							if (ans < cnt)
							{
								ans = cnt;
							}

							field[i / M][i % M] = 0;
							field[j / M][j % M] = 0;
							field[k / M][k % M] = 0;
						}
					}
				}
			}
		}
	}

	cout << ans << "\n";

	return 0;
}