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

// https://www.acmicpc.net/problem/2156

int n;
int arr[10000];
int dp[10000];
 
int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> n;

	for (int i = 0; i < 10000; i++)
	{
		arr[i] = 0;
		dp[i] = 0;
	}

	for (int i = 0; i < n; i++)
	{
		cin >> arr[i];
	}

	dp[0] = arr[0];
	dp[1] = arr[0] + arr[1];
	dp[2] = max(arr[0], arr[1]) + arr[2];
	dp[3] = max(dp[0] + arr[2], dp[1]) + arr[3];

	for (int i = 4; i < n; i++)
	{
		int mx;
		mx = max(dp[i - 3] + arr[i - 1], dp[i - 2]);
		dp[i] = max(mx, dp[i - 4] + arr[i - 1]) + arr[i];
	}

	int ans;
	ans = 0;

	for (int i = 0; i < n; i++)
	{
		if (ans < dp[i])
		{
			ans = dp[i];
		}
	}

	cout << ans << "\n";

	return 0;
}