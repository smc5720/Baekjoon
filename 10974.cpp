#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/10974

int N;
bool visited[9];
int arr[9];

void DFS(int cnt)
{
	if (cnt == 0)
	{
		for (int i = 1; i <= N; i++)
		{
			cout << arr[i] << " ";
		}

		cout << "\n";

		return;
	}

	for (int i = 1; i <= N; i++)
	{
		if (!visited[i])
		{
			arr[N - cnt + 1] = i;
			visited[i] = true;
			DFS(cnt - 1);
			visited[i] = false;
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
		visited[i] = false;
	}

	for (int i = 1; i <= N; i++)
	{
		arr[1] = i;
		visited[i] = true;
		DFS(N - 1);
		visited[i] = false;
	}

	return 0;
}