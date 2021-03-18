#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/1149

int N;
int cost[1001][3];
int house[1001][3]; 

int main()
{ 
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 1; i <= N; i++)
	{
		for (int j = 0; j < 3; j++)
		{
			cin >> cost[i][j];
		}
	}

	for (int i = 1; i <= N; i++)
	{
		if (i == 1)
		{
			house[i][0] = cost[i][0];
			house[i][1] = cost[i][1];
			house[i][2] = cost[i][2];
		}

		else
		{
			house[i][0] = min(house[i - 1][1], house[i - 1][2]) + cost[i][0];
			house[i][1] = min(house[i - 1][0], house[i - 1][2]) + cost[i][1];
			house[i][2] = min(house[i - 1][0], house[i - 1][1]) + cost[i][2];
		}
	}

	int minRes;
	minRes = min(house[N][0], house[N][1]);
	minRes = min(minRes, house[N][2]);

	cout << minRes << "\n";

	return 0;
}