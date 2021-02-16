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

// https://www.acmicpc.net/problem/2828

int N, M, J;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> M >> J;

	int leftPos, rightPos, ans;
	leftPos = 1;
	rightPos = leftPos + M - 1;
	ans = 0;

	for (int i = 0; i < J; i++)
	{
		int pos;
		cin >> pos;

		rightPos = leftPos + M - 1;

		if (pos > rightPos)
		{
			leftPos += pos - rightPos;
			ans += pos - rightPos;
		}

		else if (pos < leftPos)
		{
			ans += leftPos - pos;
			leftPos = pos;
		}

		rightPos = leftPos + M - 1;
	}

	cout << ans << "\n";

	return 0;
}