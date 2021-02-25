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

// https://www.acmicpc.net/problem/1927

struct cmp {
	bool operator()(int a, int b) {
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

	for (int i = 0; i < N; i++)
	{
		int x;
		cin >> x;

		if (x == 0)
		{
			if (pq.empty())
			{
				cout << 0 << "\n";
			}

			else
			{
				cout << pq.top() << "\n";
				pq.pop();
			}
		}

		else
		{
			pq.push(x);
		}
	}
}