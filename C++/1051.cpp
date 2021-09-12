#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/1051

int N, M;
int square[51][51];

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> M;

	for (int i = 0; i < N; i++)
	{
		string num;
		cin >> num;

		for (int j = 0; j < M; j++)
		{
			square[i][j] = num[j] - 48;
		}
	}

	int size;

	if (N > M)
	{
		size = M;
	}

	else
	{
		size = N;
	}

	for (int i = size; i > 0; i--)
	{
		for (int j = 0; j <= N - i; j++)
		{
			for (int k = 0; k <= M - i; k++)
			{
				if (square[j][k] == square[j + i - 1][k] && square[j][k] == square[j][k + i - 1] && square[j][k] == square[j + i - 1][k + i - 1])
				{
					printf("%d\n", i * i);
					exit(0);
				}
			}
		}
	}

	return 0;
}