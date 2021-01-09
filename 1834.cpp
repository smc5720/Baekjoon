#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <cstring>

using namespace std;

// https://www.acmicpc.net/problem/1834

long long N;

int main(void)
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	long long sum;
	sum = 0;

	for (int i = 1; i < N; i++)
	{
		sum += N * i + i;
	}

	cout << sum << "\n";

	return 0;
}