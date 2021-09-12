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

// https://www.acmicpc.net/problem/10799

stack <int> st;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	char c;
	cin.get(c);

	int ans;
	ans = 0;

	bool state;
	state = true;

	while (c == '(' || c == ')')
	{
		if (c == '(')
		{
			st.push(c);
			state = true;
		}

		else
		{
			char top;
			top = st.top();

			if (state)
			{
				st.pop();
				ans += st.size();
			}

			else
			{
				ans += 1;
				st.pop();
			}

			state = false;
		}

		cin.get(c);
	}

	cout << ans << "\n";

	return 0;
}