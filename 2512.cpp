#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>

using namespace std;

// https://www.acmicpc.net/problem/2512

int N;
int arr[10000];
int M;
int leftPt, rightPt;

int getBudget(int num)
{
	int sum;
	sum = 0;

	for (int i = 0; i < N; i++)
	{
		if (num < arr[i])
		{
			sum += num;
		}

		else
		{
			sum += arr[i];
		}
	}

	return sum;
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 0; i < N; i++)
	{
		cin >> arr[i];
	}

	sort(arr, arr + N);

	cin >> M;

	if (N > 0)
	{
		leftPt = 1;
		rightPt = arr[N - 1];
	}

	int ans;
	ans = 0;

	while (leftPt <= rightPt)
	{
		int mid;
		mid = (leftPt + rightPt) / 2;

		int budget;
		budget = getBudget(mid);

		if (budget <= M)
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