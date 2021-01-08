#include <iostream>
#include <math.h>
using namespace std;

// https://www.acmicpc.net/problem/14889
  
int N;
int S[21][21];

bool team[21];

int sum1, sum2;
int minVal;

void calculate(int idx, int n)
{
	if (n == 0)
	{
		for (int i = 1; i <= N; i++)
		{
			for (int j = 1; j <= N; j++)
			{
				if (i != j)
				{
					if (team[i] == team[j])
					{
						if (team[i] == true)
						{
							sum1 += S[i][j];
						}

						else
						{
							sum2 += S[i][j];
						}
					}
				}
			}
		}

		int k;
		k = sum1 - sum2;

		sum1 = 0;
		sum2 = 0;

		if (k == 0)
		{
			cout << 0 << "\n";
			exit(0);
		}

		else
		{
			if (k < 0)
			{
				k = (-1) * k;
			}

			if (minVal == 100)
			{
				minVal = k;
			}

			else
			{
				if (minVal > k)
				{
					minVal = k;
				}
			}
		}

		return;
	}

	else
	{
		for (int i = idx + 1; i <= N; i++)
		{
			team[i] = true;
			calculate(i, n - 1);
			team[i] = false;
		}
	}
}

int main(void)
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 1; i <= N; i++)
	{
		team[i] = false;

		for (int j = 1; j <= N; j++)
		{
			cin >> S[i][j];
		}
	}

	team[1] = true;

	int n;
	n = N / 2;

	sum1 = 0;
	sum2 = 0;

	minVal = 100;

	calculate(1, n-1);

	cout << minVal << "\n";

	return 0;
}