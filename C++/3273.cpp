#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/3273

int a[100000];
int n, x;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> n;

	for (int i = 0; i < n; i++)
	{
		cin >> a[i];
	}

	sort(a, a + n);

	cin >> x;

	int leftPt, rightPt;
	leftPt = 0;
	rightPt = n - 1;

	int ans;
	ans = 0;

	while (leftPt < rightPt)
	{
		int sum;
		sum = a[leftPt] + a[rightPt];

		if (sum == x)
		{
			leftPt += 1;
			rightPt -= 1;
			ans += 1;
		}

		else if (sum > x)
		{
			rightPt -= 1;
		}

		else
		{
			leftPt += 1;
		}
	}

	cout << ans << "\n";

	return 0;
}