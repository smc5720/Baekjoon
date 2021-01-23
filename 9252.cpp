#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/11053

string A, B;
int dp[1000][1000];
int sizeA, sizeB;
char c[1001];
int idx;

void printTable()
{
	for (int i = -1; i < sizeA; i++)
	{
		for (int j = -1; j < sizeB; j++)
		{
			if (i == -1)
			{
				if (j == -1)
				{
					cout << "  ";
					continue;
				}

				cout << B[j] << " ";
			}

			else
			{
				if (j == -1)
				{
					cout << A[i] << " ";
				}

				else
				{
					cout << dp[i][j] << " ";
				}
			}
		}

		cout << "\n";
	}
}

void searchAnswer(int n)
{
	int row, col;

	row = sizeA - 1;
	col = sizeB - 1;

	while (n > 0)
	{
		while (col - 1 >= 0 && dp[row][col] == dp[row][col - 1])
		{
			col -= 1;
		}

		while (row - 1 >= 0 && dp[row][col] == dp[row - 1][col])
		{
			row -= 1;
		}

		c[n] = A[row];
		n -= 1;
		row -= 1;
	}
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> A >> B;

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

			idx = dp[i][j];
		}
	}

	// printTable();
	searchAnswer(idx);

	cout << idx << "\n";

	for (int i = 1; i <= idx; i++)
	{
		cout << c[i];
	}

	cout << "\n";

	return 0;
}