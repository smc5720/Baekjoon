#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>

using namespace std;

// https://www.acmicpc.net/problem/3079

long long N, M;
long long arr[100000];
long long leftPt, rightPt;

bool func(long long mid)
{
	long long sum;
	sum = 0;

	for (int i = 0; i < N; i++)
	{
		sum += mid / arr[i];
	}

	if (sum >= M)
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

	sort(arr, arr + N);

	if (N > 0)
	{
		leftPt = 0;
		rightPt = M * arr[N - 1];
	}

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