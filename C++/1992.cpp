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

// https://www.acmicpc.net/problem/1992

int N;
int field[65][65];

void printField()
{
	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j <= N; j++)
		{
			cout << field[i][j];
		}

		cout << "\n";
	}
}

void func(int x, int y, int size)
{
	int check;
	check = field[x][y];

	for (int i = x; i < x + size; i++)
	{
		for (int j = y; j < y + size; j++)
		{
			if (check != field[i][j])
			{
				cout << "(";
				func(x, y, size / 2);
				func(x, y + size / 2, size / 2);
				func(x + size / 2, y, size / 2);
				func(x + size / 2, y + size / 2, size / 2);
				cout << ")";
				return;
			}
		}
	}

	cout << check;
}

int main(void)
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N;

	for (int i = 1; i <= N; i++)
	{
		string s;
		cin >> s;

		for (int j = 1; j <= N; j++)
		{
			field[i][j] = s[j - 1] - '0';
		}
	}

	func(1, 1, N);

	return 0;
}