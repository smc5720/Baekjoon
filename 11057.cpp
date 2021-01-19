#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/11057

int N;
int numArray[1001][10];
int divNum;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	int sum;
	sum = 0;

	divNum = 10007;

	for (int i = 1; i <= N; i++)
	{
		for (int j = 0; j < 10; j++)
		{
			if (i == 1)
			{
				numArray[i][j] = 1;
			}

			else
			{
				numArray[i][j] = 0;

				for (int k = 0; k <= j; k++)
				{
					numArray[i][j] += numArray[i - 1][k] % divNum;
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