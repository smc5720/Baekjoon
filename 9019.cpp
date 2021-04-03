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

// https://www.acmicpc.net/problem/9019

int T;
queue <pair <int, string>> q;
int A, B;
bool visited[10000];

int D(int n)
{
	return (n * 2) % 10000;
}

int S(int n)
{
	if (n == 0)
	{
		return 9999;
	}

	return n - 1;
}

int L(int n)
{
	int tmp;
	tmp = n / 1000;
	return (n * 10) % 10000 + tmp;
}

int R(int n)
{
	int tmp;
	tmp = n % 10;
	return (n / 10) + tmp * 1000;
}

void pushQueue(int num, string s)
{
	int tmp;
	tmp = D(num);

	if (!visited[tmp])
	{
		q.push(make_pair(tmp, s + "D"));
		visited[tmp] = true;
	}

	tmp = S(num);

	if (!visited[tmp])
	{
		q.push(make_pair(tmp, s + "S"));
		visited[tmp] = true;
	}

	tmp = L(num);

	if (!visited[tmp])
	{
		q.push(make_pair(tmp, s + "L"));
		visited[tmp] = true;
	}

	tmp = R(num);

	if (!visited[tmp])
	{
		q.push(make_pair(tmp, s + "R"));
		visited[tmp] = true;
	}
}

void BFS()
{
	int size;
	size = q.size();

	for (int i = 0; i < size; i++)
	{
		pair <int, string> p;
		p = q.front();
		q.pop();

		if (p.first == B)
		{
			cout << p.second << "\n";
			return;
		}

		pushQueue(p.first, p.second);
	}

	BFS();
}

void clearQueue()
{
	while (!q.empty())
	{
		q.pop();
	}
}

void initState()
{
	for (int i = 0; i < 10000; i++)
	{
		visited[i] = false;
	}
}

int main(void)
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> T;

	for (int t = 0; t < T; t++)
	{
		clearQueue();
		initState();
		cin >> A >> B;
		pushQueue(A, "");
		BFS();
	}

	return 0;
}