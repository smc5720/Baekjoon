#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>

using namespace std;

// https://www.acmicpc.net/problem/1972

bool isSurprise(string s)
{
	for (int i = 1; i <= s.length() - 1; i++)
	{
		string a, b;
		map <string, bool> p;

		for (int j = 0; j + i < s.length(); j++)
		{
			a = s[j];
			b = s[j + i];

			a.append(b);

			if (p.count(a) > 0)
			{
				return false;
			}

			else
			{
				pair <string, bool> r;
				r.first = a;
				r.second = true;

				p.insert(r);
			}
		}
	}

	return true;
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	string s;

	cin >> s;

	while (s != "*")
	{
		if (isSurprise(s))
		{
			cout << s << " is surprising.\n";
		}

		else
		{
			cout << s << " is NOT surprising.\n";
		}

		cin >> s;
	}

	return 0;
}