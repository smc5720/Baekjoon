#include <iostream>
#include <math.h>
using namespace std;

// https://www.acmicpc.net/problem/13458

int N;
int A[1000000];
int B, C;
long long sum;
  
int main(void)
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 0; i < N; i++)
	{
		cin >> A[i];
	}

	cin >> B >> C;

	sum = N;

	for (int i = 0; i < N; i++)
	{
		int k;

		k = A[i] - B;

		if (k > 0)
		{
			int n;
			n = (k - 1) / C + 1;

			sum += n;
		}
	}

	printf("%lld\n", sum);

	return 0;
}