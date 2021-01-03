#include <iostream>
#include <math.h>
using namespace std;

// https://www.acmicpc.net/problem/2490

int main()
{
	int result;

	for (int i = 0; i < 3; i++)
	{
		result = 0;
	
		for (int j = 0; j < 4; j++)
		{
			int input;

			cin >> input;

			result += input;
		}

		if (result == 0)
		{
			cout << "D" << endl;
		}

		if (result == 1)
		{
			cout << "C" << endl;
		}

		if (result == 2)
		{
			cout << "B" << endl;
		}

		if (result == 3)
		{
			cout << "A" << endl;
		}

		if (result == 4)
		{
			cout << "E" << endl;
		}
	}

	return 0;
}