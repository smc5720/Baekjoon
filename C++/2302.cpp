#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/2302

int N, M;
int dp[41];
bool vip[41] = { false };
int result;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> M;

	dp[0] = 1;
	dp[1] = 1;
	dp[2] = 2;

	for (int i = 3; i <= N; i++)
	{
		dp[i] = dp[i - 1] + dp[i - 2];
	}

	for (int i = 1; i <= M; i++)
	{
		int num;
		cin >> num;
		vip[num] = true;
	}

	result = 1;

	int cnt;
	cnt = 0;

	for (int i = 1; i <= N; i++)
	{
		if (!vip[i])
		{
			cnt += 1;

			if (i == N)
			{
				result *= dp[cnt];
			}
		}

		else
		{
			result *= dp[cnt];
			cnt = 0;
		}
	}

	cout << result << "\n";

	return 0;
}