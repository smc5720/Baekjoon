#include <iostream>
#include <math.h>
using namespace std;

// https://www.acmicpc.net/problem/1373

int main()
{
	string input;

	cin >> input;

	int size;
	size = input.size();

	int* bin = new int[size];

	for (int i = 0; i < size; i++)
	{
		bin[i] = input[size - 1 - i] - 48;
	}

	int* result = new int[size / 3 + 1];

	for (int i = 0; i < size / 3 + 1; i++)
	{
		result[i] = 0;
	}

	for (int i = 0; i <= size / 3; i++)
	{
		for (int j = 0; j < 3; j++)
		{
			if (i * 3 + j < size)
			{
				result[i] += bin[i * 3 + j] * pow(2, j);
			}
		}
	}

	for (int i = size / 3; i >= 0; i--)
	{
		if (i == size / 3 && i > 1)
		{
			if (result[i] != 0)
			{
				cout << result[i];
			}
		}

		else {
			cout << result[i];
		}
	}

	cout << endl;

	return 0;
}