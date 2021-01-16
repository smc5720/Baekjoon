#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/14916

int N;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	int five, two;
	five = N / 5;

	for (int i = five; i >= 0; i--)
	{
		int result;
		two = (N - i * 5) / 2;
		result = N - i * 5 - two * 2;

		if (result == 0)
		{
			cout << i + two << "\n";
			break;
		}

		else
		{
			if (i == 0)
			{
				cout << -1 << "\n";
			}

			continue;
		}
	}
	
	return 0;
}