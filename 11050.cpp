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

// https://www.acmicpc.net/problem/11050

int dp[11];

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	int N, K;
	cin >> N >> K;

	dp[0] = 1;

	for (int i = 1; i <= 10; i++)
	{
		dp[i] = dp[i - 1] * i;
	}

	int ans;
	ans = dp[N] / (dp[N - K] * dp[K]);

	cout << ans << "\n";

	return 0;
}