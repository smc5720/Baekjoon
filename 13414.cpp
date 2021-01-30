#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>

using namespace std;

// https://www.acmicpc.net/problem/13414

map <string, int> arr1;
map <int, string> arr2;
int K, L;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> K >> L;

	for (int i = 0; i < L; i++)
	{
		string t;
		cin >> t;

		if (arr1.count(t) > 0)
		{
			arr2[arr1[t]] = "None";
			arr1[t] = i;

			pair <int, string> p2;
			p2.first = i;
			p2.second = t;

			arr2.insert(p2);
		}

		else
		{
			pair <string, int> p1;
			pair <int, string> p2;
			p1.first = t;
			p1.second = i;

			p2.first = i;
			p2.second = t;

			arr1.insert(p1);
			arr2.insert(p2);
		}
	}

	int cnt;
	cnt = 0;

	auto itEnd = arr2.end();
	auto itBegin = arr2.begin();

	for (auto it = itBegin; it != itEnd; it++)
	{
		if (it->second != "None")
		{
			cout << it->second << "\n";
			cnt += 1;
		}

		if (cnt == K)
		{
			break;
		}
	}

	return 0;
}