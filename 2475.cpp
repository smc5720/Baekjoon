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

// https://www.acmicpc.net/problem/2475

int ans;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	ans = 0;

	for (int i = 0; i < 5; i++)
	{
		int n;
		cin >> n;
		ans += n * n;
	}

	ans %= 10;

	cout << ans << "\n";

	return 0;
}