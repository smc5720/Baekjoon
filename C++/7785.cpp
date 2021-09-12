#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>

using namespace std;

// https://www.acmicpc.net/problem/7785

map <string, bool> arr;
int N;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 0; i < N; i++)
	{
		string t, s;

		cin >> t >> s;

		if (arr.count(t) > 0)
		{
			if (s == "enter")
			{
				arr[t] = true;
			}

			else
			{
				arr[t] = false;
			}
		}

		else
		{
			pair <string, bool> p;
			p.first = t;

			if (s == "enter")
			{
				p.second = true;
			}

			else
			{
				p.second = false;
			}

			arr.insert(p);
		}
	}

	auto itEnd = arr.end();
	auto itBegin = arr.begin();
	itEnd--;
	itBegin--;

	for (auto it = itEnd; it != itBegin; it--)
	{
		if (it->second == true)
		{
			cout << it->first << "\n";
		}
	}

	return 0;
}