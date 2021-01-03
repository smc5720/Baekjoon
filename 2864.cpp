#include <iostream>
#include <math.h>
using namespace std;

// https://www.acmicpc.net/problem/2864

int main()
{
	int A, B;

	cin >> A >> B;

	int maxA, minA, maxB, minB;

	maxA = A;
	minA = A;
	maxB = B;
	minB = B;

	int countA, countB;

	countA = log10(A);
	countB = log10(B);

	for (int i = countA; i >= 0; i--)
	{
		int num;
		num = pow(10, i);

		int k;
		k = A / num;

		if (k == 5)
		{
			maxA -= k * num;
			maxA += 6 * num;
		}

		if (k == 6)
		{
			minA -= k * num;
			minA += 5 * num;
		}

		A %= num;
	}

	for (int i = countB; i >= 0; i--)
	{
		int num;
		num = pow(10, i);

		int k;
		k = B / num;

		if (k == 5)
		{
			maxB -= k * num;
			maxB += 6 * num;
		}

		if (k == 6)
		{
			minB -= k * num;
			minB += 5 * num;
		}

		B %= num;
	}

	cout << minA + minB << " " << maxA + maxB << endl;

	return 0;
}