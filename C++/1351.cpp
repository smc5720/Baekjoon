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

// https://www.acmicpc.net/problem/1351 

long long N, P, Q;
map <long long, long long> m;

long long func(long long n)
{
	if (m.count(n) > 0)
	{
		return m[n];
	}

	else
	{
		return m[n] = func(n / P) + func(n / Q);
	}
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> P >> Q;

	m[0] = 1;
	cout << func(N) << "\n";

	return 0;
}