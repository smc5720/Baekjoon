#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>
#include <stack>
#include <set>

using namespace std;

// https://www.acmicpc.net/problem/1012

int M, N, T, K;
int field[50][50];
queue <pair <int, int>> q;

void checkBug(int a, int b)
{
	field[a][b] = 0;

	int xPos[4] = { -1, 1, 0, 0 };
	int yPos[4] = { 0, 0, -1, 1 };

	for (int i = 0; i < 4; i++)
	{
		int x, y;
		x = xPos[i] + a;
		y = yPos[i] + b;

		if (x < 0 || x >= M || y < 0 || y >= N)
		{
			continue;
		}

		if (field[x][y] == 1)
		{
			checkBug(x, y);
		}
	}
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> T;

	for (int t = 0; t < T; t++)
	{
		cin >> M >> N >> K;

		int cnt;
		cnt = 0;

		for (int i = 0; i < M; i++)
		{
			for (int j = 0; j < N; j++)
			{
				field[i][j] = 0;
			}
		}

		for (int i = 0; i < K; i++)
		{
			int a, b;
			cin >> a >> b;
			field[a][b] = 1;
		}

		for (int i = 0; i < M; i++)
		{
			for (int j = 0; j < N; j++)
			{
				if (field[i][j] == 1)
				{
					checkBug(i, j);
					cnt += 1;
				}
			}
		}

		cout << cnt << "\n";
	}

	return 0;
}