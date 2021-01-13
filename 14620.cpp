#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/14620

int N;
int G[10][10];
bool flower[10][10];
int minTax;

bool checkFlower(int row, int col)
{
	if (flower[row + 1][col])
	{
		return false;
	}

	if (flower[row - 1][col])
	{
		return false;
	}

	if (flower[row][col + 1])
	{
		return false;
	}

	if (flower[row][col - 1])
	{
		return false;
	}

	return true;
}

void DFS(int row, int col, int cnt, int sum)
{
	if (cnt == 0)
	{
		if (sum < minTax)
		{
			minTax = sum;
		}

		return;
	}

	else
	{
		for (int i = row; i < N - 1; i++)
		{
			for (int j = 1; j < N - 1; j++)
			{
				if (flower[i][j] == false)
				{
					if (checkFlower(i, j))
					{
						flower[i][j] = true;
						flower[i + 1][j] = true;
						flower[i - 1][j] = true;
						flower[i][j + 1] = true;
						flower[i][j - 1] = true;

						sum += G[i][j];
						sum += G[i + 1][j];
						sum += G[i - 1][j];
						sum += G[i][j + 1];
						sum += G[i][j - 1];

						DFS(i, j, cnt - 1, sum);

						flower[i][j] = false;
						flower[i + 1][j] = false;
						flower[i - 1][j] = false;
						flower[i][j + 1] = false;
						flower[i][j - 1] = false;

						sum -= G[i][j];
						sum -= G[i + 1][j];
						sum -= G[i - 1][j];
						sum -= G[i][j + 1];
						sum -= G[i][j - 1];
					}
				}
			}
		}
	}
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	minTax = 10000;

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			cin >> G[i][j];
			flower[i][j] = false;
		}
	}

	int cnt;
	cnt = 3;

	int sum;
	sum = 0;

	DFS(1, 1, cnt, sum);

	printf("%d\n", minTax);

	return 0;
}