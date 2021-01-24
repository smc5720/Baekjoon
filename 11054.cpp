#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/11054

int N;
int A[1001];
int dpFront[1001];
int dpBack[1001];

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 1; i <= N; i++)
	{
		cin >> A[i];
	}

	for (int i = 1; i <= N; i++)
	{
		dpFront[i] = 1;

		for (int j = 1; j < i; j++)
		{
			if (A[i] > A[j] && dpFront[i] <= dpFront[j])
			{
				dpFront[i] = dpFront[j] + 1;
			}
		}
	}

	for (int i = N; i >= 1; i--)
	{
		dpBack[i] = 1;

		for (int j = N; j > i; j--)
		{
			if (A[i] > A[j] && dpBack[i] <= dpBack[j])
			{
				dpBack[i] = dpBack[j] + 1;
			}
		}
	}

	int maxVal;
	maxVal = 0;

	for (int i = 1; i <= N; i++)
	{
		int num;
		num = dpFront[i] + dpBack[i];

		if (maxVal < num)
		{
			maxVal = num;
		}
	}

	cout << maxVal - 1 << "\n";

	return 0;
}