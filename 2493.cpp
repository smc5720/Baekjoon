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

// https://www.acmicpc.net/problem/2493

int N;
stack <pair <int, int>> inStack;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 1; i <= N; i++)
	{
		bool isFinish;
		isFinish = false;

		pair <int, int> p;
		p.first = i;
		cin >> p.second;

		while (!isFinish)
		{
			if (inStack.empty())
			{
				isFinish = true;
				cout << 0 << " ";
				inStack.push(p);
				break;
			}

			else
			{
				int top;
				top = inStack.top().second;

				if (top > p.second)
				{
					isFinish = true;
					cout << inStack.top().first << " ";
					inStack.push(p);
					break;
				}

				else
				{
					inStack.pop();
				}
			}
		}
	}

	cout << "\n";

	return 0;
}