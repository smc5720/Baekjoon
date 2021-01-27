#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>

using namespace std;

// https://www.acmicpc.net/problem/1940

int N, M;
int arr[15001];
int leftPt, rightPt;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> M;

	arr[0] = 0;

	for (int i = 1; i <= N; i++)
	{
		cin >> arr[i];
	}

	sort(arr, arr + N + 1);

	int ans;
	ans = 0;

	int sum;

	leftPt = 1;
	rightPt = N;

	while (rightPt > leftPt)
	{
		sum = arr[leftPt] + arr[rightPt];

		if (sum > M)
		{
			rightPt -= 1;
		}

		else if (sum < M)
		{
			leftPt += 1;
		}

		else
		{
			leftPt += 1;
			ans += 1;
			rightPt -= 1;
		}
	}

	cout << ans << "\n";

	return 0;
}