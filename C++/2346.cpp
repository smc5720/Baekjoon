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

// https://www.acmicpc.net/problem/2346

int N;
deque <pair <int, int>> balloon;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 1; i <= N; i++)
	{
		int n;
		cin >> n;
		balloon.push_back(make_pair(i, n));
	}

	for (int i = 1; i <= N; i++)
	{
		int cnt;
		cnt = balloon.front().second;
		cout << balloon.front().first << " ";
		balloon.pop_front();

		if (!balloon.empty())
		{
			if (cnt > 0)
			{
				cnt -= 1;

				while (cnt > 0)
				{
					pair <int, int> tmp;

					tmp = balloon.front();
					balloon.pop_front();
					balloon.push_back(tmp);

					cnt -= 1;
				}
			}

			else
			{
				cnt *= -1;

				while (cnt > 0)
				{
					pair <int, int> tmp;

					tmp = balloon.back();
					balloon.pop_back();
					balloon.push_front(tmp);

					cnt -= 1;
				}
			}
		}
	}

	return 0;
}