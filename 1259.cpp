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

// https://www.acmicpc.net/problem/1259

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	string s;
	cin >> s;

	while (s != "0")
	{
		int size;
		size = s.size();

		string t;
		t = "";

		for (int i = size - 1; i >= 0; i--)
		{
			t += s[i];
		}

		if (t == s)
		{
			cout << "yes\n";
		}

		else
		{
			cout << "no\n";
		}

		cin >> s;
	}

	return 0;
}