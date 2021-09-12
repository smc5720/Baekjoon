#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>

using namespace std;

// https://www.acmicpc.net/problem/2110

int N, C;
int x[200000];
int leftPt, rightPt;
int ans;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> C;

	for (int i = 0; i < N; i++)
	{
		cin >> x[i];
	}

	sort(x, x + N);

	if (N > 0)
	{
		leftPt = 1;
		rightPt = x[N - 1] - x[0];
	}

	while (leftPt <= rightPt)
	{
		int mid;
		mid = (leftPt + rightPt) / 2;

		int start;
		start = x[0];

		int cnt;
		cnt = 1;

		for (int i = 1; i < N; i++)
		{
			int d;
			d = x[i] - start;

			if (mid <= d)
			{
				cnt += 1;
				start = x[i];
			}
		}

		if (cnt >= C)
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