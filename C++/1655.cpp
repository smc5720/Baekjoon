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

// https://www.acmicpc.net/problem/1655

struct cmp {
	bool operator()(int a, int b)
	{
		return a > b;
	}
};

int N;
priority_queue <int> maxQue;
priority_queue <int, vector<int>, cmp> minQue;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 0; i < N; i++)
	{
		int n;
		cin >> n;

		int minSize, maxSize;
		minSize = minQue.size();
		maxSize = maxQue.size();

		if (minSize == maxSize)
		{
			maxQue.push(n);
		}

		else
		{
			minQue.push(n);
		}

		if (!minQue.empty() && !maxQue.empty())
		{
			int minTop, maxTop;
			minTop = minQue.top();
			maxTop = maxQue.top();

			if (minTop < maxTop)
			{
				maxQue.pop();
				maxQue.push(minTop);
				minQue.pop();
				minQue.push(maxTop);
			}
		}

		cout << maxQue.top() << "\n";
	}

	return 0;
}