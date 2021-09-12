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

// https://www.acmicpc.net/problem/12789

int N;
queue <int> que;
stack <int> st;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	int now;
	now = 1;

	for (int i = 1; i <= N; i++)
	{
		int n;
		cin >> n;
		que.push(n);
	}

	for (int i = 1; i <= N; i++)
	{
		//cout << "we want " << i << "\n";

		if (!que.empty() && que.front() == i)
		{
			//cout << "queue front: " << que.front() << "\n";
			que.pop();
			continue;
		}

		if (!st.empty() && st.top() == i)
		{
			//cout << "stack top: " << st.top() << "\n";
			st.pop();
			continue;
		}

		while (!que.empty() && que.front() != i)
		{
			//cout << "stack push: " << que.front() << "\n";
			st.push(que.front());
			que.pop();
		}

		if (que.empty())
		{
			//cout << "stack top: " << st.top() << "\n";

			if (st.top() == i)
			{
				st.pop();
			}

			else
			{
				cout << "Sad\n";
				exit(0);
			}
		}

		else
		{
			//cout << "queue front: " << que.front() << "\n";
			que.pop();
		}
	}

	cout << "Nice\n";

	return 0;
}