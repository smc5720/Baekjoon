#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/1932

int n;
int level[501][501];
int maxCost[501][501];

#define MIN -1;

int main()
{
	cin >> n;

	for (int i = 1; i <= n; i++)
	{
		for (int j = 1; j <= i; j++)
		{
			cin >> level[i][j];
		}
	}

	for (int i = 1; i <= n; i++)
	{
		for (int j = 1; j <= i; j++)
		{
			if (i == 1)
			{
				maxCost[i][j] = level[i][j];
			}

			else
			{
				if (j > 1 && j < i)
				{
					maxCost[i][j] = level[i][j] + max(maxCost[i - 1][j - 1], maxCost[i - 1][j]);
				}

				else
				{
					if (j == 1)
					{
						maxCost[i][j] = level[i][j] + maxCost[i - 1][j];
					}

					else if (j == i)
					{
						maxCost[i][j] = level[i][j] + maxCost[i - 1][j - 1];
					}
				}
			}
		}
	}

	int maxRes;
	maxRes = MIN;

	for (int i = 1; i <= n; i++)
	{
		if (maxRes < maxCost[n][i])
		{
			maxRes = maxCost[n][i];
		}
	}

	cout << maxRes << "\n";

	return 0;
}