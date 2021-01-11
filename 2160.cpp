#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <cstring>

using namespace std;

// https://www.acmicpc.net/problem/2160

int N;
bool map[51][5][7];
int minVal;
int minA, minB;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	minVal = 100;

	for (int i = 1; i <= N; i++)
	{
		for (int j = 0; j < 5; j++)
		{
			for (int k = 0; k < 7; k++)
			{
				char input;
				cin >> input;

				if (input == '.')
				{
					map[i][j][k] = false;
				}

				else
				{
					map[i][j][k] = true;
				}
			}
		}
	}

	for (int i = 1; i < N; i++)
	{
		for (int n = i + 1; n <= N; n++)
		{
			int sum = 0;

			for (int j = 0; j < 5; j++)
			{
				for (int k = 0; k < 7; k++)
				{
					if (map[i][j][k] == map[n][j][k])
					{
						continue;
					}

					else
					{
						sum += 1;
					}
				}
			}

			if (minVal > sum)
			{
				minVal = sum;

				minA = i;
				minB = n;

				if (minVal == 0)
				{
					printf("%d %d\n", minA, minB);
					exit(0);
				}
			}
		}
	}

	printf("%d %d\n", minA, minB);

	return 0;
}