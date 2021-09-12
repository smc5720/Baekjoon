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

// https://www.acmicpc.net/problem/1107

#define MAX_VAL 1000000

int N, M;
bool broken[10];
int ans;

int check(int n)
{
	if (n == 0)
	{
		if (broken[n])
		{
			return 0;
		}

		else
		{
			return 1;
		}
	}

	int cnt;
	cnt = 0;

	while (n > 0)
	{
		if (broken[n % 10])
		{
			return 0;
		}

		cnt += 1;
		n /= 10;
	}

	return cnt;
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	for (int i = 0; i < 10; i++)
	{
		broken[i] = false;
	}

	cin >> N >> M;

	for (int i = 0; i < M; i++)
	{
		int v;
		cin >> v;
		broken[v] = true;
	}

	ans = abs(N - 100);

	for (int i = 0; i <= MAX_VAL; i++)
	{
		int n;
		n = check(i);

		if (n)
		{
			int c;
			c = n + abs(N - i);

			if (ans > c)
			{
				ans = c;
			}
		}
	}

	cout << ans << "\n";

	return 0;
}