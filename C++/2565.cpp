#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/2565

int n;
pair<int, int> wire[100];
int dp[100];

int maxVal;

int main()
{
	cin >> n;

	for (int i = 0; i < n; i++)
	{
		cin >> wire[i].first >> wire[i].second;
	}

	sort(wire, wire + n);

	maxVal = 0;

	for (int i = 0; i < n; i++)
	{
		int cnt;
		cnt = 1;

		for (int j = 0; j < i; j++)
		{
			if (dp[j] < cnt)
			{
				continue;
			}

			if (wire[i].second > wire[j].second)
			{
				cnt += 1;
			}
		}

		dp[i] = cnt;
		
		if (maxVal < dp[i])
		{
			maxVal = dp[i];
		}
	}

	cout << n - maxVal << "\n";

	return 0;
}