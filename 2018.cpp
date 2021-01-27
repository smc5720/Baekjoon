#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>

using namespace std;

// https://www.acmicpc.net/problem/2018

int N;

int sumPointer(int left, int right)
{
	int sum;
	sum = 0;

	if (left == right)
	{
		return left;
	}

	else
	{
		for (int i = left; i <= right; i++)
		{
			sum += i;
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

	cin >> N;

	int ans;
	ans = 0;

	while (right <= N)
	{
		if (sumPointer(left, right) < N)
		{
			right += 1;
		}

		else if (sumPointer(left, right) > N)
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