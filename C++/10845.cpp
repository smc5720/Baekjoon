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

// https://www.acmicpc.net/problem/10845

int N;
queue <int> q;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 0; i < N; i++)
	{
		string s;
		cin >> s;

		if (s == "push")
		{
			int n;
			cin >> n;
			q.push(n);
		}

		else if (s == "pop")
		{
			if (q.empty())
			{
				cout << "-1\n";
			}

			else
			{
				cout << q.front() << "\n";
				q.pop();
			}
		}

		else if (s == "size")
		{
			cout << q.size() << "\n";
		}

		else if (s == "empty")
		{
			if (q.empty())
			{
				cout << "1\n";
			}

			else
			{
				cout << "0\n";
			}
		}

		else if (s == "front")
		{
			if (q.empty())
			{
				cout << "-1\n";
			}

			else
			{
				cout << q.front() << "\n";
			}
		}

		else if (s == "back")
		{
			if (q.empty())
			{
				cout << "-1\n";
			}

			else
			{
				cout << q.back() << "\n";
			}
		}
	}

	return 0;
}