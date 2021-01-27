#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>

using namespace std;

// https://www.acmicpc.net/problem/1644

int N;

// true면 소수임을 의미한다.
bool pnum[4000001];
int leftPt, rightPt;
int arr[4000001];

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 2; i <= N; i++)
	{
		pnum[i] = true;
	}

	int size;
	size = 0;

	for (int i = 2; i <= N; i++)
	{
		if (pnum[i])
		{
			size += 1;

			int mul;
			mul = 2;

			while (i * mul <= N)
			{
				pnum[i * mul] = false;
				mul += 1;
			}
		}
	}

	int idx;
	idx = 1;

	for (int i = 2; i <= N; i++)
	{
		if (pnum[i])
		{
			if (idx <= size)
			{
				arr[idx] = i;
			}

			idx += 1;
		}
	}

	leftPt = 1;
	rightPt = 1;

	int sum;
	sum = arr[leftPt];

	int ans;
	ans = 0;

	while (rightPt <= size)
	{
		if (sum > N)
		{
			sum -= arr[leftPt];
			leftPt += 1;
		}

		else if (sum < N)
		{
			rightPt += 1;
			sum += arr[rightPt];
		}

		else
		{
			ans += 1;
			rightPt += 1;
			sum += arr[rightPt];
		}
	}

	cout << ans << "\n";

	return 0;
}