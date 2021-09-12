#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/15657

int N, M;
int numArray[8];
int result[8];

void DFS(int cnt,int idx)
{
	if (cnt == M)
	{
		for (int i = 0; i < M; i++)
		{
			cout << result[i] << " ";
		}

		cout << "\n";

		return;
	}

	else
	{
		for (int i = idx; i < N; i++)
		{
			result[cnt] = numArray[i];
			DFS(cnt + 1, i);
		}
	}
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> M;

	for (int i = 0; i < N; i++)
	{
		cin >> numArray[i];
	}

	sort(numArray, numArray + N);

	DFS(0, 0);

	return 0;
}