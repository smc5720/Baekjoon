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

// https://www.acmicpc.net/problem/10773

int K;
stack <int> st;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> K;

	for (int i = 0; i < K; i++)
	{
		int c;
		cin >> c;

		if (c == 0)
		{
			st.pop();
		}

		else
		{
			st.push(c);
		}
	}

	int size;
	size = st.size();

	int ans;
	ans = 0;

	for (int i = 0; i < size; i++)
	{
		ans += st.top();
		st.pop();
	}

	cout << ans << "\n";

	return 0;
}