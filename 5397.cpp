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

// https://www.acmicpc.net/problem/5397

int T;
deque <char> q;
stack <char> front, back;

void keyInput(char c)
{
	if (c == '<')
	{
		if (!front.empty())
		{
			char tmp;
			tmp = front.top();
			front.pop();
			back.push(tmp);
		}
	}

	else if (c == '>')
	{
		if (!back.empty())
		{
			char tmp;
			tmp = back.top();
			back.pop();
			front.push(tmp);
		}
	}

	else if (c == '-')
	{
		if (!front.empty())
		{
			front.pop();
		}
	}

	else
	{
		front.push(c);
	}
}

void printStack()
{
	stack <char> tmp;
	
	while (!front.empty())
	{
		tmp.push(front.top());
		front.pop();
	}

	while (!tmp.empty())
	{
		cout << tmp.top();
		tmp.pop();
	}

	while (!back.empty())
	{
		cout << back.top();
		back.pop();
	}

	cout << "\n";
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> T;

	for (int l = 0; l < T; l++)
	{
		string input;
		cin >> input;

		int size;
		size = input.length();

		for (int i = 0; i < size; i++)
		{
			keyInput(input[i]);
		}

		printStack();
	}

	return 0;
}