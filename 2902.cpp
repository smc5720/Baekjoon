#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/2902

int main()
{
	string t;
	cin >> t;

	int size;
	size = t.length();

	for (int i = 0; i < size; i++)
	{
		if (t[i] >= 65 && t[i] <= 90)
		{
			cout << t[i];
		}
	}

	cout << "\n";

	return 0;
}