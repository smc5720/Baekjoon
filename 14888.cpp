#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/14888

int N;
int numArray[11];
int plusOp, minusOp, multiOp, divOp;
int maxVal, minVal;

void DFS(int depth, int sum, int idx)
{
	if (depth == 0)
	{
		if (sum < minVal)
		{
			minVal = sum;
		}

		if (sum > maxVal)
		{
			maxVal = sum;
		}

		return;
	}

	else
	{
		if (plusOp > 0)
		{
			int k;
			k = sum;
			plusOp -= 1;
			sum += numArray[idx];
			DFS(depth - 1, sum, idx + 1);
			plusOp += 1;
			sum = k;
		}

		if (minusOp > 0)
		{
			int k;
			k = sum;
			minusOp -= 1;
			sum -= numArray[idx];
			DFS(depth - 1, sum, idx + 1);
			minusOp += 1;
			sum = k;
		}

		if (multiOp > 0)
		{
			int k;
			k = sum;
			multiOp -= 1;
			sum *= numArray[idx];
			DFS(depth - 1, sum, idx + 1);
			multiOp += 1;
			sum = k;
		}

		if (divOp > 0)
		{
			int k;
			k = sum;
			divOp -= 1;
			sum /= numArray[idx];
			DFS(depth - 1, sum, idx + 1);
			divOp += 1;
			sum = k;
		}
	}
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	minVal = 1000000000;
	maxVal = -1000000000;

	cin >> N;

	for (int i = 0; i < N; i++)
	{
		cin >> numArray[i];
	}

	cin >> plusOp >> minusOp >> multiOp >> divOp;

	DFS(N - 1, numArray[0], 1);

	cout << maxVal << " " << minVal << "\n";

	return 0;
}