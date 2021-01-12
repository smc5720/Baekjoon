#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/1436

int N;

bool check_six(int n)
{
	while (n > 100)
	{
		if (n % 1000 == 666)
		{
			return true;
		}

		n /= 10;
	}

	return false;
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	int n;
	cin >> n;

	if (n == 1)
	{
		cout << 666 << "\n";
	}

	else
	{
		int num = 1666;
		int idx = 1;

		while (1)
		{
			if (check_six(num))
			{
				idx++;
			}

			if (idx == n)
			{
				cout << num << "\n";
				break;
			}

			num++;
		}
	}

	return 0;
}