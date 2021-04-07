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

// https://www.acmicpc.net/problem/1717

int n, m;
int parent[1000001];

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

	for (int i = 1; i <= n; i++)
	{
		cout << i << "'s Parent is " << parent[i] << "\n";
	}

	cout << "\n";
}

int main(void)
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> n >> m;

	for (int i = 0; i <= n; i++)
	{
		parent[i] = i;
	}

	for (int i = 0; i < m; i++)
	{
		int cmd, a, b;
		cin >> cmd >> a >> b;

		if (cmd == 0)
		{
			unionParent(a, b);
		}

		else
		{
			if (findParent(a, b))
			{
				cout << "YES\n";
			}

			else
			{
				cout << "NO\n";
			}
		}
	}

	return 0;
}