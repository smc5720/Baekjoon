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

// https://www.acmicpc.net/problem/7662

int T;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> T;

	for (int t = 0; t < T; t++)
	{
		multiset <int> dq;
		int k;
		cin >> k;

		for (int i = 0; i < k; i++)
		{
			char c;
			int num;
			cin >> c >> num;

			if (c == 'I')
			{
				dq.insert(num);
			}

			else if (c == 'D')
			{
				if (!dq.empty())
				{
					if (num == 1)
					{
						auto iter = dq.end();
						iter--;
						dq.erase(iter);
					}

					else
					{
						auto iter = dq.begin();
						dq.erase(iter);
					}
				}
			}
		}

		if (dq.empty())
		{
			cout << "EMPTY\n";
		}

		else
		{
			auto end = dq.end();
			end--;
			cout << *end << " " << *dq.begin() << "\n";
		}
	}

	return 0;
}