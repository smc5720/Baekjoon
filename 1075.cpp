#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>

using namespace std;

// https://www.acmicpc.net/problem/1075

int N, F;
 
int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> F;
 
	N -= N % 100;

	int a;
	a = N / F;

	int div;

	if (N % F == 0)
	{
		div = 0;
	}

	else
	{
		div = (a * F + F) % 100;
	}

	if (div < 10)
	{
		cout << 0;
	}

	cout << div << "\n";

	return 0;
}