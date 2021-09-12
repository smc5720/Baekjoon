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

// https://www.acmicpc.net/problem/11866

queue <int> que;
int N, K;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> K;

	for (int i = 1; i <= N; i++)
	{
		que.push(i);
	}

	cout << "<";

	while (!que.empty())
	{
		for (int i = 1; i < K; i++)
		{
			que.push(que.front());
			que.pop();
		}

		if (que.size() == 1)
		{
			cout << que.front() << ">\n";
		}

		else
		{
			cout << que.front() << ", ";
		}
		
		que.pop();
	}

	return 0;
}