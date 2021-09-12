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

// https://www.acmicpc.net/problem/7576

int N, M;
int tomato[1000][1000];
queue <pair <int, int>> q;

int main(void)
{
	cin >> M >> N;

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			cin >> tomato[i][j];
		}
	}

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			if (tomato[i][j] == 1)
			{
				pair <int, int> p;
				p.first = i;
				p.second = j;
				q.push(p);
			}
		}
	}

	int ans;
	ans = -1;

	while (!q.empty())
	{
		int size;
		size = q.size();
		ans += 1;

		for (int s = 0; s < size; s++)
		{
			pair <int, int> tp;
			tp = q.front();
			q.pop();

			int xPos[4] = { -1, 1, 0, 0 };
			int yPos[4] = { 0, 0, -1, 1 };

			for (int i = 0; i < 4; i++)
			{
				int x, y;
				x = tp.first + xPos[i];
				y = tp.second + yPos[i];

				if (x >= N || x < 0 || y >= M || y < 0)
				{
					continue;
				}

				if (tomato[x][y] == 0)
				{
					tomato[x][y] = 1;
					q.push(make_pair(x, y));
				}
			}
		}
	}

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			if (tomato[i][j] == 0)
			{
				cout << "-1\n";
				exit(0);
			}
		}
	}

	cout << ans << "\n";

	return 0;
}