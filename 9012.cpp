#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>
#include <stack>

using namespace std;

// https://www.acmicpc.net/problem/9012

int T;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> T;

	for (int i = 0; i < T; i++)
	{
		stack <char> st;
		string input;
		cin >> input;

		int size;
		size = input.length();

		bool check;
		check = false;

		for (int j = 0; j < size; j++)
		{
			if (input[j] == '(')
			{
				st.push(input[j]);
			}

			else
			{
				if (st.empty())
				{
					cout << "NO\n";
					check = true;
					break;
				}

				else
				{
					st.pop();
				}
			}
		}

		if (!check)
		{
			if (st.empty())
			{
				cout << "YES\n";
			}

			else
			{
				cout << "NO\n";
			}
		}
	}

	return 0;
}