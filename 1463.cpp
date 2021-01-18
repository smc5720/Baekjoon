#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/1463

int N;
int dp[1000001];

int main()
{
	cin >> N;

	dp[1] = 0;
	dp[2] = 1;
	dp[3] = 1;

	for (int i = 4; i <= N; i++)
	{
		int tmp;
		tmp = dp[i - 1] + 1;

		if (i % 3 == 0)
		{
			tmp = min(tmp, dp[i / 3] + 1);
		}

		if (i % 2 == 0)
		{
			tmp = min(tmp, dp[i / 2] + 1);
		}

		dp[i] = tmp;
	}

	cout << dp[N] << "\n";

	return 0;
}