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

// https://www.acmicpc.net/problem/11723

int M;
set <int> S;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> M;

	for (int i = 0; i < M; i++)
	{
		string cmd;
		cin >> cmd;

		if (cmd == "add")
		{
			int n;
			cin >> n;
			S.insert(n);
		}

		else if (cmd == "remove")
		{
			int n;
			cin >> n;

			if (S.count(n))
			{
				S.erase(n);
			}
		}

		else if (cmd == "check")
		{
			int n;
			cin >> n;

			cout << S.count(n) << "\n";
		}

		else if (cmd == "toggle")
		{
			int n;
			cin >> n;

			if (S.count(n))
			{
				S.erase(n);
			}

			else
			{
				S.insert(n);
			}
		}

		else if (cmd == "all")
		{
			for (int i = 1; i <= 20; i++)
			{
				S.insert(i);
			}
		}

		else if (cmd == "empty")
		{
			S.clear();
		}
	}

	return 0;
}