#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/2240

int T, W;
int plum[1001];
int dp[3][1001][32];

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> T >> W;

	for (int i = 1; i <= T; i++)
	{
		cin >> plum[i];
	}

	for (int i = 1; i <= T; i++)
	{
		for (int j = 1; j <= W + 1; j++)
		{
			if (plum[i] == 1)
			{
				dp[1][i][j] = max(dp[1][i - 1][j] + 1, dp[2][i - 1][j - 1] + 1);
				dp[2][i][j] = max(dp[1][i - 1][j - 1], dp[2][i - 1][j]);
			}

			else
			{
				if (i == 1 && j == 1)
				{
					continue;
				}

				dp[1][i][j] = max(dp[1][i - 1][j], dp[2][i - 1][j - 1]);
				dp[2][i][j] = max(dp[1][i - 1][j - 1] + 1, dp[2][i - 1][j] + 1);
			}
		}
	}

	int maxRes;
	maxRes = 0;

	for (int j = 1; j <= W + 1; j++)
	{
		int n;
		n = max(dp[1][T][j], dp[2][T][j]);

		if (maxRes < n)
		{
			maxRes = n;
		}
	}

	cout << maxRes << "\n";

	return 0;
}