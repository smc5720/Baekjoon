#include <iostream>
#include <math.h>
using namespace std;

// https://www.acmicpc.net/problem/10757

int main()
{
	string a, b;

	cin >> a >> b;

	bool upTen;
	upTen = false;

	int sizeA, sizeB;

	sizeA = a.size();
	sizeB = b.size();

	int n, countB;

	if (sizeA <= sizeB)
	{
		n = sizeA;
		countB = sizeB - 1;
	}

	else
	{
		n = sizeB;
		countB = sizeA - 1;
	}

	int max;
	max = countB;

	char* sResult = new char[countB + 2];

	for (int i = 0; i < countB + 2; i++)
	{
		sResult[i] = 47;
	}

	if (sizeA <= sizeB)
	{
		for (int i = n - 1; i >= 0; i--)
		{
			if (upTen == true)
			{
				sResult[countB + 1] = a[i] + b[countB] - 47;
				upTen = false;
			}

			else
			{
				sResult[countB + 1] = a[i] + b[countB] - 48;
			}

			if (sResult[countB + 1] > 57)
			{
				upTen = true;
				sResult[countB + 1] -= 10;

				if (i == 0 && sizeA == sizeB)
				{
					sResult[i] = 49;
				}
			}

			countB -= 1;
		}

		for (int i = countB; i >= 0; i--)
		{
			if (upTen == true)
			{
				sResult[i + 1] = b[i] + 1;
				upTen = false;
			}

			else {
				sResult[i + 1] = b[i];
			}

			if (sResult[i + 1] > 57)
			{
				upTen = true;
				sResult[i + 1] -= 10;

				if (i == 0)
				{
					sResult[i] = 49;
				}
			}
		}
	}

	else
	{
		for (int i = n - 1; i >= 0; i--)
		{
			if (upTen == true)
			{
				sResult[countB + 1] = b[i] + a[countB] - 47;
				upTen = false;
			}

			else
			{
				sResult[countB + 1] = b[i] + a[countB] - 48;
			}

			if (sResult[countB + 1] > 57)
			{
				upTen = true;
				sResult[countB + 1] -= 10;

				if (i == 0 && sizeA == sizeB)
				{
					sResult[i] = 49;
				}
			}

			countB -= 1;
		}

		for (int i = countB; i >= 0; i--)
		{
			if (upTen == true)
			{
				sResult[i + 1] = a[i] + 1;
				upTen = false;
			}

			else {
				sResult[i + 1] = a[i];
			}

			if (sResult[i + 1] > 57)
			{
				upTen = true;
				sResult[i + 1] -= 10;

				if (i == 0)
				{
					sResult[i] = 49;
				}
			}
		}
	}

	if (sResult[0] <= 47 && sResult[1] == 48)
	{
		sResult[1] = 47;
	}

	for (int i = 0; i < max + 2; i++)
	{
		if (sResult[i] >= 48 && sResult[i] <= 57)
		{
			cout << sResult[i];
		}
	}

	cout << endl;

	return 0;
}