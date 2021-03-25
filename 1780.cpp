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

// https://www.acmicpc.net/problem/1780

int N;
int arr[2188][2188];
int cnt[3];

void func(int x, int y, int n)
{
	int a;
	a = arr[x][y];

	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
		{
			if (a != arr[x + i][y + j])
			{
				func(x, y, n / 3);
				func(x + (n / 3), y, n / 3);
				func(x + 2 * (n / 3), y, n / 3);

				func(x, y + (n / 3), n / 3);
				func(x + (n / 3), y + (n / 3), n / 3);
				func(x + 2 * (n / 3), y + (n / 3), n / 3);

				func(x, y + 2 * (n / 3), n / 3);
				func(x + (n / 3), y + 2 * (n / 3), n / 3);
				func(x + 2 * (n / 3), y + 2 * (n / 3), n / 3);

				return;
			}
		}
	}

	cnt[1 + a] += 1;
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 0; i < 3; i++)
	{
		cnt[i] = 0;
	}

	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j <= N; j++)
		{
			cin >> arr[i][j];
		}
	}

	func(1, 1, N);

	for (int i = 0; i < 3; i++)
	{
		cout << cnt[i] << "\n";
	}

	return 0;
}