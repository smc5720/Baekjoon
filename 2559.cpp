#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>

using namespace std;

// https://www.acmicpc.net/problem/2559

int N, K;
int arr[100001];

int sumPointer(int left, int right)
{
	int sum;
	sum = 0;

	if (left == right)
	{
		return arr[left];
	}

	else
	{
		for (int i = left; i <= right; i++)
		{
			sum += arr[i];
		}

		return sum;
	}
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> K;

	int left, right;

	left = 1;
	right = 1 + (K - 1);

	for (int i = 1; i <= N; i++)
	{
		cin >> arr[i];
	}

	int num;
	int maxVal;

	num = sumPointer(left, right);
	maxVal = num;

	left += 1;
	right += 1;

	while (right <= N)
	{
		num = num - arr[left-1] + arr[right];

		if (maxVal < num)
		{
			maxVal = num;
		}

		left += 1;
		right += 1;
	}

	cout << maxVal << "\n";

	return 0;
}