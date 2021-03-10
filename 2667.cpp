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

// https://www.acmicpc.net/problem/2667

int N;
char field[100][100];
int ans;
vector <int> v;

void DFS(int x, int y)
{
	field[x][y] = '0';
	ans += 1;

	int xPos[4] = { -1, 1, 0, 0 };
	int yPos[4] = { 0, 0, -1, 1 };

	for (int i = 0; i < 4; i++)
	{
		int idX, idY;

		idX = x + xPos[i];
		idY = y + yPos[i];

		if (idX < 0 || idX >= N || idY < 0 || idY >= N)
		{
			continue;
		}

		if (field[idX][idY] == '1')
		{
			DFS(idX, idY);
		}
	}
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int n = 0; n < N; n++)
	{
		for (int i = 0; i < N; i++)
		{
			cin >> field[n][i];
		}
	}

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			if (field[i][j] == '1')
			{
				ans = 0;
				DFS(i, j);
				v.push_back(ans);
			}
		}
	}

	sort(v.begin(), v.end());

	int size;
	size = v.size();

	cout << size << "\n";

	for (int i = 0; i < size; i++)
	{
		cout << v[i] << "\n";
	}

	return 0;
}