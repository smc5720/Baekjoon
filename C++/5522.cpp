#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/5522

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	int sum;
	sum = 0;

	for (int i = 0; i < 5; i++)
	{
		int num;
		cin >> num;

		sum += num;
	}

	cout << sum << "\n";

	return 0;
}