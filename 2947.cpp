#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>

using namespace std;

// https://www.acmicpc.net/problem/2947

int arr[6];
bool isSorted;

bool checkSorted()
{
	for (int i = 1; i <= 4; i++)
	{
		if (arr[i] > arr[i + 1])
		{
			return false;
		}
	}

	return true;
}

void printArr()
{
	for (int i = 1; i <= 5; i++)
	{
		cout << arr[i] << " ";
	}

	cout << "\n";
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	isSorted = false;

	for (int i = 1; i <= 5; i++)
	{
		cin >> arr[i];
	}

	while (!checkSorted())
	{
		for (int i = 1; i <= 4; i++)
		{
			if (arr[i] > arr[i + 1])
			{
				int tmp;
				tmp = arr[i];
				arr[i] = arr[i + 1];
				arr[i + 1] = tmp;

				printArr();
			}
		}
	}

	return 0;
}