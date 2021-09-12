#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <cstring>

using namespace std;

// https://www.acmicpc.net/problem/2858

int L, W;
int R, B;

int main(void)
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> R >> B;

	int num;
	num = R + B;

	for (int i = 1; i <= (num / 2 + 1); i++)
	{
		if (num % i == 0)
		{
			int a;
			a = num / i;

			if ((a - 2) * (i - 2) == B && (2 * a + 2 * i - 4) == R)
			{
				if (a > i)
				{
					L = a;
					W = i;
				}

				else
				{
					L = a;
					W = i;
				}

				break;
			}
		}
	}

	cout << L << " " << W << "\n";

	return 0;
}