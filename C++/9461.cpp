#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/9461

int T;
long long numArray[100];

int main()
{
	cin >> T;

	numArray[0] = 1;
	numArray[1] = 1;
	numArray[2] = 1;
	numArray[3] = 2;
	numArray[4] = 2;
	numArray[5] = 3;
	numArray[6] = 4;
	numArray[7] = 5;

	for (int i = 8; i < 100; i++)
	{
		numArray[i] = numArray[i - 1] + numArray[i - 5];
	}

	for (int i = 0; i < T; i++)
	{
		int num;
		cin >> num;

		cout << numArray[num - 1] << "\n";
	}

	return 0;
}