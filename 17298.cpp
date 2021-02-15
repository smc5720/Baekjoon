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

// https://www.acmicpc.net/problem/17298

int N;
stack <int> A, B;
stack <int> output;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 0; i < N; i++)
	{
		int n;
		cin >> n;
		A.push(n);
	}

	for (int i = 0; i < N; i++)
	{
		int top;
		top = A.top();

		while (!B.empty() && B.top() <= top)
		{
			B.pop();
		}

		if (B.empty())
		{
			output.push(-1);
		}

		else
		{
			output.push(B.top());
		}

		B.push(top);
		A.pop();
	}

	for (int i = 0; i < N; i++)
	{
		cout << output.top() << " ";
		output.pop();
	}

	cout << "\n";

	return 0;
}