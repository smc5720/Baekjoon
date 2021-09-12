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

// https://www.acmicpc.net/problem/16956

int R, C;
char field[500][500];

bool searchSheep(int a, int b)
{
	int xPos[4] = { -1, 1, 0, 0 };
	int yPos[4] = { 0, 0, -1, 1 };

	for (int i = 0; i < 4; i++)
	{
		int x, y;
		x = a + xPos[i];
		y = b + yPos[i];

		if (x < 0 || x >= R || y < 0 || y >= C)
		{
			continue;
		}

		if (field[x][y] == 'S')
		{
			return true;
		}
	}

	return false;
}

void printField()
{
	cout << "1\n";

	for (int i = 0; i < R; i++)
	{
		for (int j = 0; j < C; j++)
		{
			cout << field[i][j];
		}
		cout << "\n";
	}
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> R >> C;

	for (int i = 0; i < R; i++)
	{
		for (int j = 0; j < C; j++)
		{
			cin >> field[i][j];

			if (field[i][j] == '.')
			{
				field[i][j] = 'D';
			}
		}
	}

	for (int i = 0; i < R; i++)
	{
		for (int j = 0; j < C; j++)
		{
			if (field[i][j] == 'W')
			{
				if (searchSheep(i, j))
				{
					cout << 0 << "\n";
					exit(0);
				}
			}
		}
	}

	printField();

	return 0;
}