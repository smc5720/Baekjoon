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

// https://www.acmicpc.net/problem/2011

#define DIV 1000000

string input;
int ans[5001];

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> input;

	ans[0] = 1;
	ans[1] = 1;

	int size;
	size = input.length();

	if (input[0] == '0')
	{
		cout << 0 << "\n";
		exit(0);
	}

	for (int i = 2; i <= size; i++)
	{
		int idx;
		idx = i - 1;

		if (input[idx] >= '1' && input[idx] <= '9')
		{
			if (input[idx - 1] == '1')
			{
				ans[i] = ans[i - 1] + ans[i - 2];
			}

			else if (input[idx - 1] == '2')
			{
				if (input[idx] >= '1' && input[idx] <= '6')
				{
					ans[i] = ans[i - 1] + ans[i - 2];
				}

				else
				{
					ans[i] = ans[i - 1];
				}
			}

			else
			{
				ans[i] = ans[i - 1];
			}
		}

		else
		{
			if (input[idx - 1] == '1' || input[idx - 1] == '2')
			{
				if (input[idx - 2] == '1' || input[idx - 2] == '2')
				{
					ans[i] = ans[i - 2];
				}

				else
				{
					ans[i] = ans[i - 1];
				}				
			}

			else
			{
				cout << 0 << "\n";
				exit(0);
			}
		}

		ans[i] %= DIV;
	}

	cout << ans[size] << "\n";

	return 0;
}