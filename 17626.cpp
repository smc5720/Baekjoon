#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>
#include <stack>
#include <set>

using namespace std;

// https://www.acmicpc.net/problem/17626

int n;
int dp[50001];

int main(void)
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> n;

	dp[0] = 0;

	for (int i = 1; i <= n; i++)
	{
		dp[i] = i;
	}

	for (int i = 1; i * i <= n; i++)
	{
		dp[i * i] = 1;
	}

	for (int i = 1; i <= n; i++)
	{
		for (int j = 1; j <= int(sqrt(i)); j++)
		{
			dp[i] = min(dp[i], dp[i - j * j] + dp[j * j]);
		}
	}

	cout << dp[n] << "\n";

	return 0;
}