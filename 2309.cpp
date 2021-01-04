#include <iostream>
#include <math.h>
using namespace std;

// https://www.acmicpc.net/problem/2309

void insertion_sort(int* arr, int n)
{
	for (int i = 1; i < n; i++)
	{
		int num = arr[i];

		for (int j = i - 1; j >= 0; j--)
		{
			if (arr[j] > num)
			{
				arr[j + 1] = arr[j];

				if (j == 0)
				{
					arr[j] = num;
				}
			}

			else
			{
				arr[j + 1] = num;
				break;
			}
		}
	}
}


int main()
{
	int array[9];

	int sum = 0;

	for (int i = 0; i < 9; i++)
	{
		cin >> array[i];

		sum += array[i];
	}

	bool breaker;
	breaker = false;

	for (int i = 0; i < 9; i++)
	{
		for (int j = i + 1; j < 9; j++)
		{
			if (sum - array[i] - array[j] == 100)
			{
				array[i] = -1;
				array[j] = -1;

				breaker = true;
				break;
			}
		}

		if (breaker == true)
		{
			break;
		}
	}

	insertion_sort(array, 9);

	for (int k = 2; k < 9; k++)
	{
		cout << array[k] << endl;
	}

	return 0;
}