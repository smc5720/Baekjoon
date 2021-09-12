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

// https://www.acmicpc.net/problem/7569

struct Pos {
	int h;
	int n;
	int m;
};

int M, N, H;
int tomato[100][100][100];
queue <Pos> q;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> M >> N >> H;

	for (int h = 0; h < H; h++)
	{
		for (int n = 0; n < N; n++)
		{
			for (int m = 0; m < M; m++)
			{
				cin >> tomato[h][n][m];

				if (tomato[h][n][m] == 1)
				{
					Pos tmp;
					tmp.h = h;
					tmp.n = n;
					tmp.m = m;
					q.push(tmp);
				}
			}
		}
	}

	int day, queSize, queCnt;
	day = -1;
	queSize = q.size();
	queCnt = 0;

	while (!q.empty())
	{
		Pos tmp;
		tmp = q.front();

		int hPos[6] = { -1, 1, 0, 0, 0, 0 };
		int nPos[6] = { 0, 0, -1, 1, 0, 0 };
		int mPos[6] = { 0, 0, 0, 0, -1, 1 };

		for (int i = 0; i < 6; i++)
		{
			int hIndex;
			int nIndex;
			int mIndex;

			hIndex = tmp.h + hPos[i];
			nIndex = tmp.n + nPos[i];
			mIndex = tmp.m + mPos[i];

			if (hIndex < 0 || hIndex >= H)
			{
				continue;
			}

			if (nIndex < 0 || nIndex >= N)
			{
				continue;
			}

			if (mIndex < 0 || mIndex >= M)
			{
				continue;
			}

			if (tomato[hIndex][nIndex][mIndex] == 0)
			{
				tomato[hIndex][nIndex][mIndex] = 1;

				Pos t;
				t.h = hIndex;
				t.n = nIndex;
				t.m = mIndex;

				q.push(t);
			}
		}

		q.pop();
		queCnt += 1;

		if (queCnt == queSize)
		{
			day += 1;
			queCnt = 0;
			queSize = q.size();
		}
	}

	for (int h = 0; h < H; h++)
	{
		for (int n = 0; n < N; n++)
		{
			for (int m = 0; m < M; m++)
			{
				if (tomato[h][n][m] == 0)
				{
					cout << -1 << "\n";
					exit(0);
				}
			}
		}
	}

	cout << day << "\n";

	return 0;
}