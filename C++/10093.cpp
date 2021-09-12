#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
using namespace std;

// https://www.acmicpc.net/problem/10093

long long A, B;
int dif;

int main(void)
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> A >> B;

	if (A > B)
	{
		dif = A - B - 1;
		cout << dif << "\n";

		for (int i = 1; i <= dif; i++)
		{
			cout << B + i << " ";
		}

		cout << "\n";
	}

	else if(A < B)
	{
		dif = B - A - 1;
		cout << dif << "\n";

		for (int i = 1; i <= dif; i++)
		{
			cout << A + i << " ";
		}

		cout << "\n";
	}

	else
	{
		cout << 0 << "\n";
	}

	return 0;
}