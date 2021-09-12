#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>

using namespace std;

// https://www.acmicpc.net/problem/9322

int T, n;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> T;

	for (int i = 0; i < T; i++)
	{
		map <string, int> encrypt;
		map <int, string> decrypt;

		cin >> n;

		for (int j = 1; j <= n; j++)
		{
			pair <string, int> p;
			cin >> p.first;
			p.second = j;

			encrypt.insert(p);
		}

		vector <int> v;

		for (int j = 1; j <= n; j++)
		{
			string t;
			cin >> t;

			v.push_back(encrypt[t]);
		}

		for (int j = 1; j <= n; j++)
		{
			pair <int, string> p;
			cin >> p.second;
			p.first = v[j - 1];
			
			decrypt.insert(p);
		}

		for (int j = 1; j <= n; j++)
		{
			cout << decrypt[j] << " ";
		}

		cout << "\n";
	}

	return 0;
}