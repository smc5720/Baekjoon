#include <iostream>
#include <cstdio>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/10844

int N;
int numArray[101][10];
int divNum;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	long long sum;
	sum = 0;

	divNum = 1000000000;

	for (int i = 1; i <= N; i++)
	{
		for (int j = 0; j <= 9; j++)
		{
			numArray[i][j] = 0;

			if (i == 1)
			{
				if (j == 0)
				{
					numArray[i][j] = 0;
					continue;
				}

				numArray[i][j] = 1;
			}

			else
			{
				if (j > 0)
				{
					numArray[i][j] += numArray[i - 1][j - 1] % divNum;
				}

				if (j < 9)
				{
					numArray[i][j] += numArray[i - 1][j + 1] % divNum;
				}
			}

			if (i == N)
			{
				sum += numArray[i][j] % divNum;
			}
		}
	}

	cout << sum % divNum << "\n";

	return 0;
}