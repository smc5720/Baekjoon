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

// https://www.acmicpc.net/problem/11399

int N;
long long P[1001];
long long dp[1001];
long long sum;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	P[0] = 0;
	sum = 0;

	for (int i = 1; i <= N; i++)
	{
		cin >> P[i];
	}

	sort(P, P + N + 1);

	dp[0] = 0;

	for (int i = 1; i <= N; i++)
	{
		dp[i] = dp[i - 1] + P[i];
		sum += dp[i];
	}

	cout << sum << "\n";

	return 0;
}