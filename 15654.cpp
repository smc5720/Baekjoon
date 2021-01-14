#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/15654

int N, M;
int numArray[8];
bool visited[8];
int result[8];

void DFS(int cnt)
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
		for (int i = 0; i < N; i++)
		{
			if (!visited[i])
			{
				visited[i] = true;
				result[cnt] = numArray[i];
				DFS(cnt + 1);
				visited[i] = false;
			}
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
		visited[i] = false;
	}

	sort(numArray, numArray + N);

	DFS(0);

	return 0;
}