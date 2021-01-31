#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>

using namespace std;

// https://www.acmicpc.net/problem/14606

int dp[11];

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	int n;
	cin >> n;

	dp[1] = 0;
	dp[2] = 1;

	for (int i = 3; i <= n; i++)
	{
		if (i % 2 == 0)
		{
			dp[i] = (i / 2) * (i / 2) + dp[i / 2] * 2;
		}

		else
		{
			dp[i] = (i / 2) * (i / 2 + 1) + dp[i / 2 + 1] + dp[i / 2];
		}
	}

	cout << dp[n] << "\n";

	return 0;
}