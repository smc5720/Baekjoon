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

// https://www.acmicpc.net/problem/1697

int N, K;
bool visited[100001];
queue <int> q;

void BFS(int cnt)
{
	int size;
	size = q.size();

	for (int i = 0; i < size; i++)
	{
		int n;
		n = q.front();

		if (n < 0 || n > 100000)
		{
			q.pop();
			continue;
		}

		visited[n] = true;

		if (n == K)
		{
			cout << cnt << "\n";
			return;
		}

		if (n - 1 >= 0 && !visited[n - 1])
		{
			q.push(n - 1);
		}

		if (n + 1 <= 100000 && !visited[n + 1])
		{
			q.push(n + 1);
		}

		if (n * 2 <= 100000 && !visited[n * 2])
		{
			q.push(n * 2);
		}

		q.pop();
	}

	BFS(cnt + 1);
}

int main(void)
{
	for (int i = 0; i <= 100000; i++)
	{
		visited[i] = false;
	}

	cin >> N >> K;

	q.push(N);

	BFS(0);

	return 0;
}