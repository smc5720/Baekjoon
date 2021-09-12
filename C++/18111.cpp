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

// https://www.acmicpc.net/problem/18111

int N, M, B;
int h[500][500];
int maxH, minH;

int sumLand()
{
	int cnt;
	cnt = 0;

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			if (maxH > h[i][j])
			{
				cnt += maxH - h[i][j];
			}
		}
	}

	return cnt;
}

int minusLand()
{
	int cnt;
	cnt = 0;

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			if (maxH < h[i][j])
			{
				cnt += h[i][j] - maxH;
			}
		}
	}

	return cnt;
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> M >> B;

	maxH = 0;
	minH = 256;

	int ansTime, ansHeight;
	ansTime = 1000000000;
	ansHeight = 0;

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			cin >> h[i][j];

			if (maxH < h[i][j])
			{
				maxH = h[i][j];
			}

			if (minH > h[i][j])
			{
				minH = h[i][j];
			}
		}
	}

	while (maxH >= minH)
	{
		int block, need;
		block = minusLand();
		need = sumLand();

		if (need <= block + B)
		{
			int timer;
			timer = need + block * 2;

			if (ansTime > timer)
			{
				ansTime = timer;
				ansHeight = maxH;
			}
		}

		maxH -= 1;
	}

	cout << ansTime << " " << ansHeight << "\n";

	return 0;
}