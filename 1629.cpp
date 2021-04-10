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

// https://www.acmicpc.net/problem/1629

int A, B, C;

long long func(int num, int n)
{
	if (n == 0)
	{
		return 1;
	}

	long long tmp;
	tmp = func(num, n / 2);

	if (n % 2 == 1)
	{
		return ((tmp * tmp) % C) * num % C;
	}

	else
	{
		return (tmp * tmp) % C;
	}
}

int main(void)
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> A >> B >> C;
	cout << func(A, B) << "\n";

	return 0;
}