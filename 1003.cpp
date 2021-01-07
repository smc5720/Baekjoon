#include <iostream>
#include <math.h>
using namespace std;

// https://www.acmicpc.net/problem/1003

int arrayZero[41];
int arrayOne[41];

int main(void)
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	int T, N;

	cin >> T;

	arrayZero[0] = 1;
	arrayOne[0] = 0;

	arrayZero[1] = 0;
	arrayOne[1] = 1;

	for (int i = 2; i <= 40; i++)
	{
		arrayZero[i] = arrayZero[i - 1] + arrayZero[i - 2];
		arrayOne[i] = arrayOne[i - 1] + arrayOne[i - 2];
	}

	for (int i = 0; i < T; i++)
	{
		cin >> N;
		cout << arrayZero[N] << " " << arrayOne[N] << "\n";
	}

	return 0;
}