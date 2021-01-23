#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/11053

int N;
int A[1000];
int dp[1000];

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 0; i < N; i++)
	{
		cin >> A[i];
	}

	for (int i = 0; i < N; i++)
	{
		dp[i] = 1;

		for (int j = 0; j < i; j++)
		{
			if (A[i] > A[j])
			{
				if (dp[i] <= dp[j])
				{
					dp[i] = dp[j] + 1;
				}
			}
		}
	}

	int maxRes;
	maxRes = 0;

	for (int i = 0; i < N; i++)
	{
		if (maxRes < dp[i])
		{
			maxRes = dp[i];
		}
	}

	cout << maxRes << "\n";

	return 0;
}