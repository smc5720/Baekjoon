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

// https://www.acmicpc.net/problem/13398

int n;
int arr[100000];
int dp[100000][2];
int ans;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> n;

	for (int i = 0; i < n; i++)
	{
		cin >> arr[i];
	}

	ans = -100000000;

	dp[0][0] = arr[0];
	dp[0][1] = 0;

	for (int i = 1; i < n; i++)
	{
		if (dp[i - 1][0] > 0)
		{
			dp[i][0] = arr[i] + dp[i - 1][0];
		}

		else
		{
			dp[i][0] = arr[i];
		}

		if (ans < dp[i][0])
		{
			ans = dp[i][0];
		}
	}

	for (int i = 1; i < n; i++)
	{
		int a, b;
		a = dp[i - 1][0];
		b = dp[i - 1][1] + arr[i];
		dp[i][1] = max(a, b);

		if (ans < dp[i][1])
		{
			ans = dp[i][1];
		}
	}

	if (n == 1)
	{
		ans = arr[0];
	}

	cout << ans << "\n";

	return 0;
}