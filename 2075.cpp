#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>
#include <stack>

using namespace std;

// https://www.acmicpc.net/problem/2075

struct cmp
{
	bool operator()(int a, int b)
	{
		return a > b;
	}
};

int N;
priority_queue <int, vector <int>, cmp> pq;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 0; i < N * N; i++)
	{
		long long c;
		cin >> c;

		if (pq.size() < N)
		{
			pq.push(c);
		}

		else
		{
			if (pq.top() < c)
			{
				pq.pop();
				pq.push(c);
			}
		}
	}

	cout << pq.top() << "\n";

	return 0;
}