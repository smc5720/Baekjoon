#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>

using namespace std;

// https://www.acmicpc.net/problem/1806

int N, S;
int arr[100001];
int leftPt, rightPt;

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

	cin >> N >> S;

	for (int i = 1; i <= N; i++)
	{
		cin >> arr[i];
	}

	int len;
	len = 0;

	leftPt = 1;
	rightPt = 1;

	int sum;
	sum = sumPointer(leftPt, rightPt);

	while (rightPt <= N)
	{
		if (sum < S)
		{
			rightPt += 1;
			sum += arr[rightPt];
		}

		else if (sum > S)
		{
			if (len == 0 || len > rightPt - leftPt + 1)
			{
				len = rightPt - leftPt + 1;
			}

			sum -= arr[leftPt];
			leftPt += 1;
		}

		else
		{
			if (len == 0 || len > rightPt - leftPt + 1)
			{
				len = rightPt - leftPt + 1;
			}

			rightPt += 1;
			sum += arr[rightPt];
		}
	}

	cout << len << "\n";

	return 0;
}