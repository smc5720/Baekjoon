#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>

using namespace std;

// https://www.acmicpc.net/problem/1302

map <string, int> arr;
int N;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 0; i < N; i++)
	{
		string t;
		cin >> t;

		if (arr.count(t) > 0)
		{
			arr[t] += 1;
		}

		else
		{
			pair <string, bool> p;
			p.first = t;
			p.second = 1;

			arr.insert(p);
		}
	}

	auto itEnd = arr.end();
	auto itBegin = arr.begin();

	int maxVal;
	maxVal = 0;

	string maxKey;

	for (auto it = itBegin; it != itEnd; it++)
	{
		if (maxVal < it->second)
		{
			maxVal = it->second;
			maxKey = it->first;
		}
	}

	cout << maxKey << "\n";

	return 0;
}