#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>

using namespace std;

// https://www.acmicpc.net/problem/1010

int T, N, M;
long long dp[30][30];

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> T;

	for (int i = 0; i < 30; i++)
	{
		dp[1][i] = i;
	}

	for (int i = 2; i < 30; i++)
	{
		for (int j = i; j < 30; j++)
		{
			for (int k = j - 1; k >= i - 1; k--)
			{
				dp[i][j] += dp[i - 1][k];
			}
		}
	}

	for (int l = 0; l < T; l++)
	{
		cin >> N >> M;
		cout << dp[N][M] << "\n";
	}

	return 0;
}