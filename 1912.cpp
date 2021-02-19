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

// https://www.acmicpc.net/problem/1912

int n;
int arr[100000];
int dp[100000];

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> n;

	for (int i = 0; i < n; i++)
	{
		cin >> arr[i];
	}

	dp[0] = arr[0];

	int ans;
	ans = dp[0];

	for (int i = 1; i < n; i++)
	{
		dp[i] = max(arr[i], dp[i - 1] + arr[i]);
		
		if (ans < dp[i])
		{
			ans = dp[i];
		}
	}

	cout << ans << "\n";

	return 0;
}