#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>

using namespace std;

// https://www.acmicpc.net/problem/1072

long long X, Y, Z;
long long leftPt, rightPt;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> X >> Y;

	Z = Y * 100 / X;

	leftPt = 1;
	rightPt = X;

	long long ans;
	ans = -1;

	while (leftPt <= rightPt)
	{
		long long mid;
		mid = (leftPt + rightPt) / 2;

		int P;
		P = (Y + mid) * 100 / (X + mid);

		if (P <= Z)
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