#include <iostream>
#include <math.h>
using namespace std;

// https://www.acmicpc.net/problem/9663

bool map[14][14];
int N, result;

bool isVaild(int i, int cnt)
{
	int x, y;

	for (x = 0; x < cnt; x++)
	{
		if (map[i][x])
		{
			return false;
		}
	}

	for (x = cnt - 1, y = i - 1; y >= 0; x--, y--)
	{
		if (map[y][x])
		{
			return false;
		}
	}

	for (x = cnt - 1, y = i + 1; y < N; x--, y++)
	{
		if (map[y][x])
		{
			return false;
		}
	}

	return true;
}

void DFS(int cnt)
{
	if (cnt == N)
	{
		result += 1;
		return;
	}

	for (int i = 0; i < N; i++)
	{
		if (map[i][cnt] == false && isVaild(i, cnt))
		{
			map[i][cnt] = true;
			DFS(cnt + 1);
			map[i][cnt] = false;
		}
	}
}

int main(void)
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	result = 0;

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			map[i][j] = false;
		}
	}

	DFS(0);

	cout << result << "\n";

	return 0;
}