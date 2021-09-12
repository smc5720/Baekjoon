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

// https://www.acmicpc.net/problem/1740

long long N, ans;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	int i;
	i = 0;

	long long val, tr;
	val = 1;
	tr = 1;

	while (N >= val)
	{
		val *= 2;
		tr *= 3;
		i += 1;
	}

	val /= 2;
	tr /= 3;
	i -= 1;
	ans = 0;

	for (i; i >= 0; i--)
	{
		if (N >= val)
		{
			N -= val;
			ans += tr;
		}

		val /= 2;
		tr /= 3;
	}

	cout << ans << "\n";

	return 0;
}