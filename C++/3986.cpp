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

// https://www.acmicpc.net/problem/3986

int N;
int ans;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	ans = 0;

	for (int i = 0; i < N; i++)
	{
		stack <char> st;

		string word;
		cin >> word;

		int size;
		size = word.length();

		for (int j = 0; j < size; j++)
		{
			if (st.empty())
			{
				st.push(word[j]);
			}

			else
			{
				if (st.top() == word[j])
				{
					st.pop();
				}

				else
				{
					st.push(word[j]);
				}
			}
		}

		if (st.empty())
		{
			ans += 1;
		}
	}

	cout << ans << "\n";

	return 0;
}