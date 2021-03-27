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

// https://www.acmicpc.net/problem/11727

#define DIV 10007

int n;
int dp[1001];

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> n;

	dp[0] = 1;
	dp[1] = 1;

	for (int i = 2; i <= n; i++)
	{
		dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 2]) % DIV;
	}

	cout << dp[n] << "\n";

	return 0;
}