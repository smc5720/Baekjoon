#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/9184

int a, b, c;
int w[21][21][21] = { 0 };

int func(int a, int b, int c)
{
	if (a <= 0 || b <= 0 || c <= 0)
	{
		return 1;
	}

	if (a > 20 || b > 20 || c > 20)
	{
		return func(20, 20, 20);
	}

	if (w[a][b][c] != 0)
	{
		return w[a][b][c];
	}

	if (a < b && b < c)
	{
		w[a][b][c] = func(a, b, c - 1) + func(a, b - 1, c - 1) - func(a, b - 1, c);
	}

	else
	{
		w[a][b][c] = func(a - 1, b, c) + func(a - 1, b - 1, c) + func(a - 1, b, c - 1) - func(a - 1, b - 1, c - 1);
	}

	return w[a][b][c];
}

int main()
{
	cin >> a >> b >> c;

	while (a != -1 || b != -1 || c != -1)
	{
		int num;
		num = func(a, b, c);

		printf("w(%d, %d, %d) = %d\n", a, b, c, num);
		cin >> a >> b >> c;
	}

	return 0;
}