#include <iostream>
#include <math.h>
using namespace std;

// https://www.acmicpc.net/problem/5086

int main()
{
	int num1, num2;

	cin >> num1 >> num2;

	while (num1 != 0 && num2 != 0)
	{
		if (num1 > num2)
		{
			if (num1 % num2 == 0)
			{
				cout << "multiple" << endl;
			}

			else
			{
				cout << "neither" << endl;
			}
		}

		else
		{
			if (num2 % num1 == 0)
			{
				cout << "factor" << endl;
			}

			else
			{
				cout << "neither" << endl;
			}
		}

		cin >> num1 >> num2;
	}

	return 0;
}