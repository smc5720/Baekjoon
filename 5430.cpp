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

// https://www.acmicpc.net/problem/5430

int T;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> T;

	for (int i = 0; i < T; i++)
	{
		deque <string> dq;

		string cmd;
		cin >> cmd;
		int cmdSize;
		cmdSize = cmd.length();

		int n;
		cin >> n;

		string arr;
		cin >> arr;
		int arrSize;
		arrSize = arr.length();

		string tmp;
		tmp = "";

		for (int j = 1; j < arrSize; j++)
		{
			if (arr[j] == ',' || arr[j] == ']')
			{
				dq.push_back(tmp);
				tmp = "";
			}

			else
			{
				tmp += arr[j];
			}
		}

		bool isReverse, isError;
		isReverse = false;
		isError = false;

		for (int j = 0; j < cmdSize; j++)
		{
			if (cmd[j] == 'D')
			{
				if (n == 0 || dq.empty())
				{
					cout << "error\n";
					isError = true;
					break;
				}

				else
				{
					if (isReverse)
					{
						dq.pop_back();
					}

					else
					{
						dq.pop_front();
					}
				}
			}

			else
			{
				isReverse = !isReverse;
			}
		}

		if (!isError)
		{
			cout << '[';

			if (isReverse)
			{
				while (!dq.empty())
				{
					cout << dq.back();
					dq.pop_back();

					if (!dq.empty())
					{
						cout << ",";
					}
				}
			}

			else
			{
				while (!dq.empty())
				{
					cout << dq.front();
					dq.pop_front();

					if (!dq.empty())
					{
						cout << ",";
					}
				}
			}

			cout << "]\n";
		}
	}

	return 0;
}