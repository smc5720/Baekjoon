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

// https://www.acmicpc.net/problem/1764

int N, M;
set <string> names;
vector <string> s;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> M;

	for (int i = 0; i < N + M; i++)
	{
		string t;
		cin >> t;

		if (names.count(t) == 0)
		{
			names.insert(t);
			continue;
		}

		s.push_back(t);
	}

	sort(s.begin(), s.end());

	int size;
	size = s.size();

	cout << size << "\n";

	for (int i = 0; i < size; i++)
	{
		cout << s[i] << "\n";
	}

	return 0;
}