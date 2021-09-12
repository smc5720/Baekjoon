#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>

using namespace std;

// https://www.acmicpc.net/problem/1966

int T, N, M;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> T;

	for (int i = 0; i < T; i++)
	{
		cin >> N >> M;

		queue<pair<int, int>> que;
		priority_queue<int> pque;

		for (int j = 0; j < N; j++)
		{
			pair<int, int> p;
			p.first = j;
			cin >> p.second;
			
			que.push(p);
			pque.push(p.second);
		}

		int cnt;
		cnt = 0;

		while(!que.empty())
		{
			pair<int, int> p;
			p = que.front();

			que.pop();

			if (pque.top() == p.second)
			{
				pque.pop();
				cnt += 1;

				if (p.first == M)
				{
					cout << cnt << "\n";
					break;
				}
			}
			
			else
			{
				que.push(p);
			}
		}
	}

	return 0;
}