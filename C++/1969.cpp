#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/1969

int N, M;
char DNA[1000][50];
int minHam;
char typeDNA[4] = {'A', 'C', 'G', 'T'};
char endDNA[50];

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);  

	cin >> N >> M;

	minHam = 0;

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			cin >> DNA[i][j];
		}
	}

	for (int i = 0; i < M; i++)
	{
		int numDNA[4] = { 0, 0, 0, 0 };

		for (int j = 0; j < N; j++)
		{
			if (DNA[j][i] != 'A')
			{
				numDNA[0] += 1;
			}

			if (DNA[j][i] != 'C')
			{
				numDNA[1] += 1;
			}

			if (DNA[j][i] != 'G')
			{
				numDNA[2] += 1;
			}

			if (DNA[j][i] != 'T')
			{
				numDNA[3] += 1;
			}
		}

		char d;
		d = typeDNA[0];

		int min;
		min = numDNA[0];

		for (int m = 1; m < 4; m++)
		{
			if (min > numDNA[m])
			{
				min = numDNA[m];
				d = typeDNA[m];
			}
		}

		minHam += min;
		endDNA[i] = d;
	}

	for (int i = 0; i < M; i++)
	{
		cout << endDNA[i];
	}

	cout << "\n";
	cout << minHam << "\n";

	return 0;
}