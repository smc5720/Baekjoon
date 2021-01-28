#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>

using namespace std;

// https://www.acmicpc.net/problem/10819

int N;
int A[9];
int maxVal;
bool isVisited[9];
int sumVal;

void DFS(int n, int cnt)
{
	if (cnt == 1)
	{
		if (maxVal < sumVal)
		{
			maxVal = sumVal;
		}

		return;
	}

	cnt -= 1;

	for (int i = 1; i <= N; i++)
	{
		if (!isVisited[i])
		{
			int num;
			num = abs(n - A[i]);

			isVisited[i] = true;
			sumVal += num;

			DFS(A[i], cnt);

			sumVal -= num;
			isVisited[i] = false;
		}
	}
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 1; i <= N; i++)
	{
		cin >> A[i];
		isVisited[i] = false;
	}

	maxVal = 0;

	for (int i = 1; i <= N; i++)
	{
		sumVal = 0;
		isVisited[i] = true;
		DFS(A[i], N);
		isVisited[i] = false;
	}

	cout << maxVal << "\n";

	return 0;
}