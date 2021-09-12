#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>
#include <stack>
#include <set>

using namespace std;

// https://www.acmicpc.net/problem/11047

int N, K;
int A[10];

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> K;

	int start, cnt;
	start = 0;
	cnt = 0;

	for (int i = 0; i < N; i++)
	{
		cin >> A[i];

		if (A[i] <= K)
		{
			start = i;
		}
	}

	for (int i = start; i >= 0; i--)
	{
		cnt += K / A[i];
		K %= A[i];
	}

	cout << cnt << "\n";

	return 0;
}