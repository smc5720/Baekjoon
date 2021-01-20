#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/9095

int T, n;
int numArray[11];

int main()
{
	cin >> T;

	numArray[1] = 1;
	numArray[2] = 2;
	numArray[3] = 4;

	for (int i = 4; i <= 10; i++)
	{
		numArray[i] = numArray[i - 1] + numArray[i - 2] + numArray[i - 3];
	}

	for (int i = 0; i < T; i++)
	{
		cin >> n;
		cout << numArray[n] << "\n";
	}

	return 0;
}