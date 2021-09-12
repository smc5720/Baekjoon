#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>

using namespace std;

// https://www.acmicpc.net/problem/1654

int K, N;
int arr[10000];
long long leftPt, rightPt;

int getNum(int num)
{
	int sumVal;
	sumVal = 0;

	for (int i = 0; i < K; i++)
	{
		sumVal += arr[i] / num;
	}

	return sumVal;
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> K >> N;

	for (int i = 0; i < K; i++)
	{
		cin >> arr[i];
	}

	sort(arr, arr + K);

	if (K > 0)
	{
		leftPt = 1;
		rightPt = 2147483647;
	}

	long long ans;
	ans = 0;

	while (leftPt <= rightPt)
	{
		long long mid;
		mid = (leftPt + rightPt) / 2;

		int sumVal;
		sumVal = getNum(mid);

		if (sumVal >= N)
		{
			ans = max(ans, mid);
			leftPt = mid + 1;
		}

		else if (sumVal < N)
		{
			rightPt = mid - 1;
		}
	}

	cout << ans << "\n";

	return 0;
}