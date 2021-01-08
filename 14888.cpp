#include <iostream>
#include <math.h>
using namespace std;

// https://www.acmicpc.net/problem/14888

int A[101];
int N;
int plusOp, minusOp, multiOp, divOp;
int maxSum, minSum;
int sum;

void operation(int idx)
{
	if (idx == N)
	{
		if (maxSum == 99 && minSum == 99)
		{
			maxSum = sum;
			minSum = sum;
		}

		else
		{
			if (maxSum < sum)
			{
				maxSum = sum;
			}

			if (minSum > sum)
			{
				minSum = sum;
			}
		}

		return;
	}

	if (plusOp > 0)
	{
		int k;
		k = sum;

		sum += A[idx];
		plusOp -= 1;

		operation(idx + 1);

		sum = k;
		plusOp += 1;
	}

	if (minusOp > 0)
	{
		int k;
		k = sum;

		sum -= A[idx];
		minusOp -= 1;

		operation(idx + 1);

		sum = k;
		minusOp += 1;
	}

	if (multiOp > 0)
	{
		int k;
		k= sum;

		sum *= A[idx];
		multiOp -= 1;

		operation(idx + 1);

		sum = k;
		multiOp += 1;
	}

	if (divOp > 0)
	{
		int k;
		k = sum;

		sum /= A[idx];
		divOp -= 1;

		operation(idx + 1);

		sum = k;
		divOp += 1;
	}
}

int main(void)
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	maxSum = 99;
	minSum = 99;

	for (int i = 0; i < N; i++)
	{
		cin >> A[i];
	}

	sum = A[0];

	cin >> plusOp >> minusOp >> multiOp >> divOp;

	operation(1);

	cout << maxSum << "\n" << minSum << "\n";

	return 0;
}