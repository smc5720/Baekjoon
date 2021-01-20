#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/11726

int n;
int numArray[1001];

#define DIV 10007;

int main()
{
	cin >> n;

	numArray[1] = 1;
	numArray[2] = 2;

	for (int i = 3; i <= n; i++)
	{
		numArray[i] = numArray[i - 1] + numArray[i - 2];
		numArray[i] %= DIV;
	}

	cout << numArray[n] << "\n";

	return 0;
}