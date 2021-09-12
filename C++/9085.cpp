#include <iostream>
#include <math.h>
using namespace std;

// https://www.acmicpc.net/problem/9085

int main()
{
	int times;

	cin >> times;

	for (int i = 0; i < times; i++)
	{
		int N;

		cin >> N;

		int result=0;

		for (int j = 0; j < N; j++)
		{
			int input;

			cin >> input;

			result += input;
		}

		cout << result << endl;
	}

	return 0;
}