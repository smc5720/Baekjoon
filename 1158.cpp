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

// https://www.acmicpc.net/problem/1158

int N, K;
queue <int> q;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> K;

	for (int i = 1; i <= N; i++)
	{
		q.push(i);
	}

	cout << "<";

	while (!q.empty())
	{
		for (int i = 0; i < K - 1; i++)
		{
			int x;
			x = q.front();
			q.pop();
			q.push(x);
		}

		cout << q.front();
		q.pop();

		if (!q.empty())
		{
			cout << ", ";
		}
	}

	cout << ">\n";
}