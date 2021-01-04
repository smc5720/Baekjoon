#include <iostream>
#include <math.h>
using namespace std;

// https://www.acmicpc.net/problem/11005

int logB(int x, int base) {
	return log(x) / log(base);
}

int main()
{
	int N, B;

	cin >> N >> B;

	int size;
	size = logB(N, B);

	for (int i = size; i >= 0; i--)
	{
		int k;
		k = pow(B, i);

		int result;
		result = N / k;

		if (result < 10)
		{
			cout << result;
		}

		else
		{
			char l;
			l = result + 55;

			cout << l;
		}

		N -= k * result;
	}

	cout << endl;

	return 0;
}