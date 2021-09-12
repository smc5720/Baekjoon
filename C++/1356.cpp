#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>

using namespace std;

// https://www.acmicpc.net/problem/1356
 
int N;
int sizeNum;
int arr[10];

bool checkNum()
{
	for (int i = 0; i <= sizeNum - 1; i++)
	{
		long long frontNum, backNum;
		frontNum = 0;
		backNum = 0;

		for (int j = 0; j <= i; j++)
		{
			if (j == 0)
			{
				frontNum = arr[j];
			}

			else
			{
				frontNum *= arr[j];
			}
		}

		for (int j = i + 1; j <= sizeNum; j++)
		{
			if (j == i + 1)
			{
				backNum = arr[j];
			}

			else
			{
				backNum *= arr[j];
			}
		}

		if (frontNum == backNum)
		{
			return true;
		}
	}

	return false;
}

void printArray()
{
	for (int i = 0; i <= sizeNum; i++)
	{
		cout << arr[i] << " ";
	}

	cout << "\n";
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	int num;
	num = N;

	sizeNum = log10(num);

	int idx;
	idx = sizeNum;

	while (idx >= 0)
	{
		arr[idx] = num % 10;
		num /= 10;
		idx -= 1;
	}

	if (checkNum())
	{
		cout << "YES\n";
	}

	else
	{
		cout << "NO\n";
	}

	return 0;
}