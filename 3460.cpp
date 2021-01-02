#include <iostream>
#include <math.h>
using namespace std;

// https://www.acmicpc.net/problem/3460

int log2(int x)
{
	return log(x) / log(2);
}

int main()
{
	int times, num;

	cin >> times;

	for (int i = 0; i < times; i++)
	{
		cin >> num;

		int n;

		n = log2(num) + 1;

		int* array = new int[n];

		for (int i = 0; i < n; i++)
		{
			array[i] = 0;
		}

		while (num > 0)
		{
			int k = log2(num);

			array[k] = 1;

			int m = pow(2, k);

			num -= m;
		}

		for (int i = 0; i < n; i++)
		{
			if (array[i])
			{
				cout << i << " ";
			}
		}

		cout << endl;

		delete[] array;
	}

	return 0;
}