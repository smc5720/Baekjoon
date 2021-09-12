#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>

using namespace std;

// https://www.acmicpc.net/problem/2343

long long N, M;
int arr[100000];
long long leftPt, rightPt;

bool func(int mid)
{
	int cnt;
	int sum;

	cnt = 0;
	sum = 0;

	for (int i = 0; i < N; i++)
	{
		if (sum < arr[i])
		{
			cnt += 1;

			if (mid < arr[i])
			{
				return false;
			}

			sum = mid;
		}

		sum -= arr[i];
	}

	if (cnt <= M)
	{
		return true;
	}

	else
	{
		return false;
	}
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> M;

	for (int i = 0; i < N; i++)
	{
		cin >> arr[i];
	}

	leftPt = 0;
	rightPt = 1000000000;

	long long ans;

	while (leftPt <= rightPt)
	{
		long long mid;
		mid = (leftPt + rightPt) / 2;

		if (func(mid))
		{
			ans = mid;
			rightPt = mid - 1;
		}

		else
		{
			leftPt = mid + 1;
		}
	}

	cout << ans << "\n";

	return 0;
}