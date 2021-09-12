#include <iostream>
using namespace std;

// https://www.acmicpc.net/problem/20410

int main()
{
	int m;
	int seed, x1, x2;

	cin >> m >> seed >> x1 >> x2;

	int a, c;

	bool breaker;

	breaker = false;

	for (int i = 0; i < m; i++)
	{
		for (int j = 0; j < m; j++)
		{
			if ((i * seed + j) % m == x1 && (i * x1 + j) % m == x2)
			{
				a = i;
				c = j;
				breaker = true;
			}

			if (breaker == true)
			{
				break;
			}
		}

		if (breaker == true)
		{
			break;
		}
	}

	cout << a << " " << c << endl;

	return 0;
}