#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>

using namespace std;

// https://www.acmicpc.net/problem/14697

int N;
int A, B, C;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> A >> B >> C >> N;

	int a;
	a = N / A;

	for (int i = a; i >= 0; i--)
	{
		int b;
		b = (N - (i * A)) / B;

		for (int j = b; j >= 0; j--)
		{
			int c;
			c = (N - (i * A) - (j * B)) % C;

			if (c == 0)
			{
				cout << 1 << "\n";
				exit(0);
			}
		}
	}

	cout << 0 << "\n";

	return 0;
}