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

// https://www.acmicpc.net/problem/14500

int N, M;
int field[500][500];
int maxVal;
bool visited[500][500];

void printField()
{
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			if (visited[i][j])
			{
				cout << "- ";
			}

			else
			{
				cout << field[i][j] << " ";
			}
		}

		cout << "\n";
	}

	cout << "\n";
}

void DFS(int sum, int depth, int x, int y)
{
	sum += field[x][y];

	if (depth == 1)
	{
		maxVal = max(sum, maxVal);
		// printField();
		return;
	}

	int xPos[4] = { 1, -1, 0, 0 };
	int yPos[4] = { 0, 0, 1, -1 };

	for (int i = 0; i < 4; i++)
	{
		int a, b;
		a = x + xPos[i];
		b = y + yPos[i];

		if (a < 0 || b < 0 || a >= N || b >= M || visited[a][b])
		{
			continue;
		}

		visited[a][b] = true;
		DFS(sum, depth - 1, a, b);
		visited[a][b] = false;
	}
}

void shape(int x, int y)
{
	int sum;
	sum = 0;

	// で
	int xPos1[4] = {0, 0, 0, 1};
	int yPos1[4] = {0, -1, 1, 0};

	for (int i = 0; i < 4; i++)
	{
		int a, b;
		a = x + xPos1[i];
		b = y + yPos1[i];

		if (a < 0 || b < 0 || a >= N || b >= M)
		{
			break;
		}

		sum += field[a][b];

		if (i == 3)
		{
			maxVal = max(sum, maxVal);
			// printField();
		}
	}
	sum = 0;

	// ぬ
	int xPos2[4] = { 0, 0, 0, -1 };
	int yPos2[4] = { 0, -1, 1, 0 };

	for (int i = 0; i < 4; i++)
	{
		int a, b;
		a = x + xPos2[i];
		b = y + yPos2[i];

		if (a < 0 || b < 0 || a >= N || b >= M)
		{
			break;
		}

		sum += field[a][b];

		if (i == 3)
		{
			maxVal = max(sum, maxVal);
			// printField();
		}
	}

	sum = 0;

	// っ
	int xPos3[4] = { 0, 0, 1, -1 };
	int yPos3[4] = { 0, -1, 0, 0 };

	for (int i = 0; i < 4; i++)
	{
		int a, b;
		a = x + xPos3[i];
		b = y + yPos3[i];

		if (a < 0 || b < 0 || a >= N || b >= M)
		{
			break;
		}

		sum += field[a][b];

		if (i == 3)
		{
			maxVal = max(sum, maxVal);
			// printField();
		}
	}

	sum = 0;

	// た
	int xPos4[4] = { 0, 0, 1, -1 };
	int yPos4[4] = { 0, 1, 0, 0 };

	for (int i = 0; i < 4; i++)
	{
		int a, b;
		a = x + xPos4[i];
		b = y + yPos4[i];

		if (a < 0 || b < 0 || a >= N || b >= M)
		{
			break;
		}

		sum += field[a][b];

		if (i == 3)
		{
			maxVal = max(sum, maxVal);
			// printField();
		}
	}
}

int main(void)
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> M;

	maxVal = 0;

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			cin >> field[i][j];
			visited[i][j] = false;
		}
	}

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			visited[i][j] = true;
			DFS(0, 4, i, j);
			visited[i][j] = false;
			shape(i, j);
		}
	}

	cout << maxVal << "\n";

	return 0;
}