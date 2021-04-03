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

// https://www.acmicpc.net/problem/6064

int T;

int gcd(int a, int b)
{
	int c;
	while (b != 0)
	{
		c = a % b;
		a = b;
		b = c;
	}
	return a;
}

int lcm(int a, int b)
{
	return a * b / gcd(a, b);
}

int main(void)
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> T;

	for (int t = 0; t < T; t++)
	{
		int N, M, x, y;
		cin >> M >> N >> x >> y;

		int ans, maxVal;
		ans = -1;
		maxVal = lcm(N, M);

		int num;
		num = x;

		if (M == x && N == y)
		{
			cout << maxVal << "\n";
			continue;
		}

		while (num <= maxVal)
		{
			int a, b;
			a = num % M;
			b = num % N;

			if (a == 0)
			{
				a = M;
			}

			if (b == 0)
			{
				b = N;
			}

			if (a == x && b == y)
			{
				ans = num;
				break;
			}

			num += M;
		}

		cout << ans << "\n";
	}

	return 0;
}