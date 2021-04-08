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

// https://www.acmicpc.net/problem/1043

int N, M, K;
int parent[1000001];
vector <int> party[50];

void debugCheck(string msg)
{
	cout << msg << "\n";
}

int getParent(int a)
{
	if (parent[a] == a)
	{
		return a;
	}

	return parent[a] = getParent(parent[a]);
}

void unionParent(int a, int b)
{
	a = getParent(a);
	b = getParent(b);

	if (a < b)
	{
		parent[b] = a;
	}

	else
	{
		parent[a] = b;
	}
}

bool findParent(int a, int b)
{
	a = getParent(a);
	b = getParent(b);

	return a == b;
}

void printParent()
{
	cout << "\n";

	for (int i = 1; i <= N; i++)
	{
		cout << i << "'s Parent is " << getParent(i) << "\n";
	}

	cout << "\n";
}

int main(void)
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> M >> K;

	for (int i = 0; i <= N; i++)
	{
		parent[i] = i;
	}

	for (int i = 0; i < K; i++)
	{
		int c;
		cin >> c;
		parent[c] = 0;
	}

	for (int i = 0; i < M; i++)
	{
		int ma, start;
		cin >> ma;

		for (int j = 0; j < ma; j++)
		{
			int a;
			cin >> a;

			party[i].push_back(a);

			if (j == 0)
			{
				start = a;
				continue;
			}

			unionParent(start, a);
		}
	}

	int ans;
	ans = 0;

	for (int i = 0; i < M; i++)
	{
		int size;
		size = party[i].size();

		for (int j = 0; j < size; j++)
		{
			if (getParent(party[i][j]) == 0)
			{
				break;
			}

			if (j + 1 == size)
			{
				ans += 1;
			}
		}
	}

	cout << ans << "\n";

	return 0;
}