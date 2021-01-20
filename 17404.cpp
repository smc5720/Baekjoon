#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/17404

int N;
int minCost[1001][3];
int house[1001][3];
int minRes;

#define MAX 1000001;

int main()
{
	cin >> N;

	minRes = MAX;

	for (int i = 1; i <= N; i++)
	{
		for (int j = 0; j < 3; j++)
		{
			cin >> house[i][j];
		}
	}

	for (int k = 0; k < 3; k++)
	{
		for (int m = 0; m < 3; m++)
		{
			if (k == m)
			{
				minCost[1][m] = house[1][m];
			}

			else
			{
				minCost[1][m] = MAX;
			}
		}

		for (int i = 2; i <= N; i++)
		{
			minCost[i][0] = house[i][0] + min(minCost[i - 1][1], minCost[i - 1][2]);
			minCost[i][1] = house[i][1] + min(minCost[i - 1][0], minCost[i - 1][2]);
			minCost[i][2] = house[i][2] + min(minCost[i - 1][0], minCost[i - 1][1]);
		}

		for (int i = 0; i < 3; i++)
		{
			if (k != i)
			{
				minRes = min(minRes, minCost[N][i]);
			}
		}
	}
	
	cout << minRes << "\n";

	return 0;
}