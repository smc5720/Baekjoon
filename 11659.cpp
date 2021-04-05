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

// https://www.acmicpc.net/problem/11659

int N, M;
int dp[100001];

int main(void)
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> M;

	dp[0] = 0;

	for (int i = 1; i <= N; i++)
	{
		int a;
		cin >> a;
		dp[i] = dp[i - 1] + a;
	}

	for (int i = 0; i < M; i++)
	{
		int a, b;
		cin >> a >> b;
		cout << dp[b] - dp[a - 1] << "\n";
	}

	return 0;
}