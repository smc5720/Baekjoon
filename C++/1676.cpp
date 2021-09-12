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

// https://www.acmicpc.net/problem/1676

int N, two, five;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	two = 0;
	five = 0;

	cin >> N;

	for (int i = 2; i <= N; i *= 2)
	{
		two += N / i;
	}
	
	for (int i = 5; i <= N; i *= 5)
	{
		five += N / i;
	}

	if (two > five)
	{
		cout << five << "\n";
	}

	else
	{
		cout << two << "\n";
	}

	return 0;
}