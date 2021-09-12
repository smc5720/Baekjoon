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

// https://www.acmicpc.net/problem/11687

int binSearch(int m)
{
	int leftPt, rightPt;

	leftPt = 1;
	rightPt = 1000000000;

	int ans;
	ans = -1;

	while (leftPt <= rightPt)
	{
		int mid, cnt;
		cnt = 0;
		mid = (leftPt + rightPt) / 2;

		for (int i = 5; i <= mid; i *= 5)
		{
			cnt += mid / i;
		}

		if (m <= cnt)
		{
			rightPt = mid - 1;

			if (cnt == m)
			{
				ans = mid;
			}
		}

		else
		{
			leftPt = mid + 1;
		}
	}

	return ans;
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	int M;
	cin >> M;

	cout << binSearch(M) << "\n";

	return 0;
}