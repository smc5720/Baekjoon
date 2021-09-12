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

// https://www.acmicpc.net/problem/11478

set <string> s;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	string input;
	cin >> input;

	int size;
	size = input.size();

	for (int i = 0; i < size; i++)
	{
		string tmp;
		tmp = "";

		for (int j = i; j < size; j++)
		{
			tmp += input[j];
			s.insert(tmp);
		}
	}

	cout << s.size() << "\n";

	return 0;
}