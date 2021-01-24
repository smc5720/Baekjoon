#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/5523

int N;
int A, B;
int winA, winB;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	winA = 0;
	winB = 0;

	for (int i = 0; i < N; i++)
	{
		cin >> A >> B;

		if (A > B)
		{
			winA += 1;
		}

		else if (A < B)
		{
			winB += 1;
		}

		else
		{
			continue;
		}
	}

	cout << winA << " " << winB << "\n";

	return 0;
}