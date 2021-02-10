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

// https://www.acmicpc.net/problem/1406

int M;
string st;
stack<char> front, back, temp;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> st;

	int size;
	size = st.length();

	for (int i = 0; i < size; i++)
	{
		front.push(st[i]);
	}

	cin >> M;

	for (int i = 0; i < M; i++)
	{
		char c;
		cin >> c;

		if (c == 'L')
		{
			if (!front.empty())
			{
				char top;
				top = front.top();
				front.pop();
				back.push(top);
			}
		}

		else if (c == 'D')
		{
			if (!back.empty())
			{
				char top;
				top = back.top();
				back.pop();
				front.push(top);
			}
		}

		else if (c == 'B')
		{
			if (!front.empty())
			{
				front.pop();
			}
		}

		else if (c == 'P')
		{
			char p;
			cin >> p;
			front.push(p);
		}
	}

	int frontSize, backSize;
	frontSize = front.size();
	backSize = back.size();

	for (int i = 0; i < frontSize; i++)
	{
		temp.push(front.top());
		front.pop();
	}

	for (int i = 0; i < frontSize; i++)
	{
		cout << temp.top();
		temp.pop();
	}

	for (int i = 0; i < backSize; i++)
	{
		cout << back.top();
		back.pop();
	}

	cout << "\n";

	return 0;
}