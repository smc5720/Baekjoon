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

// https://www.acmicpc.net/problem/1874

int n;
int arr[100000];
int number;
stack <int> st;
vector <char> ans;

void pushFunc()
{
	st.push(number);
	ans.push_back('+');
	number += 1;
}

void popFunc()
{
	st.pop();
	ans.push_back('-');
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> n;
	number = 1;

	for (int i = 0; i < n; i++)
	{
		cin >> arr[i];
	}

	for (int i = 0; i < n; i++)
	{
		if (st.empty())
		{
			if (arr[i] < number)
			{
				cout << "NO\n";
				exit(0);
			}

			pushFunc();
		}

		while (st.top() < arr[i])
		{
			pushFunc();
		}

		while (st.top() > arr[i])
		{
			popFunc();
		}

		if (st.top() == arr[i])
		{
			popFunc();
		}
	}

	int size;
	size = ans.size();

	for (int i = 0; i < size; i++)
	{
		cout << ans[i] << "\n";
	}

	return 0;
}