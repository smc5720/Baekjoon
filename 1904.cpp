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

// https://www.acmicpc.net/problem/1904

#define DIV 15746

int n; 
long long dp[1000001];

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);
 
	cin >> n;

	dp[0] = 1;
	dp[1] = 1;

	for (int i = 2; i <= n; i++)
	{
		dp[i] = dp[i - 1] + dp[i - 2];
		dp[i] %= DIV;
	}

	cout << dp[n] << "\n";

	return 0;
}