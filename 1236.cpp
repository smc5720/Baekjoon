#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/1236

char map[50][50];
int N, M;

int main()
{
	cin >> N >> M;

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			cin >> map[i][j];
		}
	}

	int x, y;
	x = 0;
	y = 0;

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			if (map[i][j] == 'X')
			{
				y += 1;
				break;
			}
		}
	}

	for (int j = 0; j < M; j++)
	{
		for (int i = 0; i < N; i++)
		{
			if (map[i][j] == 'X')
			{
				x += 1;
				break;
			}
		}
	}

	int result;
	result = max(N - y, M - x);

	cout << result << "\n";

	return 0;
}