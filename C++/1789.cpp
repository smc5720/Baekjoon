#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>
#include <stack>

using namespace std;

// https://www.acmicpc.net/problem/1789

unsigned long long S, ans;
unsigned long long leftPt, rightPt, mid;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> S;

	leftPt = 1;
	rightPt = S;

	bool isSame;
	isSame = false;

	while (leftPt <= rightPt)
	{
		unsigned long long rs;
		mid = (leftPt + rightPt) / 2;
		rs = mid * (mid + 1) / 2;

		if (rs >= S)
		{
			ans = mid;
			rightPt = mid - 1;

			if (rs == S)
			{
				isSame = true;
			}

			else
			{
				isSame = false;
			}
		}

		else
		{
			leftPt = mid + 1;
		}
	}

	if (!isSame)
	{
		ans -= 1;
	}

	cout << ans << "\n";

	return 0;
}