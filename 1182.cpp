#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;  

// https://www.acmicpc.net/problem/1182

int N, S;
int numArray[20];
int result;

void DFS(int idx, int cnt, int sum)
{
	if (cnt == 0)
	{
		if (sum == S)
		{
			result += 1;
		}

		return;
	}

	else
	{
		for (int i = idx; i < N; i++)
		{
			sum += numArray[i];
			DFS(i + 1, cnt - 1, sum);
			sum -= numArray[i];
		}
	}
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> S;

	result = 0;

	for (int i = 0; i < N; i++)
	{
		cin >> numArray[i];
	}

	for (int i = 1; i <= N; i++)
	{
		int sum;
		sum = 0;

		DFS(0, i, sum);
	}

	cout << result << "\n";

	return 0;
}