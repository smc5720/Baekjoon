#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/1057

int N;
int A, B;
 
int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> A >> B;

	int round;
	round = 1;

	while ((A - 1) / 2 != (B - 1) / 2)
	{
		round += 1;

		if (A % 2 == 0)
		{
			A = A / 2;
		}

		else
		{
			A = (A + 1) / 2;
		}

		if (B % 2 == 0)
		{
			B = B / 2;
		}

		else
		{
			B = (B + 1) / 2;
		}
	}

	cout << round << "\n";

	return 0;
}