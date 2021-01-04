#include <iostream>
#include <math.h>
using namespace std;

// https://www.acmicpc.net/problem/6996

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
	int times;

	cin >> times;

	for (int i = 0; i < times; i++)
	{
		string A, B;

		cin >> A >> B;

		int size = A.size();

		if (size != B.size())
		{
			cout << A << " & " << B << " are NOT anagrams." << endl;
			continue;
		}

		int* array_A = new int[size];
		int* array_B = new int[size];

		for (int j = 0; j < size; j++)
		{
			array_A[j] = A[j];
			array_B[j] = B[j];
		}

		insertion_sort(array_A, size);
		insertion_sort(array_B, size);

		for (int j = 0; j < size; j++)
		{
			if (array_A[j] != array_B[j])
			{
				cout << A << " & " << B << " are NOT anagrams." << endl;
				break;
			}

			if (j == size - 1)
			{
				cout << A << " & " << B << " are anagrams." << endl;
			}
		}

		delete[] array_A;
		delete[] array_B;
	}

	return 0;
}