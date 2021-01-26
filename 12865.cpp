#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>

using namespace std;

// https://www.acmicpc.net/problem/12865

int N, K;
int W[101];
int V[101];
int dp[101][100001];

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> K;

	for (int i = 1; i <= N; i++)
	{
		cin >> W[i] >> V[i];
	}

	for (int i = 0; i <= N; i++)
	{
		for (int j = 0; j <= K; j++)
		{
			dp[i][j] = 0;
		}
	}

	int ans;
	ans = 0;

	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j <= K; j++)
		{
			if (j >= W[i])
			{
				dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - W[i]] + V[i]);
			}

			else
			{
				dp[i][j] = dp[i - 1][j];
			}

			ans = max(ans, dp[i][j]);
		}
	}

	cout << ans << "\n";

	return 0;
}