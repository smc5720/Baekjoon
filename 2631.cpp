#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/2631

int N;
int val[201];
int dp[201];

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 1; i <= N; i++)
	{
		cin >> val[i];
	}

	for (int i = 1; i <= N; i++)
	{
		dp[i] = 1;

		for (int j = 1; j < i; j++)
		{
			if (val[i] > val[j] && dp[i] <= dp[j])
			{
				dp[i] = dp[j] + 1;
			}
		}
	}

	int maxVal;
	maxVal = 0;

	for (int i = 1; i <= N; i++)
	{
		if (maxVal < dp[i])
		{
			maxVal = dp[i];
		}
	}

	cout << N - maxVal << "\n";

	return 0;
}