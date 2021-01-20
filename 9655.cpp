#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/9655

int N;

int main()
{
	cin >> N;

	if (N % 2 == 1)
	{
		cout << "SK\n";
	}

	else
	{
		cout << "CY\n";
	}

	return 0;
}