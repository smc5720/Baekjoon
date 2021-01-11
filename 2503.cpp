#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <cstring>

using namespace std;

// https://www.acmicpc.net/problem/2503

int N;
bool usedNum[1000];

void numCheck(string num, int strike, int ball)
{
	int a, b, c;

	a = num[0] - 48;
	b = num[1] - 48;
	c = num[2] - 48;

	int st, ba;

	for (int i = 1; i <= 9; i++)
	{
		for (int j = 1; j <= 9; j++)
		{
			for (int k = 1; k <= 9; k++)
			{
				st = 0;
				ba = 0;

				if (a == i)
				{
					st += 1;
				}

				if (b == i || c == i)
				{
					ba += 1;
				}

				if (b == j)
				{
					st += 1;
				}

				if (a == j || c == j)
				{
					ba += 1;
				}

				if (c == k)
				{
					st += 1;
				}

				if (a == k || b == k)
				{
					ba += 1;
				}

				int idx;
				idx = i * 100 + j * 10 + k;

				if (usedNum[idx])
				{
					if (st != strike || ba != ball)
					{
						usedNum[idx] = false;
					}
				}
			}
		}
	}
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	for (int i = 1; i <= 9; i++)
	{
		for (int j = 1; j <= 9; j++)
		{
			for (int k = 1; k <= 9; k++)
			{
				int idx;
				idx = i * 100 + j * 10 + k;

				if (i != j && i != k && j != k)
				{
					usedNum[idx] = true;
				}

				else
				{
					usedNum[idx] = false;
				}
			}
		}
	}

	cin >> N;

	for (int i = 0; i < N; i++)
	{
		string num;

		cin >> num;

		int strike, ball;

		cin >> strike >> ball;

		numCheck(num, strike, ball);
	}

	int sum;
	sum = 0;

	for (int i = 111; i <= 999; i++)
	{
		if (usedNum[i])
		{
			sum += 1;
		}
	}

	cout << sum << "\n";

	return 0;
}