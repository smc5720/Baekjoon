#include <iostream>
#include <math.h>
using namespace std;

// https://www.acmicpc.net/problem/14681

int main()
{
	int x, y;

	cin >> x >> y;

	if (x > 0)
	{
		if (y > 0)
		{
			cout << "1" << endl;
		}

		else
		{
			cout << "4" << endl;
		}
	}

	else
	{
		if (y > 0)
		{
			cout << "2" << endl;
		}

		else
		{
			cout << "3" << endl;
		}
	}

	return 0;
}