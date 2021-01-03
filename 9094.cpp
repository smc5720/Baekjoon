#include <iostream>
#include <math.h>
using namespace std;

// https://www.acmicpc.net/problem/9094

int main()
{
	int times;

	cin >> times;

	for (int i = 0; i < times; i++)
	{
		int n, m, count;

		cin >> n >> m;

		count = 0;

		for (int a = 1; a < n; a++)
		{
			for (int b = a + 1; b < n; b++)
			{
				int x, y;

				x = a * a + b * b + m;
				y = a * b;

				if (x % y == 0)
				{
					count++;
				}
			}
		}

		cout << count << endl;
	}

	return 0;
}