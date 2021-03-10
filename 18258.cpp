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

// https://www.acmicpc.net/problem/18258

int N;
deque <int> dq;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int n = 0; n < N; n++)
	{
		string s;
		cin >> s;

		if (s == "push")
		{
			int k;
			cin >> k;
			dq.push_back(k);
		}

		else if (s == "pop")
		{
			if (dq.empty())
			{
				cout << -1 << "\n";
			}

			else
			{
				cout << dq.front() << "\n";
				dq.pop_front();
			}
		}

		else if (s == "size")
		{
			cout << dq.size() << "\n";
		}

		else if (s == "empty")
		{
			cout << dq.empty() << "\n";
		}

		else if (s == "front")
		{
			if (dq.empty())
			{
				cout << -1 << "\n";
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
				cout << -1 << "\n";
			}

			else
			{
				cout << dq.back() << "\n";
			}
		}
	}

	return 0;
}