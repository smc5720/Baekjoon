#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/7568

int N;
int tall[200];
int weight[200];
int score[200];

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 0; i < N; i++)
	{
		cin >> weight[i] >> tall[i];
		score[i] = 1;
	}

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			if (i == j)
			{
				continue;
			}

			if (weight[i] < weight[j] && tall[i] < tall[j])
			{
				score[i] += 1;
			}
		}
	}

	for (int i = 0; i < N; i++)
	{
		cout << score[i] << " ";
	}

	cout << "\n";

	return 0;
}