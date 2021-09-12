#include <iostream>
#include <math.h>
using namespace std;

// https://www.acmicpc.net/problem/1009

int main()
{
	int times;

	cin >> times; 

	for (int i = 0; i < times; i++)
	{
		int a, b;

		cin >> a >> b;

		b = b % 4 + 4;
		// 같은 수를 곱하면 최대 4주기 반복이 일어난다.

		int num;
		num = a;

		for (int j = 2; j <= b; j++)
		{
			num *= a;
			num %= 10;
		}

		if (num == 0)
		{
			num = 10;
		}

		cout << num << endl;
	}

	return 0;
}