#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/3085

int N;
char board[51][51];
int maxCandy;

void candyCheck(int row, int col)
{
	int up, down;
	up = 1;
	down = 1;

	int sum;
	sum = 1;

	// ╩С
	if (row > 0)
	{
		while (board[row][col] == board[row - up][col])
		{
			sum += 1;
			up += 1;

			if (row - up < 0)
			{
				break;
			}
		}
	}

	// го
	if (row < N - 1)
	{
		while (board[row][col] == board[row + down][col])
		{
			sum += 1;
			down += 1;

			if (row + down > N - 1)
			{
				break;
			}
		}
	}

	if (maxCandy < sum)
	{
		maxCandy = sum;
	}

	int left, right;
	left = 1;
	right = 1;

	sum = 1;

	// аб
	if (col > 0)
	{
		while (board[row][col] == board[row][col - left])
		{
			sum += 1;
			left += 1;

			if (col - left < 0)
			{
				break;
			}
		}
	}

	// ©Л
	if (col < N - 1)
	{
		while (board[row][col] == board[row][col + right])
		{
			sum += 1;
			right += 1;

			if (col + right > N - 1)
			{
				break;
			}
		}
	}

	if (maxCandy < sum)
	{
		maxCandy = sum;
	}
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	maxCandy = 0;

	for (int i = 0; i < N; i++)
	{
		string row;
		cin >> row;

		for (int j = 0; j < N; j++)
		{
			board[i][j] = row[j];
		}
	}

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			char ca;

			// ╩С
			if (i > 0)
			{
				ca = board[i][j];
				board[i][j] = board[i - 1][j];
				board[i - 1][j] = ca;

				candyCheck(i - 1, j);

				board[i - 1][j] = board[i][j];
				board[i][j] = ca;
			}

			// го
			if (i < N - 1)
			{
				ca = board[i][j];
				board[i][j] = board[i + 1][j];
				board[i + 1][j] = ca;

				candyCheck(i + 1, j);

				board[i + 1][j] = board[i][j];
				board[i][j] = ca;
			}

			// аб
			if (j > 0)
			{
				ca = board[i][j];
				board[i][j] = board[i][j - 1];
				board[i][j - 1] = ca;

				candyCheck(i, j - 1);

				board[i][j - 1] = board[i][j];
				board[i][j] = ca;
			}

			// ©Л
			if (j < N - 1)
			{
				ca = board[i][j];
				board[i][j] = board[i][j + 1];
				board[i][j + 1] = ca;

				candyCheck(i, j + 1);

				board[i][j + 1] = board[i][j];
				board[i][j] = ca;
			}
		}
	}

	printf("%d\n", maxCandy);

	return 0;
}