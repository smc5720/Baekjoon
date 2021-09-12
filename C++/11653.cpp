#include <iostream>
#include <math.h>
using namespace std;

// https://www.acmicpc.net/problem/11653

int main(void)
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	int num;

	cin >> num;

	int n;
	n = 2;

	while (num != 1)
	{
		if (num % n == 0)
		{
			cout << n << "\n";
			num /= n;
		}

		else
		{
			n += 1;
		}
	}

	return 0;
}