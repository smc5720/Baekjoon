#include <iostream>
#include <math.h>
using namespace std;

// https://www.acmicpc.net/problem/2669

int main()
{
	int array[101][101];

	for (int i = 0; i <= 100; i++)
	{
		for (int j = 0; j <= 100; j++)
		{
			array[i][j] = 0;
		}
	}

	for (int i = 0; i < 4; i++)
	{
		int x1, y1, x2, y2;

		cin >> x1 >> y1 >> x2 >> y2;

		for (int j = x1; j < x2; j++)
		{
			for (int k = y1; k < y2; k++)
			{
				array[j][k] = 1;
			}
		}
	}

	int sum = 0;

	for (int i = 0; i <= 100; i++)
	{
		for (int j = 0; j <= 100; j++)
		{
			sum += array[i][j];
		}
	}

	cout << sum << endl;

	return 0;
}