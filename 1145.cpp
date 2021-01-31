#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>

using namespace std;

// https://www.acmicpc.net/problem/1145

int arr[5];
bool isFinish;

bool func(int val)
{
	int cnt;
	cnt = 0;

	for (int i = 0; i < 5; i++)
	{
		if (val % arr[i] == 0)
		{
			cnt += 1;
		}
	}

	if (cnt >= 3)
	{
		cout << val << "\n";
		isFinish = true;
		return true;
	}

	return false;
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	for (int i = 0; i < 5; i++)
	{
		cin >> arr[i];
	}

	sort(arr, arr + 5);

	int val;
	val = arr[0];
	isFinish = false;

	while (!isFinish)
	{
		if (func(val))
		{
			break;
		}

		val += 1;
	}

	return 0;
}