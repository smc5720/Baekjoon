#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/15624

int N;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	int div;
	div = 1000000007;

	int n1, n2, sum;

	sum = 0;
	n1 = 0;
	n2 = 1;

	if (N == 0)
	{
		sum = n1;
	}

	if (N == 1)
	{
		sum = n2;
	}

	for (int i = 2; i <= N; i++)
	{
		sum = (n1 + n2) % div;
		n1 = n2;
		n2 = sum;
	}

	cout << sum << "\n";

	return 0;
}