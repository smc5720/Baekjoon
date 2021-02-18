#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>
#include <stack>

using namespace std;

// https://www.acmicpc.net/problem/11060

int N;
int A[1000];
int dp[1000];

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 0; i < N; i++)
	{
		cin >> A[i];
		dp[i] = 10000;
	}

	dp[0] = 0;

	for (int i = 0; i < N; i++)
	{
		for (int j = 1; j <= A[i]; j++)
		{
			dp[i + j] = min(dp[i + j], dp[i] + 1);
		}
	}

	if (N > 0)
	{
		if (dp[N - 1] == 10000)
		{
			cout << -1 << "\n";
		}

		else
		{
			cout << dp[N - 1] << "\n";
		}
	}

	return 0;
}