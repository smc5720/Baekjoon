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

// https://www.acmicpc.net/problem/20001

stack <string> st;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	string input;
	getline(cin, input);
	getline(cin, input);

	while (input != "고무오리 디버깅 끝")
	{
		if (input == "문제")
		{
			st.push("문제");
		}

		else if (input == "고무오리")
		{
			if (st.empty())
			{
				st.push("문제");
				st.push("문제");
			}

			else
			{
				st.pop();
			}
		}

		getline(cin, input);
	}

	if (st.empty())
	{
		cout << "고무오리야 사랑해\n";
	}

	else
	{
		cout << "힝구\n";
	}

	return 0;
}