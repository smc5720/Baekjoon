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

// https://www.acmicpc.net/problem/2161

queue <int> que;
int N;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 1; i <= N; i++)
	{
		que.push(i);
	}

	while (!que.empty())
	{
		cout << que.front() << " ";
		que.pop();

		if (que.empty())
		{
			break;
		}

		que.push(que.front());
		que.pop();
	}

	cout << "\n";

	return 0;
}