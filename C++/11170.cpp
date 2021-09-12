#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>

using namespace std;

// https://www.acmicpc.net/problem/11170

int T, N, M;
int sum[1000001];

void setSum(int num)
{
	int s, idx;
	s = 0;
	idx = num;

	while (num != 0)
	{
		if (num % 10 == 0)
		{
			s += 1;
		}

		num /= 10;
	}

	if (idx >= 1)
	{
		sum[idx] += sum[idx - 1] + s;
	}
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	sum[0] = 1;

	for (int i = 0; i <= 1000000; i++)
	{
		setSum(i);
	}

	cin >> T;

	for (int i = 0; i < T; i++)
	{
		cin >> N >> M;

		if (N > 0)
		{
			cout << sum[M] - sum[N - 1] << "\n";
		}

		else
		{
			cout << sum[M] << "\n";
		}
	}

	return 0;
}