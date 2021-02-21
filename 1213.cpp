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

// https://www.acmicpc.net/problem/1213

int arr[26];
string s;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> s;

	int size;
	size = s.length();

	for (int i = 0; i < 26; i++)
	{
		arr[i] = 0;
	}

	for (int i = 0; i < size; i++)
	{
		int n;
		n = s[i] - 65;
		arr[n] += 1;
	}

	string tmp;
	tmp = "";

	bool check;
	check = false;

	char c;
	c = '-';

	for (int i = 0; i < 26; i++)
	{
		if (arr[i] % 2 == 0)
		{
			for (int j = 0; j < arr[i] / 2; j++)
			{
				tmp += i + 65;
			}
		}

		else
		{
			if (check)
			{
				cout << "I'm Sorry Hansoo\n";
				exit(0);
			}

			else
			{
				check = true;
				c = i + 65;

				for (int j = 0; j < arr[i] / 2; j++)
				{
					tmp += i + 65;
				}
			}
		}
	}

	cout << tmp;
	
	if (check)
	{
		cout << c;
	}

	size = tmp.length();

	for (int i = size - 1; i >= 0; i--)
	{
		cout << tmp[i];
	}

	cout << "\n";

	return 0;
}