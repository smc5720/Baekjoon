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

// https://www.acmicpc.net/problem/2630

int N;
bool field[129][129];
int bCnt, wCnt;

void checkBox(int x, int y, int l)
{
	if (l == 1)
	{
		if (field[x][y])
		{
			bCnt += 1;
		}

		else
		{
			wCnt += 1;
		}

		return;
	}

	for (int i = x; i < x + l; i++)
	{
		for (int j = y; j < y + l; j++)
		{
			if (field[x][y] != field[i][j])
			{
				checkBox(x, y, l / 2);
				checkBox(x, y + (l / 2), l / 2);
				checkBox(x + (l / 2), y, l / 2);
				checkBox(x + (l / 2), y + (l / 2), l / 2);

				return;
			}
		}
	}

	if (field[x][y])
	{
		bCnt += 1;
	}

	else
	{
		wCnt += 1;
	}
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;
	bCnt = 0;
	wCnt = 0;

	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j <= N; j++)
		{
			cin >> field[i][j];
		}
	}

	checkBox(1, 1, N);

	cout << wCnt << "\n" << bCnt << "\n";

	return 0;
}