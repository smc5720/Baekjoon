#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>

using namespace std;

// https://www.acmicpc.net/problem/2003

int N, M;
int arr[10001];

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

	int left, right;

	left = 1;
	right = 1;

	cin >> N >> M;

	for (int i = 1; i <= N; i++)
	{
		cin >> arr[i];
	}

	int ans;
	ans = 0;

	while (right <= N)
	{
		if (sumPointer(left, right) < M)
		{
			right += 1;
		}

		else if (sumPointer(left, right) > M)
		{
			left += 1;
		}

		else
		{
			ans += 1;
			right += 1;
		}
	}

	cout << ans << "\n";

	return 0;
}