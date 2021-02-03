#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>

using namespace std;

// https://www.acmicpc.net/problem/2805

long long N, M;
long long leftPt, rightPt;
int arr[1000000];

long long getTree(long long num)
{
	long long sum;
	sum = 0;

	for (int i = 0; i < N; i++)
	{
		if (arr[i] > num)
		{
			sum += arr[i] - num;
		}
	}

	return sum;
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
		rightPt = arr[N - 1];
	}

	long long ans;
	ans = 0;

	while (leftPt <= rightPt)
	{
		long long mid;
		mid = (leftPt + rightPt) / 2;

		long long tree;
		tree = getTree(mid);

		if (tree >= M)
		{
			ans = mid;
			leftPt = mid + 1;
		}

		else
		{
			rightPt = mid - 1;
		}
	}

	cout << ans << "\n";

	return 0;
}