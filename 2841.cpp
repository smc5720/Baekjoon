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

// https://www.acmicpc.net/problem/2841

int N, P;
stack <int> line[7];

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> P;

	int ans;
	ans = 0;

	for (int i = 0; i < N; i++)
	{
		int n, p;
		cin >> n >> p;

		if (line[n].empty())
		{
			ans += 1;
			line[n].push(p);
		}

		else
		{
			int top;
			top = line[n].top();

			while (top > p)
			{
				ans += 1;
				line[n].pop();

				if (line[n].empty())
				{
					break;
				}

				top = line[n].top();
			}

			if (top != p)
			{
				ans += 1;
				line[n].push(p);
			}
		}
	}

	cout << ans << "\n";

	return 0;
}