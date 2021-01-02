#include <iostream>
using namespace std;

// https://www.acmicpc.net/problem/1550

int main()
{
	string input;

	int length, sum;

	cin >> input;

	length = input.length();
	sum = 0;

	int sq = 1;

	for (int i = length-1; i >= 0; i--)
	{
		int ascii;

		ascii = input[i];

		if (ascii < 58)
		{
			sum += (ascii - 48) * sq;
		}

		else
		{
			sum += (ascii - 55) * sq;
		}

		sq *= 16;
	}

	cout << sum << endl;

	return 0;
}