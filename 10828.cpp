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

// https://www.acmicpc.net/problem/10828

int N;
stack <int> st;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 0; i < N; i++)
	{
		string command;
		cin >> command;

		if (command == "push")
		{
			int n;
			cin >> n;
			st.push(n);
		}

		else if (command == "pop")
		{
			if (st.empty())
			{
				cout << -1 << "\n";
			}

			else
			{
				cout << st.top() << "\n";
				st.pop();
			}
		}

		else if (command == "size")
		{
			cout << st.size() << "\n";
		}

		else if (command == "empty")
		{
			if (st.empty())
			{
				cout << 1 << "\n";
			}

			else
			{
				cout << 0 << "\n";
			}
		}

		else if (command == "top")
		{
			if (st.empty())
			{
				cout << -1 << "\n";
			}

			else
			{
				cout << st.top() << "\n";
			}
		}
	}

	return 0;
}