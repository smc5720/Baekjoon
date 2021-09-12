#include <iostream>
#include <math.h>
using namespace std;

// https://www.acmicpc.net/problem/2846
  
int N;
int road[1000];
int maxHeight;

int main(void)
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 0; i < N; i++)
	{
		cin >> road[i];
	}

	maxHeight = 0;

	int lowIdx;

	lowIdx = 0;

	for (int i = 1; i < N; i++)
	{
		if (road[i] <= road[i-1])
		{
			int val;
			val = road[i - 1] - road[lowIdx];

			if (val > maxHeight)
			{
				maxHeight = val;
			}

			lowIdx = i;
		}

		if (i + 1 == N)
		{
			if (road[lowIdx] < road[i])
			{
				int val;
				val = road[i] - road[lowIdx];

				if (val > maxHeight)
				{
					maxHeight = val;
				}
			}
		}
	}

	printf("%d", maxHeight);

	return 0;
}