#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>

using namespace std;

// https://www.acmicpc.net/problem/13301

long long arr[81];

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	int n;
	cin >> n;

	arr[1] = 1;
	arr[2] = 1;

	for (int i = 3; i <= n; i++)
	{
		arr[i] = arr[i - 1] + arr[i - 2];
	}

	long long sum;
	sum = arr[n] * 4 + arr[n - 1] * 2;

	cout << sum << "\n";

	return 0;
}