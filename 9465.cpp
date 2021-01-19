#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/9465

int T, n;
int price[2][100000];

int main()
{
	cin >> T;

	for (int i = 0; i < T; i++)
	{
		cin >> n;

		for (int j = 0; j < n; j++)
		{
			cin >> price[0][j];
		}

		for (int j = 0; j < n; j++)
		{
			cin >> price[1][j];
		}

		for (int j = 1; j < n; j++)
		{
			if (j == 1)
			{
				price[0][j] += price[1][j - 1];
				price[1][j] += price[0][j - 1];
			}

			else
			{
				price[0][j] += max(price[1][j - 1], price[1][j - 2]);
				price[1][j] += max(price[0][j - 1], price[0][j - 2]);
			}

			if (j + 1 == n)
			{
				cout << max(price[0][j], price[1][j]) << "\n";
			}
		}
	}

	return 0;
}