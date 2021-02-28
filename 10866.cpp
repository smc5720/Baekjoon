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

// https://www.acmicpc.net/problem/10866

int N;
deque <int> dq;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 0; i < N; i++)
	{
		string s;
		cin >> s;

		if (s == "push_front")
		{
			int n;
			cin >> n;
			dq.push_front(n);
		}

		else if (s == "push_back")
		{
			int n;
			cin >> n;
			dq.push_back(n);
		}

		else if (s == "pop_front")
		{
			if (dq.empty())
			{
				cout << "-1\n";
			}

			else
			{
				cout << dq.front() << "\n";
				dq.pop_front();
			}
		}

		else if (s == "pop_back")
		{
			if (dq.empty())
			{
				cout << "-1\n";
			}

			else
			{
				cout << dq.back() << "\n";
				dq.pop_back();
			}
		}

		else if (s == "size")
		{
			cout << dq.size() << "\n";
		}

		else if (s == "empty")
		{
			if (dq.empty())
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
			if (dq.empty())
			{
				cout << "-1\n";
			}

			else
			{
				cout << dq.front() << "\n";
			}
		}

		else if (s == "back")
		{
			if (dq.empty())
			{
				cout << "-1\n";
			}

			else
			{
				cout << dq.back() << "\n";
			}
		}
	}

	return 0;
}