#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>

using namespace std;

// https://www.acmicpc.net/problem/1026

int A[50];
int B[50];

int N;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 0; i < N; i++)
	{
		cin >> A[i];
	}

	for (int i = 0; i < N; i++)
	{
		cin >> B[i];
	}

	sort(A, A + N);
	sort(B, B + N);

	int sum;
	sum = 0;

	for (int i = 0; i < N; i++)
	{
		sum += A[i] * B[N - i - 1];
	}

	cout << sum << "\n";

	return 0;
}