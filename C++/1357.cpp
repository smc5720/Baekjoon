#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <cstring>

using namespace std;

// https://www.acmicpc.net/problem/1357

int X, Y;
int revX, revY;

int reverse(int size, int num)
{
	int rev;
	rev = 0;

	for (int i = size; i >= 0; i--)
	{
		int n;
		n = num / pow(10, i);
		num -= n * pow(10, i);
		rev += n * pow(10, size - i);
	}

	return rev;
}

int main(void)
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> X >> Y;

	int sizeX, sizeY;

	sizeX = log10(X);
	sizeY = log10(Y);

	revX = 0;
	revY = 0;

	revX = reverse(sizeX, X);
	revY = reverse(sizeY, Y);

	int sum, sizeSum;

	sum = revX + revY;
	sizeSum = log10(sum);

	int revSum;

	revSum = reverse(sizeSum, sum);

	cout << revSum << "\n";

	return 0;
}