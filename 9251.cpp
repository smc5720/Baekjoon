#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/11053

string A, B;
int dp[1000][1000];

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> A >> B;

	int sizeA, sizeB;

	sizeA = A.length();
	sizeB = B.length();

	for (int i = 0; i < sizeA; i++)
	{
		for (int j = 0; j < sizeB; j++)
		{
			if (i == 0)
			{
				if (j == 0)
				{
					if (A[i] == B[j])
					{
						dp[i][j] = 1;
					}

					else
					{
						dp[i][j] = 0;
					}
				}

				else if (j > 0)
				{
					if (A[i] == B[j])
					{
						dp[i][j] = 1;
					}

					else
					{
						dp[i][j] = dp[i][j - 1];
					}
				}
			}

			else if (i > 0)
			{
				if (j == 0)
				{
					if (A[i] == B[j])
					{
						dp[i][j] = 1;
					}

					else
					{
						dp[i][j] = dp[i - 1][j];
					}
				}

				else if (j > 0)
				{
					if (A[i] == B[j])
					{
						dp[i][j] = dp[i - 1][j - 1] + 1;
					}

					else
					{
						dp[i][j] = max(dp[i][j - 1], dp[i - 1][j]);
					}
				}
			}
		}
	}

	if (sizeA > 0 && sizeB > 0)
	{
		cout << dp[sizeA - 1][sizeB - 1] << "\n";
	}

	return 0;
}