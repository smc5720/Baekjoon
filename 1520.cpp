#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/1520

int M, N;
int map[501][501];
int dp[501][501] = { -1 };

void printDP()
{
	for (int i = 1; i <= M; i++)
	{
		for (int j = 1; j <= N; j++)
		{
			cout << dp[i][j] << " ";
		}

		cout << "\n";
	}

	cout << "\n";
}

int DFS(int row, int col)
{
	if (dp[row][col] != -1)
	{
		return dp[row][col];
	}

	// ╩С
	if (row - 1 >= 1)
	{
		if (map[row][col] > map[row - 1][col])
		{
			int num;
			num = DFS(row - 1, col);

			if (dp[row][col] == -1)
			{
				dp[row][col] = 0;
			}

			dp[row][col] += num;
		}
	}

	// го
	if (row + 1 <= M)
	{
		if (map[row][col] > map[row + 1][col])
		{
			int num;
			num = DFS(row + 1, col);

			if (dp[row][col] == -1)
			{
				dp[row][col] = 0;
			}

			dp[row][col] += num;
		}
	}

	// аб
	if (col - 1 >= 1)
	{
		if (map[row][col] > map[row][col - 1])
		{
			int num;
			num = DFS(row, col - 1);

			if (dp[row][col] == -1)
			{
				dp[row][col] = 0;
			}

			dp[row][col] += num;
		}
	}

	// ©Л
	if (col + 1 <= N)
	{
		if (map[row][col] > map[row][col + 1])
		{
			int num;
			num = DFS(row, col + 1);

			if (dp[row][col] == -1)
			{
				dp[row][col] = 0;
			}

			dp[row][col] += num;
		}
	}

	if (dp[row][col] > 0)
	{
		return dp[row][col];
	}

	else
	{
		return 0;
	}
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> M >> N;

	for (int i = 1; i <= M; i++)
	{
		for (int j = 1; j <= N; j++)
		{
			cin >> map[i][j];
			dp[i][j] = -1;
		}
	}

	dp[M][N] = 1;

	cout << DFS(1, 1) << "\n";

	return 0;
}