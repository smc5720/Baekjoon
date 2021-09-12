#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>
#include <stack>
#include <set>

using namespace std;

// https://www.acmicpc.net/problem/1300

int N, k;
int leftPt, rightPt;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> k;

	leftPt = 1;
	rightPt = k;

	int ans;

	while (leftPt <= rightPt)
	{
		int mid;
		mid = (leftPt + rightPt) / 2;

		int cnt;
		cnt = 0;

		for (int i = 1; i <= N; i++)
		{
			cnt += min(N, mid / i);
		}

		if (cnt < k)
		{
			leftPt = mid + 1;
		}
		
		else
		{
			ans = mid;
			rightPt = mid - 1;
		}
	}

	cout << ans << "\n";

	return 0;
}