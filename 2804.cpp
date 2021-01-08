#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <cstring>

using namespace std;

// https://www.acmicpc.net/problem/2804

char A[31];
char B[31];

int main(void)
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> A >> B;

	int sizeA, sizeB;

	sizeA = (int)strlen(A);
	sizeB = (int)strlen(B);

	bool state;
	state = true;

	int idxA, idxB;

	idxA = 0;
	idxB = 0;

	for (int i = 0; i < sizeA; i++)
	{
		if (state)
		{
			for (int j = 0; j < sizeB; j++)
			{
				if (A[i] == B[j])
				{
					state = false;

					idxA = i;
					idxB = j;

					break;
				}
			}
		}

		else
		{
			break;
		}
	}

	for (int i = 0; i < sizeB; i++)
	{
		for (int j = 0; j < sizeA; j++)
		{
			if (i == idxB)
			{
				cout << A[j];
			}

			else if (j == idxA)
			{
				cout << B[i];
			}

			else
			{
				cout << ".";
			}
		}

		cout << "\n";
	}

	return 0;
}