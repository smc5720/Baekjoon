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

// https://www.acmicpc.net/problem/1935

stack <double> st;
int alphabet[27];

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	int N;
	cin >> N;

	string str;
	cin >> str;

	for (int i = 1; i <= N; i++)
	{
		cin >> alphabet[i];
	}

	for (int i = 0; i < str.length(); i++)
	{
		char c;
		c = str[i];

		if (c == '+')
		{
			double a, b;

			b = st.top();
			st.pop();

			a = st.top();
			st.pop();

			st.push(a + b);
		}

		else if (c == '-')
		{
			double a, b;

			b = st.top();
			st.pop();

			a = st.top();
			st.pop();

			st.push(a - b);
		}

		else if (c == '*')
		{
			double a, b;

			b = st.top();
			st.pop();

			a = st.top();
			st.pop();

			st.push(a * b);
		}

		else if (c == '/')
		{
			double a, b;

			b = st.top();
			st.pop();

			a = st.top();
			st.pop();

			st.push(a / b);
		}

		else
		{
			int num;
			num = alphabet[c - 64];

			st.push(num);
		}
	}

	printf("%.2f\n", st.top());

	return 0;
}