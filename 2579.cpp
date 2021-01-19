#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/2579

int N;
int highCost[301];
int stair[301];

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 1; i <= N; i++)
	{
		cin >> stair[i];
	}

	for (int i = 1; i <= N; i++)
	{
		if (i == 1)
		{
			highCost[i] = stair[i];
		}

		else if (i == 2)
		{
			highCost[i] = stair[i] + stair[i - 1];
		}

		else
		{
			highCost[i] = stair[i] + max(stair[i - 1] + highCost[i - 3], highCost[i - 2]);
		}
	}

	int maxRes;
	maxRes = highCost[N];

	cout << maxRes << "\n";

	return 0;
}