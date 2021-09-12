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

// https://www.acmicpc.net/problem/14235

priority_queue <int> pq;
int n;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> n;

	for (int i = 0; i < n; i++)
	{
		int a;
		cin >> a;

		if (a == 0)
		{
			if (pq.empty())
			{
				cout << -1 << "\n";
			}

			else
			{
				cout << pq.top() << "\n";
				pq.pop();
			}
		}

		else
		{
			for (int j = 0; j < a; j++)
			{
				int b;
				cin >> b;
				pq.push(b);
			}
		}
	}

	return 0;
}