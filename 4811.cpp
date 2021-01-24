#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/4811

int N;
long long dp[31][31];

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 0; i <= 30; i++)
	{
		for (int j = 0; j <= 30; j++)
		{
			if (i == 0)
			{
				dp[i][j] = 1;
			}

			else
			{
				if (j == 0)
				{
					dp[i][j] = dp[i - 1][1];
				}

				else
				{
					dp[i][j] = dp[i - 1][j + 1] + dp[i][j - 1];
				}
			}
		}
	}

	while (N != 0)
	{
		cout << dp[N - 1][1] << "\n";
		cin >> N;
	}

	return 0;
}