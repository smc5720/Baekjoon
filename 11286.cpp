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

// https://www.acmicpc.net/problem/11286

int arr[100000];

struct cmp {
	bool operator()(int a, int b)
	{
		if (abs(a) > abs(b))
		{
			return true;
		}

		else if (abs(a) == abs(b))
		{
			if (a > b)
			{
				return true;
			}

			else
			{
				return false;
			}
		}

		return false;
	}
};

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	priority_queue <int, vector<int>, cmp> pq;

	int n;
	cin >> n;

	for (int i = 0; i < n; i++)
	{
		cin >> arr[i];
	}

	for (int i = 0; i < n; i++)
	{
		if (arr[i] == 0)
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
			pq.push(arr[i]);
		}
	}

	return 0;
}