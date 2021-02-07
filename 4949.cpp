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

// https://www.acmicpc.net/problem/4949

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	string st;
	getline(cin, st);

	while (st != ".")
	{
		int size;
		size = st.length();

		stack <char> chStack;

		for (int i = 0; i < size; i++)
		{
			char c;
			c = st[i];

			if (c == '(' || c == '[')
			{
				chStack.push(c);
			}

			else if(c == ')' || c == ']')
			{
				if (chStack.empty())
				{
					cout << "no\n";
					break;
				}

				char top;
				top = chStack.top();

				if (c == ')')
				{
					if (top == '(')
					{
						chStack.pop();
					}

					else
					{
						cout << "no\n";
						break;
					}
				}

				else if(c == ']')
				{
					if (top == '[')
					{
						chStack.pop();
					}

					else
					{
						cout << "no\n";
						break;
					}
				}
			}

			if (i + 1 == size)
			{
				if (chStack.empty())
				{
					cout << "yes\n";
				}

				else
				{
					cout << "no\n";
				}
			}
		}

		getline(cin, st);
	}

	return 0;
}