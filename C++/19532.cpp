#include <iostream>
using namespace std;

// https://www.acmicpc.net/problem/19532

int main()
{
	int a1, a2;
	int b1, b2;
	int sol1, sol2;

	cin >> a1 >> b1 >> sol1;
	cin >> a2 >> b2 >> sol2;

	int x, y;

	bool breaker;
	
	breaker = false;

	for (int i = -999; i < 1000; i++)
	{
		for (int j = -999; j < 1000; j++)
		{
			if (a1 * i + b1 * j == sol1 && a2 * i + b2 * j == sol2)
			{
				x = i;
				y = j;

				breaker = true;
			}

			if (breaker == true) {
				break;
			}
		}

		if (breaker == true) {
			break;
		}
	}

	cout << x << " " << y << endl;

	return 0;
}