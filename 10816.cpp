#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>

using namespace std;

// https://www.acmicpc.net/problem/10816

int N, M;
int ans[500000];

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	map <int, int> hs;

	for (int i = 0; i < N; i++)
	{
		pair <int, int> p;
		cin >> p.first;

		if (hs.count(p.first) > 0)
		{
			hs[p.first] += 1;
		}

		else
		{
			p.second = 1;
			hs.insert(p);
		}
	}

	cin >> M;

	for (int i = 0; i < M; i++)
	{
		int num;
		cin >> num;

		ans[i] = hs[num];
	}

	for (int i = 0; i < M; i++)
	{
		cout << ans[i] << " ";
	}

	cout << "\n";

	return 0;
}