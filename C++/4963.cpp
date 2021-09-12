#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>

using namespace std;

// https://www.acmicpc.net/problem/1010

int w, h;
int land[52][52];
bool visited[52][52];
int ans;
bool isCheck;

void initMem()
{
	for (int i = 0; i <= 51; i++)
	{
		for (int j = 0; j <= 51; j++)
		{
			land[i][j] = 0;
			visited[i][j] = false;
		}
	}

	ans = 0;
}

void DFS(int h, int w)
{
	if (land[h][w] == 1 && visited[h][w] == false)
	{
		if (!isCheck)
		{
			isCheck = true;
			ans += 1;
		}

		visited[h][w] = true;

		DFS(h - 1, w); // ÁÂ
		DFS(h + 1, w); // ¿ì
		DFS(h, w - 1); // »ó
		DFS(h, w + 1); // ÇÏ
		DFS(h - 1, w - 1); // ÁÂ»ó
		DFS(h + 1, w - 1); // ¿ì»ó
		DFS(h - 1, w + 1); // ÁÂÇÏ
		DFS(h + 1, w + 1); // ¿ìÇÏ
	}

	return;
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> w >> h;

	while (w != 0 || h != 0)
	{
		initMem();

		for (int i = 1; i <= h; i++)
		{
			for (int j = 1; j <= w; j++)
			{
				cin >> land[i][j];
			}
		}

		for (int i = 1; i <= h; i++)
		{
			for (int j = 1; j <= w; j++)
			{
				isCheck = false;
				DFS(i, j);
			}
		}

		cout << ans << "\n";

		cin >> w >> h;
	}

	return 0;
}