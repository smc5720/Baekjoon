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

// https://www.acmicpc.net/problem/2638

int N, M;
int field[100][100];
queue <pair <int, int>> q;

void checkAir(int a, int b)
{
	bool check;
	check = false;

	int xPos[4] = { -1, 1, 0, 0 };
	int yPos[4] = { 0, 0, -1, 1 };

	for (int i = 0; i < 4; i++)
	{
		int x, y;
		x = xPos[i] + a;
		y = yPos[i] + b;

		if (x < 0 || x >= N || y < 0 || y >= M)
		{
			continue;
		}

		if (field[x][y] == 0)
		{
			field[a][b] = 0;
			check = true;
		}
	}

	if (check)
	{
		for (int i = 0; i < 4; i++)
		{
			int x, y;
			x = xPos[i] + a;
			y = yPos[i] + b;

			if (x < 0 || x >= N || y < 0 || y >= M)
			{
				continue;
			}

			if (field[x][y] == 2)
			{
				checkAir(x, y);
			}
		}
	}
}

void refreshAir()
{
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			if (field[i][j] == 2)
			{
				checkAir(i, j);
			}
		}
	}
}

void checkCheese(int a, int b)
{
	int cnt;
	cnt = 0;

	int xPos[4] = { -1, 1, 0, 0 };
	int yPos[4] = { 0, 0, -1, 1 };

	for (int i = 0; i < 4; i++)
	{
		int x, y;
		x = xPos[i] + a;
		y = yPos[i] + b;

		if (field[x][y] == 0)
		{
			cnt += 1;
		}
	}

	if (cnt >= 2)
	{
		q.push(make_pair(a, b));
	}
}

void meltCheese()
{
	while (!q.empty())
	{
		int x, y;
		x = q.front().first;
		y = q.front().second;

		field[x][y] = 0;

		q.pop();
	}
}

bool lastCheese()
{
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			if (field[i][j] == 1)
			{
				return true;
			}
		}
	}

	return false;
}

void printCheese()
{
	cout << "printCheese\n";

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			cout << field[i][j] << " ";
		}
		cout << "\n";
	}

	cout << "\n";
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> M;

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			cin >> field[i][j];

			if (field[i][j] == 0)
			{
				field[i][j] = 2;
			}

			if (i == 0 || j == 0 || i + 1 == N || j + 1 == M)
			{
				field[i][j] = 0;
			}
		}
	}

	refreshAir();
	// printCheese();

	int hours;
	hours = 0;

	while (lastCheese())
	{
		hours += 1;

		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < M; j++)
			{
				if (field[i][j] == 1)
				{
					checkCheese(i, j);
				}
			}
		}

		meltCheese();
		refreshAir();
		// printCheese();
	}

	cout << hours << "\n";

	return 0;
}